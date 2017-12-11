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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findProductPromoCodeEmailsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCodeEmailsBy query = new FindProductPromoCodeEmailsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCodeEmail> productPromoCodeEmails =((ProductPromoCodeEmailFound) Scheduler.execute(query).data()).getProductPromoCodeEmails();

		if (productPromoCodeEmails.size() == 1) {
			return ResponseEntity.ok().body(productPromoCodeEmails.get(0));
		}

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
	public ResponseEntity<Object> createProductPromoCodeEmail(HttpServletRequest request) throws Exception {

		ProductPromoCodeEmail productPromoCodeEmailToBeAdded = new ProductPromoCodeEmail();
		try {
			productPromoCodeEmailToBeAdded = ProductPromoCodeEmailMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductPromoCodeEmail(@RequestBody ProductPromoCodeEmail productPromoCodeEmailToBeAdded) throws Exception {

		AddProductPromoCodeEmail command = new AddProductPromoCodeEmail(productPromoCodeEmailToBeAdded);
		ProductPromoCodeEmail productPromoCodeEmail = ((ProductPromoCodeEmailAdded) Scheduler.execute(command).data()).getAddedProductPromoCodeEmail();
		
		if (productPromoCodeEmail != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoCodeEmail);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoCodeEmail could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductPromoCodeEmail(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ProductPromoCodeEmail productPromoCodeEmailToBeUpdated = new ProductPromoCodeEmail();

		try {
			productPromoCodeEmailToBeUpdated = ProductPromoCodeEmailMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoCodeEmail(productPromoCodeEmailToBeUpdated, productPromoCodeEmailToBeUpdated.getEmailAddress()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductPromoCodeEmail(@RequestBody ProductPromoCodeEmail productPromoCodeEmailToBeUpdated,
			@PathVariable String emailAddress) throws Exception {

		productPromoCodeEmailToBeUpdated.setEmailAddress(emailAddress);

		UpdateProductPromoCodeEmail command = new UpdateProductPromoCodeEmail(productPromoCodeEmailToBeUpdated);

		try {
			if(((ProductPromoCodeEmailUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productPromoCodeEmailId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoCodeEmailId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCodeEmailId", productPromoCodeEmailId);
		try {

			Object foundProductPromoCodeEmail = findProductPromoCodeEmailsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoCodeEmail);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productPromoCodeEmailId}")
	public ResponseEntity<Object> deleteProductPromoCodeEmailByIdUpdated(@PathVariable String productPromoCodeEmailId) throws Exception {
		DeleteProductPromoCodeEmail command = new DeleteProductPromoCodeEmail(productPromoCodeEmailId);

		try {
			if (((ProductPromoCodeEmailDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoCodeEmail could not be deleted");

	}

}
