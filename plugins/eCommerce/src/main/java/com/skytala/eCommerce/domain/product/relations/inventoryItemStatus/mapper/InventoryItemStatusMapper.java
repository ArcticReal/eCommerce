package com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.model.InventoryItemStatus;

public class InventoryItemStatusMapper  {


	public static Map<String, Object> map(InventoryItemStatus inventoryitemstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemstatus.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",inventoryitemstatus.getInventoryItemId());
}

		if(inventoryitemstatus.getStatusId() != null ){
			returnVal.put("statusId",inventoryitemstatus.getStatusId());
}

		if(inventoryitemstatus.getStatusDatetime() != null ){
			returnVal.put("statusDatetime",inventoryitemstatus.getStatusDatetime());
}

		if(inventoryitemstatus.getStatusEndDatetime() != null ){
			returnVal.put("statusEndDatetime",inventoryitemstatus.getStatusEndDatetime());
}

		if(inventoryitemstatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",inventoryitemstatus.getChangeByUserLoginId());
}

		if(inventoryitemstatus.getOwnerPartyId() != null ){
			returnVal.put("ownerPartyId",inventoryitemstatus.getOwnerPartyId());
}

		if(inventoryitemstatus.getProductId() != null ){
			returnVal.put("productId",inventoryitemstatus.getProductId());
}

		return returnVal;
}


	public static InventoryItemStatus map(Map<String, Object> fields) {

		InventoryItemStatus returnVal = new InventoryItemStatus();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDatetime") != null) {
			returnVal.setStatusDatetime((Timestamp) fields.get("statusDatetime"));
}

		if(fields.get("statusEndDatetime") != null) {
			returnVal.setStatusEndDatetime((Timestamp) fields.get("statusEndDatetime"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}


		return returnVal;
 } 
	public static InventoryItemStatus mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemStatus returnVal = new InventoryItemStatus();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDatetime") != null) {
String buf = fields.get("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDatetime(ibuf);
}

		if(fields.get("statusEndDatetime") != null) {
String buf = fields.get("statusEndDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusEndDatetime(ibuf);
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}


		return returnVal;
 } 
	public static InventoryItemStatus map(GenericValue val) {

InventoryItemStatus returnVal = new InventoryItemStatus();
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setStatusDatetime(val.getTimestamp("statusDatetime"));
		returnVal.setStatusEndDatetime(val.getTimestamp("statusEndDatetime"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));
		returnVal.setOwnerPartyId(val.getString("ownerPartyId"));
		returnVal.setProductId(val.getString("productId"));


return returnVal;

}

public static InventoryItemStatus map(HttpServletRequest request) throws Exception {

		InventoryItemStatus returnVal = new InventoryItemStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemId")) {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("statusDatetime"))  {
String buf = request.getParameter("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDatetime(ibuf);
}
		if(paramMap.containsKey("statusEndDatetime"))  {
String buf = request.getParameter("statusEndDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusEndDatetime(ibuf);
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
		if(paramMap.containsKey("ownerPartyId"))  {
returnVal.setOwnerPartyId(request.getParameter("ownerPartyId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
return returnVal;

}
}
