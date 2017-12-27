package com.skytala.eCommerce.domain.product.relations.product.control.categoryMember;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.framework.util.TimestampUtil;
import org.apache.ofbiz.base.util.UtilMisc;
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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryMember.AddProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryMember.DeleteProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryMember.UpdateProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryMember.ProductCategoryMemberAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryMember.ProductCategoryMemberDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryMember.ProductCategoryMemberFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryMember.ProductCategoryMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryMember.ProductCategoryMemberMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryMember.ProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryMember.FindProductCategoryMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productCategoryMembers")
public class ProductCategoryMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryMember
	 * @return a List with the ProductCategoryMembers
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<ProductCategoryMember>> findProductCategoryMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryMembersBy query = new FindProductCategoryMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryMember> productCategoryMembers =((ProductCategoryMemberFound) Scheduler.execute(query).data()).getProductCategoryMembers();

		return ResponseEntity.ok().body(getValidMembers(productCategoryMembers));

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductCategoryMember(HttpServletRequest request) throws Exception {

		ProductCategoryMember productCategoryMemberToBeAdded = new ProductCategoryMember();
		try {
			productCategoryMemberToBeAdded = ProductCategoryMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryMember(productCategoryMemberToBeAdded);

	}

	/**
	 * creates a new ProductCategoryMember entry in the ofbiz database
	 * 
	 * @param productCategoryMemberToBeAdded
	 *            the ProductCategoryMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryMember(@RequestBody ProductCategoryMember productCategoryMemberToBeAdded) throws Exception {

		AddProductCategoryMember command = new AddProductCategoryMember(productCategoryMemberToBeAdded);
		ProductCategoryMember productCategoryMember = ((ProductCategoryMemberAdded) Scheduler.execute(command).data()).getAddedProductCategoryMember();
		
		if (productCategoryMember != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryMember);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryMember could not be created.");
	}


	/**
	 * Updates the ProductCategoryMember with the specific Id
	 * 
	 * @param productCategoryMemberToBeUpdated
	 *            the ProductCategoryMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryMember(@RequestBody ProductCategoryMember productCategoryMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryMemberToBeUpdated.setnull(null);

		UpdateProductCategoryMember command = new UpdateProductCategoryMember(productCategoryMemberToBeUpdated);

		try {
			if(((ProductCategoryMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCategoryMemberId}")
	public ResponseEntity<ProductCategoryMember> findById(@PathVariable String productCategoryMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryMemberId", productCategoryMemberId);
		try {

			List<ProductCategoryMember> foundProductCategoryMember = findProductCategoryMembersBy(requestParams).getBody();
			if(foundProductCategoryMember.size()==1)
				return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryMember.get(0));
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCategoryId}")
	public ResponseEntity<Object> deleteProductCategoryMemberByCategoryId(@PathVariable String productCategoryId) throws Exception {
		DeleteProductCategoryMember command = new DeleteProductCategoryMember(productCategoryId);

		try {
			if (((ProductCategoryMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryMember could not be deleted");

	}


	public List<ProductCategoryMember> getValidMembers(List<ProductCategoryMember> members){
		List<ProductCategoryMember> returnVal = new LinkedList<>();
		for(ProductCategoryMember member : members){
			if((member.getThruDate()==null||member.getThruDate().after(TimestampUtil.currentTime()))
					&&member.getFromDate().before(TimestampUtil.currentTime())){
				returnVal.add(member);
			}
		}
		return returnVal;
	}


}
