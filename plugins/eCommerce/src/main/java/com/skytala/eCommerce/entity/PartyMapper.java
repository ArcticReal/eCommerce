package com.skytala.eCommerce.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

public class PartyMapper {

	public static Map<String, Object> map(Party party) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (party.getPartyId() != null) {
			returnVal.put("partyId", party.getPartyId());
		}

		if (party.getPartyTypeId() != null) {
			returnVal.put("partyTypeId", party.getPartyTypeId());
		}

		if (party.getExternalId() != null) {
			returnVal.put("externalId", party.getExternalId());
		}

		if (party.getPreferredCurrencyUomId() != null) {
			returnVal.put("preferredCurrencyUomId", party.getPreferredCurrencyUomId());
		}

		if (party.getDescription() != null) {
			returnVal.put("description", party.getDescription());
		}

		if (party.getStatusId() != null) {
			returnVal.put("statusId", party.getStatusId());
		}

		if (party.getCreatedDate() != null) {
			returnVal.put("createdDate", party.getCreatedDate());
		}

		if (party.getCreatedByUserLogin() != null) {
			returnVal.put("createdByUserLogin", party.getCreatedByUserLogin());
		}

		if (party.getLastModifiedDate() != null) {
			returnVal.put("lastModifiedDate", party.getLastModifiedDate());
		}

		if (party.getLastModifiedByUserLogin() != null) {
			returnVal.put("lastModifiedByUserLogin", party.getLastModifiedByUserLogin());
		}

		if (party.getDataSourceId() != null) {
			returnVal.put("dataSourceId", party.getDataSourceId());
		}

		if (party.getIsUnread() != null) {
			returnVal.put("isUnread", party.getIsUnread());
		}

		return returnVal;
	}

	public static Party map(Map<String, Object> fields) {

		Party returnVal = new Party();

		if (fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
		}

		if (fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
		}

		if (fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
		}

		if (fields.get("preferredCurrencyUomId") != null) {
			returnVal.setPreferredCurrencyUomId((String) fields.get("preferredCurrencyUomId"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
		}

		if (fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
		}

		if (fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
		}

		if (fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
		}

		if (fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
		}

		if (fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
		}

		if (fields.get("isUnread") != null) {
			returnVal.setIsUnread((boolean) fields.get("isUnread"));
		}

		return returnVal;
	}

	public static Party mapstrstr(Map<String, String> fields) throws Exception {

		Party returnVal = new Party();

		if (fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
		}

		if (fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
		}

		if (fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
		}

		if (fields.get("preferredCurrencyUomId") != null) {
			returnVal.setPreferredCurrencyUomId((String) fields.get("preferredCurrencyUomId"));
		}

		if (fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
		}

		if (fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
		}

		if (fields.get("createdDate") != null) {
			String buf = fields.get("createdDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
		}

		if (fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
		}

		if (fields.get("lastModifiedDate") != null) {
			String buf = fields.get("lastModifiedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
		}

		if (fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
		}

		if (fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
		}

		if (fields.get("isUnread") != null) {
			String buf;
			buf = fields.get("isUnread");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsUnread(ibuf);
		}

		return returnVal;
	}

	public static Party map(GenericValue val) {

		Party returnVal = new Party();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setPartyTypeId(val.getString("partyTypeId"));
		returnVal.setExternalId(val.getString("externalId"));
		returnVal.setPreferredCurrencyUomId(val.getString("preferredCurrencyUomId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));
		returnVal.setIsUnread(val.getBoolean("isUnread"));

		return returnVal;

	}

	public static Party map(HttpServletRequest request) throws Exception {

		Party returnVal = new Party();

		Map<String, String[]> paramMap = request.getParameterMap();

		if (!paramMap.containsKey("partyId")) {
			throw new Exception("Error! Id required");
		} else {
			returnVal.setPartyId(request.getParameter("productId"));
		}

		if (paramMap.containsKey("partyTypeId")) {
			returnVal.setPartyTypeId(request.getParameter("partyTypeId"));
		}
		if (paramMap.containsKey("externalId")) {
			returnVal.setExternalId(request.getParameter("externalId"));
		}
		if (paramMap.containsKey("preferredCurrencyUomId")) {
			returnVal.setPreferredCurrencyUomId(request.getParameter("preferredCurrencyUomId"));
		}
		if (paramMap.containsKey("description")) {
			returnVal.setDescription(request.getParameter("description"));
		}
		if (paramMap.containsKey("statusId")) {
			returnVal.setStatusId(request.getParameter("statusId"));
		}
		if (paramMap.containsKey("createdDate")) {
			String buf = request.getParameter("createdDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
		}
		if (paramMap.containsKey("createdByUserLogin")) {
			returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
		}
		if (paramMap.containsKey("lastModifiedDate")) {
			String buf = request.getParameter("lastModifiedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
		}
		if (paramMap.containsKey("lastModifiedByUserLogin")) {
			returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
		}
		if (paramMap.containsKey("dataSourceId")) {
			returnVal.setDataSourceId(request.getParameter("dataSourceId"));
		}
		if (paramMap.containsKey("isUnread")) {
			String buf = request.getParameter("isUnread");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsUnread(ibuf);
		}
		return returnVal;

	}
}
