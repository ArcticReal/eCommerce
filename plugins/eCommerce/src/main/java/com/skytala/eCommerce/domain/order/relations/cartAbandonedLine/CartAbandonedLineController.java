package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.command.AddCartAbandonedLine;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.command.DeleteCartAbandonedLine;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.command.UpdateCartAbandonedLine;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event.CartAbandonedLineAdded;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event.CartAbandonedLineDeleted;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event.CartAbandonedLineFound;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event.CartAbandonedLineUpdated;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.mapper.CartAbandonedLineMapper;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.query.FindCartAbandonedLinesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/cartAbandonedLines")
public class CartAbandonedLineController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CartAbandonedLineController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CartAbandonedLine
	 * @return a List with the CartAbandonedLines
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CartAbandonedLine>> findCartAbandonedLinesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCartAbandonedLinesBy query = new FindCartAbandonedLinesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CartAbandonedLine> cartAbandonedLines =((CartAbandonedLineFound) Scheduler.execute(query).data()).getCartAbandonedLines();

		return ResponseEntity.ok().body(cartAbandonedLines);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<CartAbandonedLine> createCartAbandonedLine(HttpServletRequest request) throws Exception {

		CartAbandonedLine cartAbandonedLineToBeAdded = new CartAbandonedLine();
		try {
			cartAbandonedLineToBeAdded = CartAbandonedLineMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCartAbandonedLine(cartAbandonedLineToBeAdded);

	}

	/**
	 * creates a new CartAbandonedLine entry in the ofbiz database
	 * 
	 * @param cartAbandonedLineToBeAdded
	 *            the CartAbandonedLine thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CartAbandonedLine> createCartAbandonedLine(@RequestBody CartAbandonedLine cartAbandonedLineToBeAdded) throws Exception {

		AddCartAbandonedLine command = new AddCartAbandonedLine(cartAbandonedLineToBeAdded);
		CartAbandonedLine cartAbandonedLine = ((CartAbandonedLineAdded) Scheduler.execute(command).data()).getAddedCartAbandonedLine();
		
		if (cartAbandonedLine != null) 
			return successful(cartAbandonedLine);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CartAbandonedLine with the specific Id
	 * 
	 * @param cartAbandonedLineToBeUpdated
	 *            the CartAbandonedLine thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCartAbandonedLine(@RequestBody CartAbandonedLine cartAbandonedLineToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		cartAbandonedLineToBeUpdated.setnull(null);

		UpdateCartAbandonedLine command = new UpdateCartAbandonedLine(cartAbandonedLineToBeUpdated);

		try {
			if(((CartAbandonedLineUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{cartAbandonedLineId}")
	public ResponseEntity<CartAbandonedLine> findById(@PathVariable String cartAbandonedLineId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("cartAbandonedLineId", cartAbandonedLineId);
		try {

			List<CartAbandonedLine> foundCartAbandonedLine = findCartAbandonedLinesBy(requestParams).getBody();
			if(foundCartAbandonedLine.size()==1){				return successful(foundCartAbandonedLine.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{cartAbandonedLineId}")
	public ResponseEntity<String> deleteCartAbandonedLineByIdUpdated(@PathVariable String cartAbandonedLineId) throws Exception {
		DeleteCartAbandonedLine command = new DeleteCartAbandonedLine(cartAbandonedLineId);

		try {
			if (((CartAbandonedLineDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
