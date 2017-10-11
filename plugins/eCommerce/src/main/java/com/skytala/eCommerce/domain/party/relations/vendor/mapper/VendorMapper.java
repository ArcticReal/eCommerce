package com.skytala.eCommerce.domain.party.relations.vendor.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;

public class VendorMapper  {


	public static Map<String, Object> map(Vendor vendor) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(vendor.getPartyId() != null ){
			returnVal.put("partyId",vendor.getPartyId());
}

		if(vendor.getManifestCompanyName() != null ){
			returnVal.put("manifestCompanyName",vendor.getManifestCompanyName());
}

		if(vendor.getManifestCompanyTitle() != null ){
			returnVal.put("manifestCompanyTitle",vendor.getManifestCompanyTitle());
}

		if(vendor.getManifestLogoUrl() != null ){
			returnVal.put("manifestLogoUrl",vendor.getManifestLogoUrl());
}

		if(vendor.getManifestPolicies() != null ){
			returnVal.put("manifestPolicies",vendor.getManifestPolicies());
}

		return returnVal;
}


	public static Vendor map(Map<String, Object> fields) {

		Vendor returnVal = new Vendor();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("manifestCompanyName") != null) {
			returnVal.setManifestCompanyName((String) fields.get("manifestCompanyName"));
}

		if(fields.get("manifestCompanyTitle") != null) {
			returnVal.setManifestCompanyTitle((String) fields.get("manifestCompanyTitle"));
}

		if(fields.get("manifestLogoUrl") != null) {
			returnVal.setManifestLogoUrl((String) fields.get("manifestLogoUrl"));
}

		if(fields.get("manifestPolicies") != null) {
			returnVal.setManifestPolicies((String) fields.get("manifestPolicies"));
}


		return returnVal;
 } 
	public static Vendor mapstrstr(Map<String, String> fields) throws Exception {

		Vendor returnVal = new Vendor();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("manifestCompanyName") != null) {
			returnVal.setManifestCompanyName((String) fields.get("manifestCompanyName"));
}

		if(fields.get("manifestCompanyTitle") != null) {
			returnVal.setManifestCompanyTitle((String) fields.get("manifestCompanyTitle"));
}

		if(fields.get("manifestLogoUrl") != null) {
			returnVal.setManifestLogoUrl((String) fields.get("manifestLogoUrl"));
}

		if(fields.get("manifestPolicies") != null) {
			returnVal.setManifestPolicies((String) fields.get("manifestPolicies"));
}


		return returnVal;
 } 
	public static Vendor map(GenericValue val) {

Vendor returnVal = new Vendor();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setManifestCompanyName(val.getString("manifestCompanyName"));
		returnVal.setManifestCompanyTitle(val.getString("manifestCompanyTitle"));
		returnVal.setManifestLogoUrl(val.getString("manifestLogoUrl"));
		returnVal.setManifestPolicies(val.getString("manifestPolicies"));


return returnVal;

}

public static Vendor map(HttpServletRequest request) throws Exception {

		Vendor returnVal = new Vendor();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("manifestCompanyName"))  {
returnVal.setManifestCompanyName(request.getParameter("manifestCompanyName"));
}
		if(paramMap.containsKey("manifestCompanyTitle"))  {
returnVal.setManifestCompanyTitle(request.getParameter("manifestCompanyTitle"));
}
		if(paramMap.containsKey("manifestLogoUrl"))  {
returnVal.setManifestLogoUrl(request.getParameter("manifestLogoUrl"));
}
		if(paramMap.containsKey("manifestPolicies"))  {
returnVal.setManifestPolicies(request.getParameter("manifestPolicies"));
}
return returnVal;

}
}
