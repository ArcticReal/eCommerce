package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;

public class ZipSalesRuleLookupMapper  {


	public static Map<String, Object> map(ZipSalesRuleLookup zipsalesrulelookup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(zipsalesrulelookup.getStateCode() != null ){
			returnVal.put("stateCode",zipsalesrulelookup.getStateCode());
}

		if(zipsalesrulelookup.getCity() != null ){
			returnVal.put("city",zipsalesrulelookup.getCity());
}

		if(zipsalesrulelookup.getCounty() != null ){
			returnVal.put("county",zipsalesrulelookup.getCounty());
}

		if(zipsalesrulelookup.getFromDate() != null ){
			returnVal.put("fromDate",zipsalesrulelookup.getFromDate());
}

		if(zipsalesrulelookup.getIdCode() != null ){
			returnVal.put("idCode",zipsalesrulelookup.getIdCode());
}

		if(zipsalesrulelookup.getTaxable() != null ){
			returnVal.put("taxable",zipsalesrulelookup.getTaxable());
}

		if(zipsalesrulelookup.getShipCond() != null ){
			returnVal.put("shipCond",zipsalesrulelookup.getShipCond());
}

		return returnVal;
}


	public static ZipSalesRuleLookup map(Map<String, Object> fields) {

		ZipSalesRuleLookup returnVal = new ZipSalesRuleLookup();

		if(fields.get("stateCode") != null) {
			returnVal.setStateCode((String) fields.get("stateCode"));
}

		if(fields.get("city") != null) {
			returnVal.setCity((String) fields.get("city"));
}

		if(fields.get("county") != null) {
			returnVal.setCounty((String) fields.get("county"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("idCode") != null) {
			returnVal.setIdCode((String) fields.get("idCode"));
}

		if(fields.get("taxable") != null) {
			returnVal.setTaxable((String) fields.get("taxable"));
}

		if(fields.get("shipCond") != null) {
			returnVal.setShipCond((String) fields.get("shipCond"));
}


		return returnVal;
 } 
	public static ZipSalesRuleLookup mapstrstr(Map<String, String> fields) throws Exception {

		ZipSalesRuleLookup returnVal = new ZipSalesRuleLookup();

		if(fields.get("stateCode") != null) {
			returnVal.setStateCode((String) fields.get("stateCode"));
}

		if(fields.get("city") != null) {
			returnVal.setCity((String) fields.get("city"));
}

		if(fields.get("county") != null) {
			returnVal.setCounty((String) fields.get("county"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("idCode") != null) {
			returnVal.setIdCode((String) fields.get("idCode"));
}

		if(fields.get("taxable") != null) {
			returnVal.setTaxable((String) fields.get("taxable"));
}

		if(fields.get("shipCond") != null) {
			returnVal.setShipCond((String) fields.get("shipCond"));
}


		return returnVal;
 } 
	public static ZipSalesRuleLookup map(GenericValue val) {

ZipSalesRuleLookup returnVal = new ZipSalesRuleLookup();
		returnVal.setStateCode(val.getString("stateCode"));
		returnVal.setCity(val.getString("city"));
		returnVal.setCounty(val.getString("county"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setIdCode(val.getString("idCode"));
		returnVal.setTaxable(val.getString("taxable"));
		returnVal.setShipCond(val.getString("shipCond"));


return returnVal;

}

public static ZipSalesRuleLookup map(HttpServletRequest request) throws Exception {

		ZipSalesRuleLookup returnVal = new ZipSalesRuleLookup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("stateCode")) {
returnVal.setStateCode(request.getParameter("stateCode"));
}

		if(paramMap.containsKey("city"))  {
returnVal.setCity(request.getParameter("city"));
}
		if(paramMap.containsKey("county"))  {
returnVal.setCounty(request.getParameter("county"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("idCode"))  {
returnVal.setIdCode(request.getParameter("idCode"));
}
		if(paramMap.containsKey("taxable"))  {
returnVal.setTaxable(request.getParameter("taxable"));
}
		if(paramMap.containsKey("shipCond"))  {
returnVal.setShipCond(request.getParameter("shipCond"));
}
return returnVal;

}
}
