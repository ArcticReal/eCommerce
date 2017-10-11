package com.skytala.eCommerce.domain.product.relations.productFacilityLocation.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model.ProductFacilityLocation;

public class ProductFacilityLocationMapper  {


	public static Map<String, Object> map(ProductFacilityLocation productfacilitylocation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfacilitylocation.getProductId() != null ){
			returnVal.put("productId",productfacilitylocation.getProductId());
}

		if(productfacilitylocation.getFacilityId() != null ){
			returnVal.put("facilityId",productfacilitylocation.getFacilityId());
}

		if(productfacilitylocation.getLocationSeqId() != null ){
			returnVal.put("locationSeqId",productfacilitylocation.getLocationSeqId());
}

		if(productfacilitylocation.getMinimumStock() != null ){
			returnVal.put("minimumStock",productfacilitylocation.getMinimumStock());
}

		if(productfacilitylocation.getMoveQuantity() != null ){
			returnVal.put("moveQuantity",productfacilitylocation.getMoveQuantity());
}

		return returnVal;
}


	public static ProductFacilityLocation map(Map<String, Object> fields) {

		ProductFacilityLocation returnVal = new ProductFacilityLocation();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("minimumStock") != null) {
			returnVal.setMinimumStock((BigDecimal) fields.get("minimumStock"));
}

		if(fields.get("moveQuantity") != null) {
			returnVal.setMoveQuantity((BigDecimal) fields.get("moveQuantity"));
}


		return returnVal;
 } 
	public static ProductFacilityLocation mapstrstr(Map<String, String> fields) throws Exception {

		ProductFacilityLocation returnVal = new ProductFacilityLocation();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("minimumStock") != null) {
String buf;
buf = fields.get("minimumStock");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumStock(bd);
}

		if(fields.get("moveQuantity") != null) {
String buf;
buf = fields.get("moveQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMoveQuantity(bd);
}


		return returnVal;
 } 
	public static ProductFacilityLocation map(GenericValue val) {

ProductFacilityLocation returnVal = new ProductFacilityLocation();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setLocationSeqId(val.getString("locationSeqId"));
		returnVal.setMinimumStock(val.getBigDecimal("minimumStock"));
		returnVal.setMoveQuantity(val.getBigDecimal("moveQuantity"));


return returnVal;

}

public static ProductFacilityLocation map(HttpServletRequest request) throws Exception {

		ProductFacilityLocation returnVal = new ProductFacilityLocation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("locationSeqId"))  {
returnVal.setLocationSeqId(request.getParameter("locationSeqId"));
}
		if(paramMap.containsKey("minimumStock"))  {
String buf = request.getParameter("minimumStock");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumStock(bd);
}
		if(paramMap.containsKey("moveQuantity"))  {
String buf = request.getParameter("moveQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMoveQuantity(bd);
}
return returnVal;

}
}
