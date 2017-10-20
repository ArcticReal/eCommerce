package com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.tempRes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;

public class InventoryItemTempResMapper  {


	public static Map<String, Object> map(InventoryItemTempRes inventoryitemtempres) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemtempres.getVisitId() != null ){
			returnVal.put("visitId",inventoryitemtempres.getVisitId());
}

		if(inventoryitemtempres.getProductId() != null ){
			returnVal.put("productId",inventoryitemtempres.getProductId());
}

		if(inventoryitemtempres.getProductStoreId() != null ){
			returnVal.put("productStoreId",inventoryitemtempres.getProductStoreId());
}

		if(inventoryitemtempres.getQuantity() != null ){
			returnVal.put("quantity",inventoryitemtempres.getQuantity());
}

		if(inventoryitemtempres.getReservedDate() != null ){
			returnVal.put("reservedDate",inventoryitemtempres.getReservedDate());
}

		return returnVal;
}


	public static InventoryItemTempRes map(Map<String, Object> fields) {

		InventoryItemTempRes returnVal = new InventoryItemTempRes();

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("reservedDate") != null) {
			returnVal.setReservedDate((Timestamp) fields.get("reservedDate"));
}


		return returnVal;
 } 
	public static InventoryItemTempRes mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemTempRes returnVal = new InventoryItemTempRes();

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("reservedDate") != null) {
String buf = fields.get("reservedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReservedDate(ibuf);
}


		return returnVal;
 } 
	public static InventoryItemTempRes map(GenericValue val) {

InventoryItemTempRes returnVal = new InventoryItemTempRes();
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setReservedDate(val.getTimestamp("reservedDate"));


return returnVal;

}

public static InventoryItemTempRes map(HttpServletRequest request) throws Exception {

		InventoryItemTempRes returnVal = new InventoryItemTempRes();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("visitId")) {
returnVal.setVisitId(request.getParameter("visitId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("reservedDate"))  {
String buf = request.getParameter("reservedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReservedDate(ibuf);
}
return returnVal;

}
}
