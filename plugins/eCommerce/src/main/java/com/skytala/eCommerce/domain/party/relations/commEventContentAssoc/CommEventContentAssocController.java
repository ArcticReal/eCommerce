package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc;

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
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.command.AddCommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.command.DeleteCommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.command.UpdateCommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocAdded;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocDeleted;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocFound;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocUpdated;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.mapper.CommEventContentAssocMapper;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.query.FindCommEventContentAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/commEventContentAssocs")
public class CommEventContentAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommEventContentAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommEventContentAssoc
	 * @return a List with the CommEventContentAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CommEventContentAssoc>> findCommEventContentAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommEventContentAssocsBy query = new FindCommEventContentAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommEventContentAssoc> commEventContentAssocs =((CommEventContentAssocFound) Scheduler.execute(query).data()).getCommEventContentAssocs();

		return ResponseEntity.ok().body(commEventContentAssocs);

	}

	/**
	 * creates a new CommEventContentAssoc entry in the ofbiz database
	 * 
	 * @param commEventContentAssocToBeAdded
	 *            the CommEventContentAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CommEventContentAssoc> createCommEventContentAssoc(@RequestBody CommEventContentAssoc commEventContentAssocToBeAdded) throws Exception {

		AddCommEventContentAssoc command = new AddCommEventContentAssoc(commEventContentAssocToBeAdded);
		CommEventContentAssoc commEventContentAssoc = ((CommEventContentAssocAdded) Scheduler.execute(command).data()).getAddedCommEventContentAssoc();
		
		if (commEventContentAssoc != null) 
			return successful(commEventContentAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CommEventContentAssoc with the specific Id
	 * 
	 * @param commEventContentAssocToBeUpdated
	 *            the CommEventContentAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCommEventContentAssoc(@RequestBody CommEventContentAssoc commEventContentAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		commEventContentAssocToBeUpdated.setnull(null);

		UpdateCommEventContentAssoc command = new UpdateCommEventContentAssoc(commEventContentAssocToBeUpdated);

		try {
			if(((CommEventContentAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{commEventContentAssocId}")
	public ResponseEntity<CommEventContentAssoc> findById(@PathVariable String commEventContentAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("commEventContentAssocId", commEventContentAssocId);
		try {

			List<CommEventContentAssoc> foundCommEventContentAssoc = findCommEventContentAssocsBy(requestParams).getBody();
			if(foundCommEventContentAssoc.size()==1){				return successful(foundCommEventContentAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{commEventContentAssocId}")
	public ResponseEntity<String> deleteCommEventContentAssocByIdUpdated(@PathVariable String commEventContentAssocId) throws Exception {
		DeleteCommEventContentAssoc command = new DeleteCommEventContentAssoc(commEventContentAssocId);

		try {
			if (((CommEventContentAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
