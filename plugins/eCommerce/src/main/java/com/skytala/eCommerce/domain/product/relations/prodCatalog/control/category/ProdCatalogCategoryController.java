package com.skytala.eCommerce.domain.product.relations.prodCatalog.control.category;

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
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.category.AddProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.category.DeleteProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.category.UpdateProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryDeleted;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.category.ProdCatalogCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.category.ProdCatalogCategory;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.query.category.FindProdCatalogCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/prodCatalog/prodCatalogCategorys")
public class ProdCatalogCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdCatalogCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdCatalogCategory
	 * @return a List with the ProdCatalogCategorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProdCatalogCategory>> findProdCatalogCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogCategorysBy query = new FindProdCatalogCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalogCategory> prodCatalogCategorys =((ProdCatalogCategoryFound) Scheduler.execute(query).data()).getProdCatalogCategorys();

		return ResponseEntity.ok().body(prodCatalogCategorys);

	}

	/**
	 * creates a new ProdCatalogCategory entry in the ofbiz database
	 * 
	 * @param prodCatalogCategoryToBeAdded
	 *            the ProdCatalogCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProdCatalogCategory> createProdCatalogCategory(@RequestBody ProdCatalogCategory prodCatalogCategoryToBeAdded) throws Exception {

		AddProdCatalogCategory command = new AddProdCatalogCategory(prodCatalogCategoryToBeAdded);
		ProdCatalogCategory prodCatalogCategory = ((ProdCatalogCategoryAdded) Scheduler.execute(command).data()).getAddedProdCatalogCategory();
		
		if (prodCatalogCategory != null) 
			return successful(prodCatalogCategory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProdCatalogCategory with the specific Id
	 * 
	 * @param prodCatalogCategoryToBeUpdated
	 *            the ProdCatalogCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProdCatalogCategory(@RequestBody ProdCatalogCategory prodCatalogCategoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		prodCatalogCategoryToBeUpdated.setnull(null);

		UpdateProdCatalogCategory command = new UpdateProdCatalogCategory(prodCatalogCategoryToBeUpdated);

		try {
			if(((ProdCatalogCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{prodCatalogCategoryId}")
	public ResponseEntity<ProdCatalogCategory> findById(@PathVariable String prodCatalogCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogCategoryId", prodCatalogCategoryId);
		try {

			List<ProdCatalogCategory> foundProdCatalogCategory = findProdCatalogCategorysBy(requestParams).getBody();
			if(foundProdCatalogCategory.size()==1){				return successful(foundProdCatalogCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{prodCatalogCategoryId}")
	public ResponseEntity<String> deleteProdCatalogCategoryByIdUpdated(@PathVariable String prodCatalogCategoryId) throws Exception {
		DeleteProdCatalogCategory command = new DeleteProdCatalogCategory(prodCatalogCategoryId);

		try {
			if (((ProdCatalogCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
