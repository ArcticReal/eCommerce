package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.model.TaxAuthorityGlAccount;

public class TaxAuthorityGlAccountMapper  {


	public static Map<String, Object> map(TaxAuthorityGlAccount taxauthorityglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(taxauthorityglaccount.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",taxauthorityglaccount.getTaxAuthGeoId());
}

		if(taxauthorityglaccount.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",taxauthorityglaccount.getTaxAuthPartyId());
}

		if(taxauthorityglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",taxauthorityglaccount.getOrganizationPartyId());
}

		if(taxauthorityglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",taxauthorityglaccount.getGlAccountId());
}

		return returnVal;
}


	public static TaxAuthorityGlAccount map(Map<String, Object> fields) {

		TaxAuthorityGlAccount returnVal = new TaxAuthorityGlAccount();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static TaxAuthorityGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		TaxAuthorityGlAccount returnVal = new TaxAuthorityGlAccount();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static TaxAuthorityGlAccount map(GenericValue val) {

TaxAuthorityGlAccount returnVal = new TaxAuthorityGlAccount();
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static TaxAuthorityGlAccount map(HttpServletRequest request) throws Exception {

		TaxAuthorityGlAccount returnVal = new TaxAuthorityGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("taxAuthGeoId")) {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}

		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
return returnVal;

}
}
