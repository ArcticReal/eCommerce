package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.ident;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.ident.AddFixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.ident.DeleteFixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.ident.UpdateFixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.ident.FixedAssetIdentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.ident.FixedAssetIdent;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.ident.FindFixedAssetIdentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetIdents")
public class FixedAssetIdentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetIdentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetIdent
	 * @return a List with the FixedAssetIdents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetIdent>> findFixedAssetIdentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetIdentsBy query = new FindFixedAssetIdentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetIdent> fixedAssetIdents =((FixedAssetIdentFound) Scheduler.execute(query).data()).getFixedAssetIdents();

		return ResponseEntity.ok().body(fixedAssetIdents);

	}

	/**
	 * creates a new FixedAssetIdent entry in the ofbiz database
	 * 
	 * @param fixedAssetIdentToBeAdded
	 *            the FixedAssetIdent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetIdent> createFixedAssetIdent(@RequestBody FixedAssetIdent fixedAssetIdentToBeAdded) throws Exception {

		AddFixedAssetIdent command = new AddFixedAssetIdent(fixedAssetIdentToBeAdded);
		FixedAssetIdent fixedAssetIdent = ((FixedAssetIdentAdded) Scheduler.execute(command).data()).getAddedFixedAssetIdent();
		
		if (fixedAssetIdent != null) 
			return successful(fixedAssetIdent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetIdent with the specific Id
	 * 
	 * @param fixedAssetIdentToBeUpdated
	 *            the FixedAssetIdent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetIdent(@RequestBody FixedAssetIdent fixedAssetIdentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetIdentToBeUpdated.setnull(null);

		UpdateFixedAssetIdent command = new UpdateFixedAssetIdent(fixedAssetIdentToBeUpdated);

		try {
			if(((FixedAssetIdentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetIdentId}")
	public ResponseEntity<FixedAssetIdent> findById(@PathVariable String fixedAssetIdentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetIdentId", fixedAssetIdentId);
		try {

			List<FixedAssetIdent> foundFixedAssetIdent = findFixedAssetIdentsBy(requestParams).getBody();
			if(foundFixedAssetIdent.size()==1){				return successful(foundFixedAssetIdent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetIdentId}")
	public ResponseEntity<String> deleteFixedAssetIdentByIdUpdated(@PathVariable String fixedAssetIdentId) throws Exception {
		DeleteFixedAssetIdent command = new DeleteFixedAssetIdent(fixedAssetIdentId);

		try {
			if (((FixedAssetIdentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
