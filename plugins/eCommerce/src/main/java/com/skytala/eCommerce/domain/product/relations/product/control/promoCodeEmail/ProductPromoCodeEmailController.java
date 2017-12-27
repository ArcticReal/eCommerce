package com.skytala.eCommerce.domain.product.relations.product.control.promoCodeEmail;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoCodeEmail.AddProductPromoCodeEmail;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCodeEmail.DeleteProductPromoCodeEmail;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCodeEmail.UpdateProductPromoCodeEmail;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail.ProductPromoCodeEmailAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail.ProductPromoCodeEmailDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail.ProductPromoCodeEmailFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail.ProductPromoCodeEmailUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCodeEmail.ProductPromoCodeEmailMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeEmail.ProductPromoCodeEmail;
import com.skytala.eCommerce.domain.product.relations.product.query.promoCodeEmail.FindProductPromoCodeEmailsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoCodeEmails")
public class ProductPromoCodeEmailController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoCodeEmailController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoCodeEmail
	 * @return a List with the ProductPromoCodeEmails
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoCodeEmail>> findProductPromoCodeEmailsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCodeEmailsBy query = new FindProductPromoCodeEmailsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCodeEmail> productPromoCodeEmails =((ProductPromoCodeEmailFound) Scheduler.execute(query).data()).getProductPromoCodeEmails();

		return ResponseEntity.ok().body(productPromoCodeEmails);

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
	public ResponseEntity<ProductPromoCodeEmail> createProductPromoCodeEmail(HttpServletRequest request) throws Exception {

		ProductPromoCodeEmail productPromoCodeEmailToBeAdded = new ProductPromoCodeEmail();
		try {
			productPromoCodeEmailToBeAdded = ProductPromoCodeEmailMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductPromoCodeEmail(productPromoCodeEmailToBeAdded);

	}

	/**
	 * creates a new ProductPromoCodeEmail entry in the ofbiz database
	 * 
	 * @param productPromoCodeEmailToBeAdded
	 *            the ProductPromoCodeEmail thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoCodeEmail> createProductPromoCodeEmail(@RequestBody ProductPromoCodeEmail productPromoCodeEmailToBeAdded) throws Exception {

		AddProductPromoCodeEmail command = new AddProductPromoCodeEmail(productPromoCodeEmailToBeAdded);
		ProductPromoCodeEmail productPromoCodeEmail = ((ProductPromoCodeEmailAdded) Scheduler.execute(command).data()).getAddedProductPromoCodeEmail();
		
		if (productPromoCodeEmail != null) 
			return successful(productPromoCodeEmail);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromoCodeEmail with the specific Id
	 * 
	 * @param productPromoCodeEmailToBeUpdated
	 *            the ProductPromoCodeEmail thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emailAddress}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromoCodeEmail(@RequestBody ProductPromoCodeEmail productPromoCodeEmailToBeUpdated,
			@PathVariable String emailAddress) throws Exception {

		productPromoCodeEmailToBeUpdated.setEmailAddress(emailAddress);

		UpdateProductPromoCodeEmail command = new UpdateProductPromoCodeEmail(productPromoCodeEmailToBeUpdated);

		try {
			if(((ProductPromoCodeEmailUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoCodeEmailId}")
	public ResponseEntity<ProductPromoCodeEmail> findById(@PathVariable String productPromoCodeEmailId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCodeEmailId", productPromoCodeEmailId);
		try {

			List<ProductPromoCodeEmail> foundProductPromoCodeEmail = findProductPromoCodeEmailsBy(requestParams).getBody();
			if(foundProductPromoCodeEmail.size()==1){				return successful(foundProductPromoCodeEmail.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoCodeEmailId}")
	public ResponseEntity<String> deleteProductPromoCodeEmailByIdUpdated(@PathVariable String productPromoCodeEmailId) throws Exception {
		DeleteProductPromoCodeEmail command = new DeleteProductPromoCodeEmail(productPromoCodeEmailId);

		try {
			if (((ProductPromoCodeEmailDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
