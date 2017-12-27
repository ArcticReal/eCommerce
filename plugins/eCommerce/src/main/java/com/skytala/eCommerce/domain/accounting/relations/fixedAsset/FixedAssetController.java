package com.skytala.eCommerce.domain.accounting.relations.fixedAsset;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.AddFixedAsset;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.DeleteFixedAsset;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.UpdateFixedAsset;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.FixedAssetAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.FixedAssetDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.FixedAssetFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.FixedAssetUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.FixedAssetMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.FixedAsset;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.FindFixedAssetsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAssets")
public class FixedAssetController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAsset
	 * @return a List with the FixedAssets
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAsset>> findFixedAssetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetsBy query = new FindFixedAssetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAsset> fixedAssets =((FixedAssetFound) Scheduler.execute(query).data()).getFixedAssets();

		return ResponseEntity.ok().body(fixedAssets);

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
	public ResponseEntity<FixedAsset> createFixedAsset(HttpServletRequest request) throws Exception {

		FixedAsset fixedAssetToBeAdded = new FixedAsset();
		try {
			fixedAssetToBeAdded = FixedAssetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFixedAsset(fixedAssetToBeAdded);

	}

	/**
	 * creates a new FixedAsset entry in the ofbiz database
	 * 
	 * @param fixedAssetToBeAdded
	 *            the FixedAsset thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAsset> createFixedAsset(@RequestBody FixedAsset fixedAssetToBeAdded) throws Exception {

		AddFixedAsset command = new AddFixedAsset(fixedAssetToBeAdded);
		FixedAsset fixedAsset = ((FixedAssetAdded) Scheduler.execute(command).data()).getAddedFixedAsset();
		
		if (fixedAsset != null) 
			return successful(fixedAsset);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAsset with the specific Id
	 * 
	 * @param fixedAssetToBeUpdated
	 *            the FixedAsset thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAsset(@RequestBody FixedAsset fixedAssetToBeUpdated,
			@PathVariable String fixedAssetId) throws Exception {

		fixedAssetToBeUpdated.setFixedAssetId(fixedAssetId);

		UpdateFixedAsset command = new UpdateFixedAsset(fixedAssetToBeUpdated);

		try {
			if(((FixedAssetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetId}")
	public ResponseEntity<FixedAsset> findById(@PathVariable String fixedAssetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetId", fixedAssetId);
		try {

			List<FixedAsset> foundFixedAsset = findFixedAssetsBy(requestParams).getBody();
			if(foundFixedAsset.size()==1){				return successful(foundFixedAsset.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetId}")
	public ResponseEntity<String> deleteFixedAssetByIdUpdated(@PathVariable String fixedAssetId) throws Exception {
		DeleteFixedAsset command = new DeleteFixedAsset(fixedAssetId);

		try {
			if (((FixedAssetDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
