package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeAttr.AddFixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeAttr.DeleteFixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeAttr.UpdateFixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr.FixedAssetTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeAttr.FixedAssetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeAttr.FixedAssetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.typeAttr.FindFixedAssetTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetTypeAttrs")
public class FixedAssetTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetTypeAttr
	 * @return a List with the FixedAssetTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetTypeAttr>> findFixedAssetTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetTypeAttrsBy query = new FindFixedAssetTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetTypeAttr> fixedAssetTypeAttrs =((FixedAssetTypeAttrFound) Scheduler.execute(query).data()).getFixedAssetTypeAttrs();

		return ResponseEntity.ok().body(fixedAssetTypeAttrs);

	}

	/**
	 * creates a new FixedAssetTypeAttr entry in the ofbiz database
	 * 
	 * @param fixedAssetTypeAttrToBeAdded
	 *            the FixedAssetTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetTypeAttr> createFixedAssetTypeAttr(@RequestBody FixedAssetTypeAttr fixedAssetTypeAttrToBeAdded) throws Exception {

		AddFixedAssetTypeAttr command = new AddFixedAssetTypeAttr(fixedAssetTypeAttrToBeAdded);
		FixedAssetTypeAttr fixedAssetTypeAttr = ((FixedAssetTypeAttrAdded) Scheduler.execute(command).data()).getAddedFixedAssetTypeAttr();
		
		if (fixedAssetTypeAttr != null) 
			return successful(fixedAssetTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetTypeAttr with the specific Id
	 * 
	 * @param fixedAssetTypeAttrToBeUpdated
	 *            the FixedAssetTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetTypeAttr(@RequestBody FixedAssetTypeAttr fixedAssetTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		fixedAssetTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateFixedAssetTypeAttr command = new UpdateFixedAssetTypeAttr(fixedAssetTypeAttrToBeUpdated);

		try {
			if(((FixedAssetTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetTypeAttrId}")
	public ResponseEntity<FixedAssetTypeAttr> findById(@PathVariable String fixedAssetTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetTypeAttrId", fixedAssetTypeAttrId);
		try {

			List<FixedAssetTypeAttr> foundFixedAssetTypeAttr = findFixedAssetTypeAttrsBy(requestParams).getBody();
			if(foundFixedAssetTypeAttr.size()==1){				return successful(foundFixedAssetTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetTypeAttrId}")
	public ResponseEntity<String> deleteFixedAssetTypeAttrByIdUpdated(@PathVariable String fixedAssetTypeAttrId) throws Exception {
		DeleteFixedAssetTypeAttr command = new DeleteFixedAssetTypeAttr(fixedAssetTypeAttrId);

		try {
			if (((FixedAssetTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
