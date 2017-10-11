package com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.model.ProdCatalogInvFacility;

public class ProdCatalogInvFacilityMapper  {


	public static Map<String, Object> map(ProdCatalogInvFacility prodcataloginvfacility) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prodcataloginvfacility.getProdCatalogId() != null ){
			returnVal.put("prodCatalogId",prodcataloginvfacility.getProdCatalogId());
}

		if(prodcataloginvfacility.getFacilityId() != null ){
			returnVal.put("facilityId",prodcataloginvfacility.getFacilityId());
}

		if(prodcataloginvfacility.getFromDate() != null ){
			returnVal.put("fromDate",prodcataloginvfacility.getFromDate());
}

		if(prodcataloginvfacility.getThruDate() != null ){
			returnVal.put("thruDate",prodcataloginvfacility.getThruDate());
}

		if(prodcataloginvfacility.getSequenceNum() != null ){
			returnVal.put("sequenceNum",prodcataloginvfacility.getSequenceNum());
}

		return returnVal;
}


	public static ProdCatalogInvFacility map(Map<String, Object> fields) {

		ProdCatalogInvFacility returnVal = new ProdCatalogInvFacility();

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProdCatalogInvFacility mapstrstr(Map<String, String> fields) throws Exception {

		ProdCatalogInvFacility returnVal = new ProdCatalogInvFacility();

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProdCatalogInvFacility map(GenericValue val) {

ProdCatalogInvFacility returnVal = new ProdCatalogInvFacility();
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProdCatalogInvFacility map(HttpServletRequest request) throws Exception {

		ProdCatalogInvFacility returnVal = new ProdCatalogInvFacility();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("prodCatalogId")) {
returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
}

		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
