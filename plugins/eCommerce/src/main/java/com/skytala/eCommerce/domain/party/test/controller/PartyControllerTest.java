package com.skytala.eCommerce.domain.party.test.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.skytala.eCommerce.config.WebAppConfig;
import com.skytala.eCommerce.domain.party.PartyController;
import com.skytala.eCommerce.domain.party.model.Party;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppConfig.class })
public class PartyControllerTest extends TestCase {

	// Party Attributes
	private static final String DEFAULT_PARTY_ID = "AAAAAAAAAA";
	private static final String EXISTING_PARTY_ID_NON_DELETABLE = "EXISTING_ID";
	private static final String EXISTING_PARTY_ID_DELETABLE = "EXISTING_ID_DELABLE";
	private static final String NON_EXISTING_PARTY_ID = "NON_EXISTING_XYZAABC";

	private static final String DEFAULT_EXTERNALID = "AAAAAAAAAA";
	private static final String UPDATED_EXTERNALID = "BBBBBBBBBB";
	private static final String EXISTING_EXTERNALID = "EXISTING_EXTERNALID";

	private static final String PARTYTYPEID = "NON_EXISTING_XYZAABBC";

	ObjectMapper objectMapper;

	MockMvc mockMvc;

	@Override
	@Before
	public void setUp() throws Exception {

		objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new PartyController()).build();

		Delegator delegator = DelegatorFactory.getDelegator("test");

		Party party = new Party();
		party.setPartyId(EXISTING_PARTY_ID_NON_DELETABLE);
		party.setExternalId(EXISTING_EXTERNALID);
//		Party.setAutoCreateKeywords(true);

		try {
			delegator.createOrStore(delegator.makeValue("Party", party.mapAttributeField()));
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		party = new Party();
		party.setPartyId(EXISTING_PARTY_ID_DELETABLE);
		party.setExternalId("testPartyDel");
//		party.setAutoCreateKeywords(false);

		try {
			delegator.createOrStore(delegator.makeValue("Party", party.mapAttributeField()));
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		super.setUp();
	}

	/**
	 * Create test
	 *
	 * TestCase 1: create Party that doesn't exist
	 * 
	 * Given data: ID is not used in DB, data is valid (no FK Constraints are hurt)
	 * 
	 * Expected behavior: Party created, given ID overwritten, new ID
	 * automatically generated
	 * 
	 * Expected status code: 201 CREATED
	 * 
	 * 
	 */
	@Test
	@Transactional
	@Rollback
	public void testCreatePartyThatDoesntExist() throws JsonProcessingException, Exception {

		Party party = new Party();
		party.setPartyId(DEFAULT_PARTY_ID);
		party.setExternalId(DEFAULT_EXTERNALID);

		String contentExpected = mockMvc
				.perform(post("/partys/add").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(party)))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		Party createdParty = objectMapper.readValue(contentExpected, Party.class);

		String contentIs = mockMvc.perform(get("/partys/" + createdParty.getPartyId())).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		assertEquals(contentIs, contentExpected);
		
	}

	/**
	 * Create test
	 * 
	 * TestCase 2: create Party that exists
	 * 
	 * Given data: ID is used in DB, data is valid (no FK Constraints are hurt)
	 * 
	 * Expected behavior: Party created, given ID overwritten, new ID
	 * automatically generated (NO UPDATE!)
	 * 
	 * Expected status code: 201 CREATED
	 * 
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void testCreatePartyThatExists() throws JsonProcessingException, Exception {

		Party party = new Party();
		party.setPartyId(EXISTING_PARTY_ID_DELETABLE);
		party.setExternalId(DEFAULT_EXTERNALID);

		mockMvc.perform(post("/partys/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(party))).andExpect(status().isCreated());

		mockMvc.perform(get("/partys/" + DEFAULT_PARTY_ID)).andExpect(status().isNotFound());
			
	}

	/**
	 * create test
	 * 
	 * TestCase 3: create Party with invalid data
	 * 
	 * Given data: Data is invalid (at least FK Constraint is hurt)
	 * 
	 * Expected behavior: Party not created
	 * 
	 * Expected status code: 409 CONFLICT
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 * 
	 */
	@Test
	public void testCreatePartyWithInvalidData() throws JsonProcessingException, Exception {

		Party party = new Party();
		party.setPartyId(DEFAULT_PARTY_ID);
		party.setPartyTypeId(PARTYTYPEID);

		mockMvc.perform(post("/partys/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(party))).andExpect(status().isConflict());

		mockMvc.perform(get("/partys/" + DEFAULT_PARTY_ID)).andExpect(status().isNotFound());

	}
	/**
	 * update test
	 * 
	 * TestCase 1: update existing Party with valid data
	 * 
	 * Given data: ID is used, data is valid
	 * 
	 * Expected behavior: Party updated
	 * 
	 * Expected status code: 204 NO_CONTENT
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateExistingPartyWithValidData() throws JsonProcessingException, Exception {

		Party party = new Party();
		party.setPartyId(EXISTING_PARTY_ID_NON_DELETABLE);
		party.setExternalId(UPDATED_EXTERNALID);

		mockMvc.perform(put("/partys/" + EXISTING_PARTY_ID_NON_DELETABLE)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(party)))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/partys/" + EXISTING_PARTY_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.externalId").value(UPDATED_EXTERNALID));

	}

	/**
	 * update test
	 * 
	 * TestCase 2: update non-existing Party
	 * 
	 * Given data: ID is not used
	 * 
	 * Expected behavior: No Party updated or created
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateNonExistingPartyWithValidData() throws JsonProcessingException, Exception {
		Party party = new Party();
		party.setPartyId(NON_EXISTING_PARTY_ID);
		party.setExternalId(UPDATED_EXTERNALID);

		mockMvc.perform(put("/partys/" + NON_EXISTING_PARTY_ID).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(party))).andExpect(status().isNotFound());

		mockMvc.perform(get("/partys/" + NON_EXISTING_PARTY_ID)).andExpect(status().isNotFound());

	}

	/**
	 * update test
	 * 
	 * TestCase 3: update existing Party with invalid data
	 * 
	 * Given data: ID is used, Data is invalid (at least FK Constraint is hurt)
	 * 
	 * Expected behavior: Party not updated
	 * 
	 * Expected status code: 409 CONFLICT
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateExistingPartyWithInvalidData() throws JsonProcessingException, Exception {
		Party party = new Party();
		party.setPartyId(EXISTING_PARTY_ID_NON_DELETABLE);
		party.setExternalId(UPDATED_EXTERNALID);
		party.setPartyTypeId(PARTYTYPEID);

		mockMvc.perform(put("/partys/" + EXISTING_PARTY_ID_NON_DELETABLE)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(party)))
				.andExpect(status().isConflict());

		mockMvc.perform(get("/partys/" + EXISTING_PARTY_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.externalId").value(EXISTING_EXTERNALID));
	}

	/**
	 * update test
	 * 
	 * TestCase 4: update non-existing Party with invalid data
	 * 
	 * Given data: ID is not used, Data is invalid (at least one FK Constraint is
	 * hurt)
	 * 
	 * Expected behavior: No Party updated or created
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void testUpdateNonExistingPartyWithInvalidData() throws JsonProcessingException, Exception {
		Party party = new Party();
		party.setPartyId(NON_EXISTING_PARTY_ID);
		party.setExternalId(UPDATED_EXTERNALID);
		party.setPartyTypeId(PARTYTYPEID);

		mockMvc.perform(put("/partys/" + NON_EXISTING_PARTY_ID).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(party))).andExpect(status().isNotFound());

		mockMvc.perform(get("/partys/" + NON_EXISTING_PARTY_ID)).andExpect(status().isNotFound());

	}
	/**
	 * get test
	 *
	 * TestCase 1: get existing Party
	 * 
	 * Given data: ID is used
	 * 
	 * Expected behavior: Gets data of Party with given ID from DB
	 * 
	 * Expected status code: 200 OK
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testGetExistingParty() throws Exception {
		mockMvc.perform(get("/partys/" + EXISTING_PARTY_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.partyId", containsString(EXISTING_PARTY_ID_NON_DELETABLE)));

	}

	/**
	 * get test
	 * 
	 * TestCase 2: get non-existing Party
	 * 
	 * Given data: ID not used
	 * 
	 * Expected behavior: No data gotten from DB
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetNonExistingParty() throws Exception {
		mockMvc.perform(get("/partys/" + NON_EXISTING_PARTY_ID)).andExpect(status().isNotFound());

	}

	/**
	 * delete test
	 * 
	 * TestCase 1: delete existing deletable Party
	 * 
	 * Given data: ID is used, tuple deletable
	 * 
	 * Expected behavior: tuple deleted
	 * 
	 * Expected status code: 204 NO_CONTENT
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testDeleteExistingDeletableParty() throws Exception {
		String result = mockMvc.perform(delete("/partys/" + EXISTING_PARTY_ID_DELETABLE))
				.andExpect(status().isNoContent()).andReturn().getResponse().getContentAsString();

		assertEquals(result, "");

		mockMvc.perform(get("/partys/" + EXISTING_PARTY_ID_DELETABLE)).andExpect(status().isNotFound());

	}

	/**
	 * delete test
	 * 
	 * TestCase 2: delete existing non-deletable Party
	 * 
	 * Given data: ID is used, tuple not deletable (FK Constraint)
	 * 
	 * Expected behavior: tuple not deleted
	 * 
	 * Expected status code: 409 CONFLICT
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testDeleteExistingNonDeletableParty() throws Exception {

		mockMvc.perform(delete("/partys/" + EXISTING_PARTY_ID_NON_DELETABLE)).andExpect(status().isConflict())
				.andExpect(jsonPath("$").value("Party could not be deleted"));

		mockMvc.perform(get("/partys/" + EXISTING_PARTY_ID_NON_DELETABLE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.partyId").value(EXISTING_PARTY_ID_NON_DELETABLE));

	}

	/**
	 * delete test
	 * 
	 * TestCase 3: delete non-existing Party
	 * 
	 * Given data: ID is not used
	 * 
	 * Expected behavior: no Party deleted
	 * 
	 * Expected status code: 404 NOT_FOUND
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	@Test
	public void testDeleteNonExistingParty() throws Exception {

		int countPartysBeforeDelete = 0;

		String responseStr = mockMvc.perform(get("/partys/find")).andReturn().getResponse().getContentAsString();

		List<?> partys = JsonPath.read(responseStr, "$");
		countPartysBeforeDelete = partys.size();

		mockMvc.perform(delete("/partys/" + NON_EXISTING_PARTY_ID)).andExpect(status().isNotFound());

		mockMvc.perform(get("/partys/find")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(countPartysBeforeDelete)));

	}
}
