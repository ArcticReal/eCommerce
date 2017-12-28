package com.skytala.eCommerce.domain.party.relations.affiliate;

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
import com.skytala.eCommerce.domain.party.relations.affiliate.command.AddAffiliate;
import com.skytala.eCommerce.domain.party.relations.affiliate.command.DeleteAffiliate;
import com.skytala.eCommerce.domain.party.relations.affiliate.command.UpdateAffiliate;
import com.skytala.eCommerce.domain.party.relations.affiliate.event.AffiliateAdded;
import com.skytala.eCommerce.domain.party.relations.affiliate.event.AffiliateDeleted;
import com.skytala.eCommerce.domain.party.relations.affiliate.event.AffiliateFound;
import com.skytala.eCommerce.domain.party.relations.affiliate.event.AffiliateUpdated;
import com.skytala.eCommerce.domain.party.relations.affiliate.mapper.AffiliateMapper;
import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;
import com.skytala.eCommerce.domain.party.relations.affiliate.query.FindAffiliatesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/affiliates")
public class AffiliateController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AffiliateController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Affiliate
	 * @return a List with the Affiliates
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Affiliate>> findAffiliatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAffiliatesBy query = new FindAffiliatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Affiliate> affiliates =((AffiliateFound) Scheduler.execute(query).data()).getAffiliates();

		return ResponseEntity.ok().body(affiliates);

	}

	/**
	 * creates a new Affiliate entry in the ofbiz database
	 * 
	 * @param affiliateToBeAdded
	 *            the Affiliate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Affiliate> createAffiliate(@RequestBody Affiliate affiliateToBeAdded) throws Exception {

		AddAffiliate command = new AddAffiliate(affiliateToBeAdded);
		Affiliate affiliate = ((AffiliateAdded) Scheduler.execute(command).data()).getAddedAffiliate();
		
		if (affiliate != null) 
			return successful(affiliate);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Affiliate with the specific Id
	 * 
	 * @param affiliateToBeUpdated
	 *            the Affiliate thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAffiliate(@RequestBody Affiliate affiliateToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		affiliateToBeUpdated.setnull(null);

		UpdateAffiliate command = new UpdateAffiliate(affiliateToBeUpdated);

		try {
			if(((AffiliateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{affiliateId}")
	public ResponseEntity<Affiliate> findById(@PathVariable String affiliateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("affiliateId", affiliateId);
		try {

			List<Affiliate> foundAffiliate = findAffiliatesBy(requestParams).getBody();
			if(foundAffiliate.size()==1){				return successful(foundAffiliate.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{affiliateId}")
	public ResponseEntity<String> deleteAffiliateByIdUpdated(@PathVariable String affiliateId) throws Exception {
		DeleteAffiliate command = new DeleteAffiliate(affiliateId);

		try {
			if (((AffiliateDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
