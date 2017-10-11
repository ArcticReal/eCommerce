package com.skytala.eCommerce.domain.product.relations.productFacility.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFacility.model.ProductFacility;

public class ProductFacilityMapper  {


	public static Map<String, Object> map(ProductFacility productfacility) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfacility.getProductId() != null ){
			returnVal.put("productId",productfacility.getProductId());
}

		if(productfacility.getFacilityId() != null ){
			returnVal.put("facilityId",productfacility.getFacilityId());
}

		if(productfacility.getMinimumStock() != null ){
			returnVal.put("minimumStock",productfacility.getMinimumStock());
}

		if(productfacility.getReorderQuantity() != null ){
			returnVal.put("reorderQuantity",productfacility.getReorderQuantity());
}

		if(productfacility.getDaysToShip() != null ){
			returnVal.put("daysToShip",productfacility.getDaysToShip());
}

		if(productfacility.getLastInventoryCount() != null ){
			returnVal.put("lastInventoryCount",productfacility.getLastInventoryCount());
}

		return returnVal;
}


	public static ProductFacility map(Map<String, Object> fields) {

		ProductFacility returnVal = new ProductFacility();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("minimumStock") != null) {
			returnVal.setMinimumStock((BigDecimal) fields.get("minimumStock"));
}

		if(fields.get("reorderQuantity") != null) {
			returnVal.setReorderQuantity((BigDecimal) fields.get("reorderQuantity"));
}

		if(fields.get("daysToShip") != null) {
			returnVal.setDaysToShip((long) fields.get("daysToShip"));
}

		if(fields.get("lastInventoryCount") != null) {
			returnVal.setLastInventoryCount((BigDecimal) fields.get("lastInventoryCount"));
}


		return returnVal;
 } 
	public static ProductFacility mapstrstr(Map<String, String> fields) throws Exception {

		ProductFacility returnVal = new ProductFacility();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("minimumStock") != null) {
String buf;
buf = fields.get("minimumStock");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumStock(bd);
}

		if(fields.get("reorderQuantity") != null) {
String buf;
buf = fields.get("reorderQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReorderQuantity(bd);
}

		if(fields.get("daysToShip") != null) {
String buf;
buf = fields.get("daysToShip");
long ibuf = Long.parseLong(buf);
			returnVal.setDaysToShip(ibuf);
}

		if(fields.get("lastInventoryCount") != null) {
String buf;
buf = fields.get("lastInventoryCount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setLastInventoryCount(bd);
}


		return returnVal;
 } 
	public static ProductFacility map(GenericValue val) {

ProductFacility returnVal = new ProductFacility();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setMinimumStock(val.getBigDecimal("minimumStock"));
		returnVal.setReorderQuantity(val.getBigDecimal("reorderQuantity"));
		returnVal.setDaysToShip(val.getLong("daysToShip"));
		returnVal.setLastInventoryCount(val.getBigDecimal("lastInventoryCount"));


return returnVal;

}

public static ProductFacility map(HttpServletRequest request) throws Exception {

		ProductFacility returnVal = new ProductFacility();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("minimumStock"))  {
String buf = request.getParameter("minimumStock");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumStock(bd);
}
		if(paramMap.containsKey("reorderQuantity"))  {
String buf = request.getParameter("reorderQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReorderQuantity(bd);
}
		if(paramMap.containsKey("daysToShip"))  {
String buf = request.getParameter("daysToShip");
Long ibuf = Long.parseLong(buf);
returnVal.setDaysToShip(ibuf);
}
		if(paramMap.containsKey("lastInventoryCount"))  {
String buf = request.getParameter("lastInventoryCount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setLastInventoryCount(bd);
}
return returnVal;

}
}
