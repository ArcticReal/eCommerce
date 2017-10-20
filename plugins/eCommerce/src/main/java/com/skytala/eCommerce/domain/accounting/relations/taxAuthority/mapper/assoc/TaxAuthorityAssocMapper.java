package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.assoc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assoc.TaxAuthorityAssoc;

public class TaxAuthorityAssocMapper  {


	public static Map<String, Object> map(TaxAuthorityAssoc taxauthorityassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(taxauthorityassoc.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",taxauthorityassoc.getTaxAuthGeoId());
}

		if(taxauthorityassoc.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",taxauthorityassoc.getTaxAuthPartyId());
}

		if(taxauthorityassoc.getToTaxAuthGeoId() != null ){
			returnVal.put("toTaxAuthGeoId",taxauthorityassoc.getToTaxAuthGeoId());
}

		if(taxauthorityassoc.getToTaxAuthPartyId() != null ){
			returnVal.put("toTaxAuthPartyId",taxauthorityassoc.getToTaxAuthPartyId());
}

		if(taxauthorityassoc.getFromDate() != null ){
			returnVal.put("fromDate",taxauthorityassoc.getFromDate());
}

		if(taxauthorityassoc.getThruDate() != null ){
			returnVal.put("thruDate",taxauthorityassoc.getThruDate());
}

		if(taxauthorityassoc.getTaxAuthorityAssocTypeId() != null ){
			returnVal.put("taxAuthorityAssocTypeId",taxauthorityassoc.getTaxAuthorityAssocTypeId());
}

		return returnVal;
}


	public static TaxAuthorityAssoc map(Map<String, Object> fields) {

		TaxAuthorityAssoc returnVal = new TaxAuthorityAssoc();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("toTaxAuthGeoId") != null) {
			returnVal.setToTaxAuthGeoId((String) fields.get("toTaxAuthGeoId"));
}

		if(fields.get("toTaxAuthPartyId") != null) {
			returnVal.setToTaxAuthPartyId((String) fields.get("toTaxAuthPartyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("taxAuthorityAssocTypeId") != null) {
			returnVal.setTaxAuthorityAssocTypeId((String) fields.get("taxAuthorityAssocTypeId"));
}


		return returnVal;
 } 
	public static TaxAuthorityAssoc mapstrstr(Map<String, String> fields) throws Exception {

		TaxAuthorityAssoc returnVal = new TaxAuthorityAssoc();

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("toTaxAuthGeoId") != null) {
			returnVal.setToTaxAuthGeoId((String) fields.get("toTaxAuthGeoId"));
}

		if(fields.get("toTaxAuthPartyId") != null) {
			returnVal.setToTaxAuthPartyId((String) fields.get("toTaxAuthPartyId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("taxAuthorityAssocTypeId") != null) {
			returnVal.setTaxAuthorityAssocTypeId((String) fields.get("taxAuthorityAssocTypeId"));
}


		return returnVal;
 } 
	public static TaxAuthorityAssoc map(GenericValue val) {

TaxAuthorityAssoc returnVal = new TaxAuthorityAssoc();
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setToTaxAuthGeoId(val.getString("toTaxAuthGeoId"));
		returnVal.setToTaxAuthPartyId(val.getString("toTaxAuthPartyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setTaxAuthorityAssocTypeId(val.getString("taxAuthorityAssocTypeId"));


return returnVal;

}

public static TaxAuthorityAssoc map(HttpServletRequest request) throws Exception {

		TaxAuthorityAssoc returnVal = new TaxAuthorityAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("taxAuthGeoId")) {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}

		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("toTaxAuthGeoId"))  {
returnVal.setToTaxAuthGeoId(request.getParameter("toTaxAuthGeoId"));
}
		if(paramMap.containsKey("toTaxAuthPartyId"))  {
returnVal.setToTaxAuthPartyId(request.getParameter("toTaxAuthPartyId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("taxAuthorityAssocTypeId"))  {
returnVal.setTaxAuthorityAssocTypeId(request.getParameter("taxAuthorityAssocTypeId"));
}
return returnVal;

}
}
