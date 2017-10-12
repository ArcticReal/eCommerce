package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;

public class TaxAuthorityMapper  {


	public static Map<String, Object> map(TaxAuthority taxauthority) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(taxauthority.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",taxauthority.getTaxAuthGeoId());
}

		if(taxauthority.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",taxauthority.getTaxAuthPartyId());
}

		if(taxauthority.getRequireTaxIdForExemption() != null ){
			returnVal.put("requireTaxIdForExemption",taxauthority.getRequireTaxIdForExemption());
}

		if(taxauthority.getTaxIdFormatPattern() != null ){
			returnVal.put("taxIdFormatPattern",taxauthority.getTaxIdFormatPattern());
}

		if(taxauthority.getIncludeTaxInPrice() != null ){
			returnVal.put("includeTaxInPrice",taxauthority.getIncludeTaxInPrice());
}

		return returnVal;
}


	public static TaxAuthority map(Map<String, Object> fields) {

		TaxAuthority returnVal = new TaxAuthority();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("requireTaxIdForExemption") != null) {
			returnVal.setRequireTaxIdForExemption((boolean) fields.get("requireTaxIdForExemption"));
}

		if(fields.get("taxIdFormatPattern") != null) {
			returnVal.setTaxIdFormatPattern((String) fields.get("taxIdFormatPattern"));
}

		if(fields.get("includeTaxInPrice") != null) {
			returnVal.setIncludeTaxInPrice((boolean) fields.get("includeTaxInPrice"));
}


		return returnVal;
 } 
	public static TaxAuthority mapstrstr(Map<String, String> fields) throws Exception {

		TaxAuthority returnVal = new TaxAuthority();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("requireTaxIdForExemption") != null) {
String buf;
buf = fields.get("requireTaxIdForExemption");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireTaxIdForExemption(ibuf);
}

		if(fields.get("taxIdFormatPattern") != null) {
			returnVal.setTaxIdFormatPattern((String) fields.get("taxIdFormatPattern"));
}

		if(fields.get("includeTaxInPrice") != null) {
String buf;
buf = fields.get("includeTaxInPrice");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeTaxInPrice(ibuf);
}


		return returnVal;
 } 
	public static TaxAuthority map(GenericValue val) {

TaxAuthority returnVal = new TaxAuthority();
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setRequireTaxIdForExemption(val.getBoolean("requireTaxIdForExemption"));
		returnVal.setTaxIdFormatPattern(val.getString("taxIdFormatPattern"));
		returnVal.setIncludeTaxInPrice(val.getBoolean("includeTaxInPrice"));


return returnVal;

}

public static TaxAuthority map(HttpServletRequest request) throws Exception {

		TaxAuthority returnVal = new TaxAuthority();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("taxAuthGeoId")) {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}

		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("requireTaxIdForExemption"))  {
String buf = request.getParameter("requireTaxIdForExemption");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireTaxIdForExemption(ibuf);
}
		if(paramMap.containsKey("taxIdFormatPattern"))  {
returnVal.setTaxIdFormatPattern(request.getParameter("taxIdFormatPattern"));
}
		if(paramMap.containsKey("includeTaxInPrice"))  {
String buf = request.getParameter("includeTaxInPrice");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIncludeTaxInPrice(ibuf);
}
return returnVal;

}
}
