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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findProdCatalogsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProdCatalogsBy query = new FindProdCatalogsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProdCatalog> prodCatalogs =((ProdCatalogFound) Scheduler.execute(query).data()).getProdCatalogs();

		if (prodCatalogs.size() == 1) {
			return ResponseEntity.ok().body(prodCatalogs.get(0));
		}

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
	public ResponseEntity<Object> createProdCatalog(HttpServletRequest request) throws Exception {

		ProdCatalog prodCatalogToBeAdded = new ProdCatalog();
		try {
			prodCatalogToBeAdded = ProdCatalogMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProdCatalog(@RequestBody ProdCatalog prodCatalogToBeAdded) throws Exception {

		AddProdCatalog command = new AddProdCatalog(prodCatalogToBeAdded);
		ProdCatalog prodCatalog = ((ProdCatalogAdded) Scheduler.execute(command).data()).getAddedProdCatalog();
		
		if (prodCatalog != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(prodCatalog);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProdCatalog could not be created.");
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
	public boolean updateProdCatalog(HttpServletRequest request) throws Exception {

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

		ProdCatalog prodCatalogToBeUpdated = new ProdCatalog();

		try {
			prodCatalogToBeUpdated = ProdCatalogMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProdCatalog(prodCatalogToBeUpdated, prodCatalogToBeUpdated.getProdCatalogId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProdCatalog(@RequestBody ProdCatalog prodCatalogToBeUpdated,
			@PathVariable String prodCatalogId) throws Exception {

		prodCatalogToBeUpdated.setProdCatalogId(prodCatalogId);

		UpdateProdCatalog command = new UpdateProdCatalog(prodCatalogToBeUpdated);

		try {
			if(((ProdCatalogUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{prodCatalogId}")
	public ResponseEntity<Object> findById(@PathVariable String prodCatalogId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("prodCatalogId", prodCatalogId);
		try {

			Object foundProdCatalog = findProdCatalogsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProdCatalog);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{prodCatalogId}")
	public ResponseEntity<Object> deleteProdCatalogByIdUpdated(@PathVariable String prodCatalogId) throws Exception {
		DeleteProdCatalog command = new DeleteProdCatalog(prodCatalogId);

		try {
			if (((ProdCatalogDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProdCatalog could not be deleted");

	}

}
