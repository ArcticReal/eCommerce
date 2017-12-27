package com.skytala.eCommerce.domain.product.relations.product.control.storeGroupMember;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupMember.AddProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupMember.DeleteProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupMember.UpdateProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember.ProductStoreGroupMemberAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember.ProductStoreGroupMemberDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember.ProductStoreGroupMemberFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember.ProductStoreGroupMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupMember.ProductStoreGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupMember.ProductStoreGroupMember;
import com.skytala.eCommerce.domain.product.relations.product.query.storeGroupMember.FindProductStoreGroupMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreGroupMembers")
public class ProductStoreGroupMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroupMember
	 * @return a List with the ProductStoreGroupMembers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreGroupMember>> findProductStoreGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupMembersBy query = new FindProductStoreGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroupMember> productStoreGroupMembers =((ProductStoreGroupMemberFound) Scheduler.execute(query).data()).getProductStoreGroupMembers();

		return ResponseEntity.ok().body(productStoreGroupMembers);

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
	public ResponseEntity<ProductStoreGroupMember> createProductStoreGroupMember(HttpServletRequest request) throws Exception {

		ProductStoreGroupMember productStoreGroupMemberToBeAdded = new ProductStoreGroupMember();
		try {
			productStoreGroupMemberToBeAdded = ProductStoreGroupMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductStoreGroupMember(productStoreGroupMemberToBeAdded);

	}

	/**
	 * creates a new ProductStoreGroupMember entry in the ofbiz database
	 * 
	 * @param productStoreGroupMemberToBeAdded
	 *            the ProductStoreGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreGroupMember> createProductStoreGroupMember(@RequestBody ProductStoreGroupMember productStoreGroupMemberToBeAdded) throws Exception {

		AddProductStoreGroupMember command = new AddProductStoreGroupMember(productStoreGroupMemberToBeAdded);
		ProductStoreGroupMember productStoreGroupMember = ((ProductStoreGroupMemberAdded) Scheduler.execute(command).data()).getAddedProductStoreGroupMember();
		
		if (productStoreGroupMember != null) 
			return successful(productStoreGroupMember);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreGroupMember with the specific Id
	 * 
	 * @param productStoreGroupMemberToBeUpdated
	 *            the ProductStoreGroupMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreGroupMember(@RequestBody ProductStoreGroupMember productStoreGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreGroupMemberToBeUpdated.setnull(null);

		UpdateProductStoreGroupMember command = new UpdateProductStoreGroupMember(productStoreGroupMemberToBeUpdated);

		try {
			if(((ProductStoreGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreGroupMemberId}")
	public ResponseEntity<ProductStoreGroupMember> findById(@PathVariable String productStoreGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupMemberId", productStoreGroupMemberId);
		try {

			List<ProductStoreGroupMember> foundProductStoreGroupMember = findProductStoreGroupMembersBy(requestParams).getBody();
			if(foundProductStoreGroupMember.size()==1){				return successful(foundProductStoreGroupMember.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreGroupMemberId}")
	public ResponseEntity<String> deleteProductStoreGroupMemberByIdUpdated(@PathVariable String productStoreGroupMemberId) throws Exception {
		DeleteProductStoreGroupMember command = new DeleteProductStoreGroupMember(productStoreGroupMemberId);

		try {
			if (((ProductStoreGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
