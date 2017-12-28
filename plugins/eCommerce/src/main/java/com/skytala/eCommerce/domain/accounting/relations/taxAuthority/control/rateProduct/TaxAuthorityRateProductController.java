package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.control.rateProduct;

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
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.rateProduct.AddTaxAuthorityRateProduct;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.rateProduct.DeleteTaxAuthorityRateProduct;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command.rateProduct.UpdateTaxAuthorityRateProduct;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct.TaxAuthorityRateProductAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct.TaxAuthorityRateProductDeleted;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct.TaxAuthorityRateProductFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct.TaxAuthorityRateProductUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.rateProduct.TaxAuthorityRateProductMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct.TaxAuthorityRateProduct;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.rateProduct.FindTaxAuthorityRateProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/taxAuthority/taxAuthorityRateProducts")
public class TaxAuthorityRateProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TaxAuthorityRateProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TaxAuthorityRateProduct
	 * @return a List with the TaxAuthorityRateProducts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TaxAuthorityRateProduct>> findTaxAuthorityRateProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTaxAuthorityRateProductsBy query = new FindTaxAuthorityRateProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TaxAuthorityRateProduct> taxAuthorityRateProducts =((TaxAuthorityRateProductFound) Scheduler.execute(query).data()).getTaxAuthorityRateProducts();

		return ResponseEntity.ok().body(taxAuthorityRateProducts);

	}

	/**
	 * creates a new TaxAuthorityRateProduct entry in the ofbiz database
	 * 
	 * @param taxAuthorityRateProductToBeAdded
	 *            the TaxAuthorityRateProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TaxAuthorityRateProduct> createTaxAuthorityRateProduct(@RequestBody TaxAuthorityRateProduct taxAuthorityRateProductToBeAdded) throws Exception {

		AddTaxAuthorityRateProduct command = new AddTaxAuthorityRateProduct(taxAuthorityRateProductToBeAdded);
		TaxAuthorityRateProduct taxAuthorityRateProduct = ((TaxAuthorityRateProductAdded) Scheduler.execute(command).data()).getAddedTaxAuthorityRateProduct();
		
		if (taxAuthorityRateProduct != null) 
			return successful(taxAuthorityRateProduct);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TaxAuthorityRateProduct with the specific Id
	 * 
	 * @param taxAuthorityRateProductToBeUpdated
	 *            the TaxAuthorityRateProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{taxAuthorityRateSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTaxAuthorityRateProduct(@RequestBody TaxAuthorityRateProduct taxAuthorityRateProductToBeUpdated,
			@PathVariable String taxAuthorityRateSeqId) throws Exception {

		taxAuthorityRateProductToBeUpdated.setTaxAuthorityRateSeqId(taxAuthorityRateSeqId);

		UpdateTaxAuthorityRateProduct command = new UpdateTaxAuthorityRateProduct(taxAuthorityRateProductToBeUpdated);

		try {
			if(((TaxAuthorityRateProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{taxAuthorityRateProductId}")
	public ResponseEntity<TaxAuthorityRateProduct> findById(@PathVariable String taxAuthorityRateProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("taxAuthorityRateProductId", taxAuthorityRateProductId);
		try {

			List<TaxAuthorityRateProduct> foundTaxAuthorityRateProduct = findTaxAuthorityRateProductsBy(requestParams).getBody();
			if(foundTaxAuthorityRateProduct.size()==1){				return successful(foundTaxAuthorityRateProduct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{taxAuthorityRateProductId}")
	public ResponseEntity<String> deleteTaxAuthorityRateProductByIdUpdated(@PathVariable String taxAuthorityRateProductId) throws Exception {
		DeleteTaxAuthorityRateProduct command = new DeleteTaxAuthorityRateProduct(taxAuthorityRateProductId);

		try {
			if (((TaxAuthorityRateProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
