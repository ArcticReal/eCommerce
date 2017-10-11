package com.skytala.eCommerce.domain.order.relations.shoppingListItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.model.ShoppingListItem;

public class ShoppingListItemMapper  {


	public static Map<String, Object> map(ShoppingListItem shoppinglistitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shoppinglistitem.getShoppingListId() != null ){
			returnVal.put("shoppingListId",shoppinglistitem.getShoppingListId());
}

		if(shoppinglistitem.getShoppingListItemSeqId() != null ){
			returnVal.put("shoppingListItemSeqId",shoppinglistitem.getShoppingListItemSeqId());
}

		if(shoppinglistitem.getProductId() != null ){
			returnVal.put("productId",shoppinglistitem.getProductId());
}

		if(shoppinglistitem.getQuantity() != null ){
			returnVal.put("quantity",shoppinglistitem.getQuantity());
}

		if(shoppinglistitem.getModifiedPrice() != null ){
			returnVal.put("modifiedPrice",shoppinglistitem.getModifiedPrice());
}

		if(shoppinglistitem.getReservStart() != null ){
			returnVal.put("reservStart",shoppinglistitem.getReservStart());
}

		if(shoppinglistitem.getReservLength() != null ){
			returnVal.put("reservLength",shoppinglistitem.getReservLength());
}

		if(shoppinglistitem.getReservPersons() != null ){
			returnVal.put("reservPersons",shoppinglistitem.getReservPersons());
}

		if(shoppinglistitem.getQuantityPurchased() != null ){
			returnVal.put("quantityPurchased",shoppinglistitem.getQuantityPurchased());
}

		if(shoppinglistitem.getConfigId() != null ){
			returnVal.put("configId",shoppinglistitem.getConfigId());
}

		return returnVal;
}


	public static ShoppingListItem map(Map<String, Object> fields) {

		ShoppingListItem returnVal = new ShoppingListItem();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("shoppingListItemSeqId") != null) {
			returnVal.setShoppingListItemSeqId((String) fields.get("shoppingListItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("modifiedPrice") != null) {
			returnVal.setModifiedPrice((BigDecimal) fields.get("modifiedPrice"));
}

		if(fields.get("reservStart") != null) {
			returnVal.setReservStart((Timestamp) fields.get("reservStart"));
}

		if(fields.get("reservLength") != null) {
			returnVal.setReservLength((BigDecimal) fields.get("reservLength"));
}

		if(fields.get("reservPersons") != null) {
			returnVal.setReservPersons((BigDecimal) fields.get("reservPersons"));
}

		if(fields.get("quantityPurchased") != null) {
			returnVal.setQuantityPurchased((BigDecimal) fields.get("quantityPurchased"));
}

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}


		return returnVal;
 } 
	public static ShoppingListItem mapstrstr(Map<String, String> fields) throws Exception {

		ShoppingListItem returnVal = new ShoppingListItem();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("shoppingListItemSeqId") != null) {
			returnVal.setShoppingListItemSeqId((String) fields.get("shoppingListItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("modifiedPrice") != null) {
String buf;
buf = fields.get("modifiedPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setModifiedPrice(bd);
}

		if(fields.get("reservStart") != null) {
String buf = fields.get("reservStart");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReservStart(ibuf);
}

		if(fields.get("reservLength") != null) {
String buf;
buf = fields.get("reservLength");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservLength(bd);
}

		if(fields.get("reservPersons") != null) {
String buf;
buf = fields.get("reservPersons");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}

		if(fields.get("quantityPurchased") != null) {
String buf;
buf = fields.get("quantityPurchased");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityPurchased(bd);
}

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}


		return returnVal;
 } 
	public static ShoppingListItem map(GenericValue val) {

ShoppingListItem returnVal = new ShoppingListItem();
		returnVal.setShoppingListId(val.getString("shoppingListId"));
		returnVal.setShoppingListItemSeqId(val.getString("shoppingListItemSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setModifiedPrice(val.getBigDecimal("modifiedPrice"));
		returnVal.setReservStart(val.getTimestamp("reservStart"));
		returnVal.setReservLength(val.getBigDecimal("reservLength"));
		returnVal.setReservPersons(val.getBigDecimal("reservPersons"));
		returnVal.setQuantityPurchased(val.getBigDecimal("quantityPurchased"));
		returnVal.setConfigId(val.getString("configId"));


return returnVal;

}

public static ShoppingListItem map(HttpServletRequest request) throws Exception {

		ShoppingListItem returnVal = new ShoppingListItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shoppingListId")) {
returnVal.setShoppingListId(request.getParameter("shoppingListId"));
}

		if(paramMap.containsKey("shoppingListItemSeqId"))  {
returnVal.setShoppingListItemSeqId(request.getParameter("shoppingListItemSeqId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("modifiedPrice"))  {
String buf = request.getParameter("modifiedPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setModifiedPrice(bd);
}
		if(paramMap.containsKey("reservStart"))  {
String buf = request.getParameter("reservStart");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReservStart(ibuf);
}
		if(paramMap.containsKey("reservLength"))  {
String buf = request.getParameter("reservLength");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservLength(bd);
}
		if(paramMap.containsKey("reservPersons"))  {
String buf = request.getParameter("reservPersons");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}
		if(paramMap.containsKey("quantityPurchased"))  {
String buf = request.getParameter("quantityPurchased");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityPurchased(bd);
}
		if(paramMap.containsKey("configId"))  {
returnVal.setConfigId(request.getParameter("configId"));
}
return returnVal;

}
}
