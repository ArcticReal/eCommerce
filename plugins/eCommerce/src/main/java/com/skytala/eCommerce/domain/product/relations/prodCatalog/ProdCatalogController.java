package com.skytala.eCommerce.domain.product.relations.prodCatalog;

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
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.AddProdCatalog;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.DeleteProdCatalog;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.command.UpdateProdCatalog;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.ProdCatalogAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.ProdCatalogDeleted;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.ProdCatalogFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.ProdCatalogUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.ProdCatalogMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.ProdCatalog;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.query.FindProdCatalogsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/prodCatalogs")
public class ProdCatalogController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProdCatalogController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProdCatalog
	 * @return a List with the ProdCatalogs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProdCatalog>> findProdCatalogsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogsBy query = new FindProdCatalogsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalog> prodCatalogs =((ProdCatalogFound) Scheduler.execute(query).data()).getProdCatalogs();

		return ResponseEntity.ok().body(prodCatalogs);

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
	public ResponseEntity<ProdCatalog> createProdCatalog(HttpServletRequest request) throws Exception {

		ProdCatalog prodCatalogToBeAdded = new ProdCatalog();
		try {
			prodCatalogToBeAdded = ProdCatalogMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProdCatalog(prodCatalogToBeAdded);

	}

	/**
	 * creates a new ProdCatalog entry in the ofbiz database
	 * 
	 * @param prodCatalogToBeAdded
	 *            the ProdCatalog thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProdCatalog> createProdCatalog(@RequestBody ProdCatalog prodCatalogToBeAdded) throws Exception {

		AddProdCatalog command = new AddProdCatalog(prodCatalogToBeAdded);
		ProdCatalog prodCatalog = ((ProdCatalogAdded) Scheduler.execute(command).data()).getAddedProdCatalog();
		
		if (prodCatalog != null) 
			return successful(prodCatalog);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProdCatalog with the specific Id
	 * 
	 * @param prodCatalogToBeUpdated
	 *            the ProdCatalog thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{prodCatalogId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProdCatalog(@RequestBody ProdCatalog prodCatalogToBeUpdated,
			@PathVariable String prodCatalogId) throws Exception {

		prodCatalogToBeUpdated.setProdCatalogId(prodCatalogId);

		UpdateProdCatalog command = new UpdateProdCatalog(prodCatalogToBeUpdated);

		try {
			if(((ProdCatalogUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{prodCatalogId}")
	public ResponseEntity<ProdCatalog> findById(@PathVariable String prodCatalogId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogId", prodCatalogId);
		try {

			List<ProdCatalog> foundProdCatalog = findProdCatalogsBy(requestParams).getBody();
			if(foundProdCatalog.size()==1){				return successful(foundProdCatalog.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{prodCatalogId}")
	public ResponseEntity<String> deleteProdCatalogByIdUpdated(@PathVariable String prodCatalogId) throws Exception {
		DeleteProdCatalog command = new DeleteProdCatalog(prodCatalogId);

		try {
			if (((ProdCatalogDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
