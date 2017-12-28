package com.skytala.eCommerce.domain.humanres.relations.skillType;

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
import com.skytala.eCommerce.domain.humanres.relations.skillType.command.AddSkillType;
import com.skytala.eCommerce.domain.humanres.relations.skillType.command.DeleteSkillType;
import com.skytala.eCommerce.domain.humanres.relations.skillType.command.UpdateSkillType;
import com.skytala.eCommerce.domain.humanres.relations.skillType.event.SkillTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.skillType.event.SkillTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.skillType.event.SkillTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.skillType.event.SkillTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.skillType.mapper.SkillTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.skillType.model.SkillType;
import com.skytala.eCommerce.domain.humanres.relations.skillType.query.FindSkillTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/skillTypes")
public class SkillTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SkillTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SkillType
	 * @return a List with the SkillTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SkillType>> findSkillTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSkillTypesBy query = new FindSkillTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SkillType> skillTypes =((SkillTypeFound) Scheduler.execute(query).data()).getSkillTypes();

		return ResponseEntity.ok().body(skillTypes);

	}

	/**
	 * creates a new SkillType entry in the ofbiz database
	 * 
	 * @param skillTypeToBeAdded
	 *            the SkillType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SkillType> createSkillType(@RequestBody SkillType skillTypeToBeAdded) throws Exception {

		AddSkillType command = new AddSkillType(skillTypeToBeAdded);
		SkillType skillType = ((SkillTypeAdded) Scheduler.execute(command).data()).getAddedSkillType();
		
		if (skillType != null) 
			return successful(skillType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SkillType with the specific Id
	 * 
	 * @param skillTypeToBeUpdated
	 *            the SkillType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{skillTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSkillType(@RequestBody SkillType skillTypeToBeUpdated,
			@PathVariable String skillTypeId) throws Exception {

		skillTypeToBeUpdated.setSkillTypeId(skillTypeId);

		UpdateSkillType command = new UpdateSkillType(skillTypeToBeUpdated);

		try {
			if(((SkillTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{skillTypeId}")
	public ResponseEntity<SkillType> findById(@PathVariable String skillTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("skillTypeId", skillTypeId);
		try {

			List<SkillType> foundSkillType = findSkillTypesBy(requestParams).getBody();
			if(foundSkillType.size()==1){				return successful(foundSkillType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{skillTypeId}")
	public ResponseEntity<String> deleteSkillTypeByIdUpdated(@PathVariable String skillTypeId) throws Exception {
		DeleteSkillType command = new DeleteSkillType(skillTypeId);

		try {
			if (((SkillTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
