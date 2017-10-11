package com.skytala.eCommerce.domain.product.relations.reorderGuideline.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.model.ReorderGuideline;

public class ReorderGuidelineMapper  {


	public static Map<String, Object> map(ReorderGuideline reorderguideline) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(reorderguideline.getReorderGuidelineId() != null ){
			returnVal.put("reorderGuidelineId",reorderguideline.getReorderGuidelineId());
}

		if(reorderguideline.getProductId() != null ){
			returnVal.put("productId",reorderguideline.getProductId());
}

		if(reorderguideline.getPartyId() != null ){
			returnVal.put("partyId",reorderguideline.getPartyId());
}

		if(reorderguideline.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",reorderguideline.getRoleTypeId());
}

		if(reorderguideline.getFacilityId() != null ){
			returnVal.put("facilityId",reorderguideline.getFacilityId());
}

		if(reorderguideline.getGeoId() != null ){
			returnVal.put("geoId",reorderguideline.getGeoId());
}

		if(reorderguideline.getFromDate() != null ){
			returnVal.put("fromDate",reorderguideline.getFromDate());
}

		if(reorderguideline.getThruDate() != null ){
			returnVal.put("thruDate",reorderguideline.getThruDate());
}

		if(reorderguideline.getReorderQuantity() != null ){
			returnVal.put("reorderQuantity",reorderguideline.getReorderQuantity());
}

		if(reorderguideline.getReorderLevel() != null ){
			returnVal.put("reorderLevel",reorderguideline.getReorderLevel());
}

		return returnVal;
}


	public static ReorderGuideline map(Map<String, Object> fields) {

		ReorderGuideline returnVal = new ReorderGuideline();

		if(fields.get("reorderGuidelineId") != null) {
			returnVal.setReorderGuidelineId((String) fields.get("reorderGuidelineId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("reorderQuantity") != null) {
			returnVal.setReorderQuantity((BigDecimal) fields.get("reorderQuantity"));
}

		if(fields.get("reorderLevel") != null) {
			returnVal.setReorderLevel((BigDecimal) fields.get("reorderLevel"));
}


		return returnVal;
 } 
	public static ReorderGuideline mapstrstr(Map<String, String> fields) throws Exception {

		ReorderGuideline returnVal = new ReorderGuideline();

		if(fields.get("reorderGuidelineId") != null) {
			returnVal.setReorderGuidelineId((String) fields.get("reorderGuidelineId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
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

		if(fields.get("reorderQuantity") != null) {
String buf;
buf = fields.get("reorderQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReorderQuantity(bd);
}

		if(fields.get("reorderLevel") != null) {
String buf;
buf = fields.get("reorderLevel");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReorderLevel(bd);
}


		return returnVal;
 } 
	public static ReorderGuideline map(GenericValue val) {

ReorderGuideline returnVal = new ReorderGuideline();
		returnVal.setReorderGuidelineId(val.getString("reorderGuidelineId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setGeoId(val.getString("geoId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setReorderQuantity(val.getBigDecimal("reorderQuantity"));
		returnVal.setReorderLevel(val.getBigDecimal("reorderLevel"));


return returnVal;

}

public static ReorderGuideline map(HttpServletRequest request) throws Exception {

		ReorderGuideline returnVal = new ReorderGuideline();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("reorderGuidelineId")) {
returnVal.setReorderGuidelineId(request.getParameter("reorderGuidelineId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
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
		if(paramMap.containsKey("reorderQuantity"))  {
String buf = request.getParameter("reorderQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReorderQuantity(bd);
}
		if(paramMap.containsKey("reorderLevel"))  {
String buf = request.getParameter("reorderLevel");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReorderLevel(bd);
}
return returnVal;

}
}
