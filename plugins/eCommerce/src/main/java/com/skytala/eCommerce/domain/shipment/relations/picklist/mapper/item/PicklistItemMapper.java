package com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.item;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.item.PicklistItem;

public class PicklistItemMapper  {


	public static Map<String, Object> map(PicklistItem picklistitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(picklistitem.getPicklistBinId() != null ){
			returnVal.put("picklistBinId",picklistitem.getPicklistBinId());
}

		if(picklistitem.getOrderId() != null ){
			returnVal.put("orderId",picklistitem.getOrderId());
}

		if(picklistitem.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",picklistitem.getOrderItemSeqId());
}

		if(picklistitem.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",picklistitem.getShipGroupSeqId());
}

		if(picklistitem.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",picklistitem.getInventoryItemId());
}

		if(picklistitem.getItemStatusId() != null ){
			returnVal.put("itemStatusId",picklistitem.getItemStatusId());
}

		if(picklistitem.getQuantity() != null ){
			returnVal.put("quantity",picklistitem.getQuantity());
}

		return returnVal;
}


	public static PicklistItem map(Map<String, Object> fields) {

		PicklistItem returnVal = new PicklistItem();

		if(fields.get("picklistBinId") != null) {
			returnVal.setPicklistBinId((String) fields.get("picklistBinId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("itemStatusId") != null) {
			returnVal.setItemStatusId((String) fields.get("itemStatusId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}


		return returnVal;
 } 
	public static PicklistItem mapstrstr(Map<String, String> fields) throws Exception {

		PicklistItem returnVal = new PicklistItem();

		if(fields.get("picklistBinId") != null) {
			returnVal.setPicklistBinId((String) fields.get("picklistBinId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("itemStatusId") != null) {
			returnVal.setItemStatusId((String) fields.get("itemStatusId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}


		return returnVal;
 } 
	public static PicklistItem map(GenericValue val) {

PicklistItem returnVal = new PicklistItem();
		returnVal.setPicklistBinId(val.getString("picklistBinId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setItemStatusId(val.getString("itemStatusId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));


return returnVal;

}

public static PicklistItem map(HttpServletRequest request) throws Exception {

		PicklistItem returnVal = new PicklistItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("picklistBinId")) {
returnVal.setPicklistBinId(request.getParameter("picklistBinId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("itemStatusId"))  {
returnVal.setItemStatusId(request.getParameter("itemStatusId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
return returnVal;

}
}
