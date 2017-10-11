package com.skytala.eCommerce.domain.product.relations.productStoreFacility.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.model.ProductStoreFacility;

public class ProductStoreFacilityMapper  {


	public static Map<String, Object> map(ProductStoreFacility productstorefacility) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorefacility.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorefacility.getProductStoreId());
}

		if(productstorefacility.getFacilityId() != null ){
			returnVal.put("facilityId",productstorefacility.getFacilityId());
}

		if(productstorefacility.getFromDate() != null ){
			returnVal.put("fromDate",productstorefacility.getFromDate());
}

		if(productstorefacility.getThruDate() != null ){
			returnVal.put("thruDate",productstorefacility.getThruDate());
}

		if(productstorefacility.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productstorefacility.getSequenceNum());
}

		return returnVal;
}


	public static ProductStoreFacility map(Map<String, Object> fields) {

		ProductStoreFacility returnVal = new ProductStoreFacility();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
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
	public static ProductStoreFacility mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreFacility returnVal = new ProductStoreFacility();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
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
	public static ProductStoreFacility map(GenericValue val) {

ProductStoreFacility returnVal = new ProductStoreFacility();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductStoreFacility map(HttpServletRequest request) throws Exception {

		ProductStoreFacility returnVal = new ProductStoreFacility();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
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
