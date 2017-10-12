package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;

public class ProductAverageCostMapper  {


	public static Map<String, Object> map(ProductAverageCost productaveragecost) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productaveragecost.getProductAverageCostTypeId() != null ){
			returnVal.put("productAverageCostTypeId",productaveragecost.getProductAverageCostTypeId());
}

		if(productaveragecost.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",productaveragecost.getOrganizationPartyId());
}

		if(productaveragecost.getProductId() != null ){
			returnVal.put("productId",productaveragecost.getProductId());
}

		if(productaveragecost.getFacilityId() != null ){
			returnVal.put("facilityId",productaveragecost.getFacilityId());
}

		if(productaveragecost.getFromDate() != null ){
			returnVal.put("fromDate",productaveragecost.getFromDate());
}

		if(productaveragecost.getThruDate() != null ){
			returnVal.put("thruDate",productaveragecost.getThruDate());
}

		if(productaveragecost.getAverageCost() != null ){
			returnVal.put("averageCost",productaveragecost.getAverageCost());
}

		return returnVal;
}


	public static ProductAverageCost map(Map<String, Object> fields) {

		ProductAverageCost returnVal = new ProductAverageCost();

		if(fields.get("productAverageCostTypeId") != null) {
			returnVal.setProductAverageCostTypeId((String) fields.get("productAverageCostTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
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

		if(fields.get("averageCost") != null) {
			returnVal.setAverageCost((BigDecimal) fields.get("averageCost"));
}


		return returnVal;
 } 
	public static ProductAverageCost mapstrstr(Map<String, String> fields) throws Exception {

		ProductAverageCost returnVal = new ProductAverageCost();

		if(fields.get("productAverageCostTypeId") != null) {
			returnVal.setProductAverageCostTypeId((String) fields.get("productAverageCostTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
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

		if(fields.get("averageCost") != null) {
String buf;
buf = fields.get("averageCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAverageCost(bd);
}


		return returnVal;
 } 
	public static ProductAverageCost map(GenericValue val) {

ProductAverageCost returnVal = new ProductAverageCost();
		returnVal.setProductAverageCostTypeId(val.getString("productAverageCostTypeId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAverageCost(val.getBigDecimal("averageCost"));


return returnVal;

}

public static ProductAverageCost map(HttpServletRequest request) throws Exception {

		ProductAverageCost returnVal = new ProductAverageCost();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productAverageCostTypeId")) {
returnVal.setProductAverageCostTypeId(request.getParameter("productAverageCostTypeId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
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
		if(paramMap.containsKey("averageCost"))  {
String buf = request.getParameter("averageCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAverageCost(bd);
}
return returnVal;

}
}
