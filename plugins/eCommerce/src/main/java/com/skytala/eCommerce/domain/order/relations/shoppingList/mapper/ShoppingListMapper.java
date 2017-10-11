package com.skytala.eCommerce.domain.order.relations.shoppingList.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.ShoppingList;

public class ShoppingListMapper  {


	public static Map<String, Object> map(ShoppingList shoppinglist) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shoppinglist.getShoppingListId() != null ){
			returnVal.put("shoppingListId",shoppinglist.getShoppingListId());
}

		if(shoppinglist.getShoppingListTypeId() != null ){
			returnVal.put("shoppingListTypeId",shoppinglist.getShoppingListTypeId());
}

		if(shoppinglist.getParentShoppingListId() != null ){
			returnVal.put("parentShoppingListId",shoppinglist.getParentShoppingListId());
}

		if(shoppinglist.getProductStoreId() != null ){
			returnVal.put("productStoreId",shoppinglist.getProductStoreId());
}

		if(shoppinglist.getVisitorId() != null ){
			returnVal.put("visitorId",shoppinglist.getVisitorId());
}

		if(shoppinglist.getPartyId() != null ){
			returnVal.put("partyId",shoppinglist.getPartyId());
}

		if(shoppinglist.getListName() != null ){
			returnVal.put("listName",shoppinglist.getListName());
}

		if(shoppinglist.getDescription() != null ){
			returnVal.put("description",shoppinglist.getDescription());
}

		if(shoppinglist.getIsPublic() != null ){
			returnVal.put("isPublic",shoppinglist.getIsPublic());
}

		if(shoppinglist.getIsActive() != null ){
			returnVal.put("isActive",shoppinglist.getIsActive());
}

		if(shoppinglist.getCurrencyUom() != null ){
			returnVal.put("currencyUom",shoppinglist.getCurrencyUom());
}

		if(shoppinglist.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",shoppinglist.getShipmentMethodTypeId());
}

		if(shoppinglist.getCarrierPartyId() != null ){
			returnVal.put("carrierPartyId",shoppinglist.getCarrierPartyId());
}

		if(shoppinglist.getCarrierRoleTypeId() != null ){
			returnVal.put("carrierRoleTypeId",shoppinglist.getCarrierRoleTypeId());
}

		if(shoppinglist.getContactMechId() != null ){
			returnVal.put("contactMechId",shoppinglist.getContactMechId());
}

		if(shoppinglist.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",shoppinglist.getPaymentMethodId());
}

		if(shoppinglist.getRecurrenceInfoId() != null ){
			returnVal.put("recurrenceInfoId",shoppinglist.getRecurrenceInfoId());
}

		if(shoppinglist.getLastOrderedDate() != null ){
			returnVal.put("lastOrderedDate",shoppinglist.getLastOrderedDate());
}

		if(shoppinglist.getLastAdminModified() != null ){
			returnVal.put("lastAdminModified",shoppinglist.getLastAdminModified());
}

		if(shoppinglist.getProductPromoCodeId() != null ){
			returnVal.put("productPromoCodeId",shoppinglist.getProductPromoCodeId());
}

		return returnVal;
}


	public static ShoppingList map(Map<String, Object> fields) {

		ShoppingList returnVal = new ShoppingList();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("shoppingListTypeId") != null) {
			returnVal.setShoppingListTypeId((String) fields.get("shoppingListTypeId"));
}

		if(fields.get("parentShoppingListId") != null) {
			returnVal.setParentShoppingListId((String) fields.get("parentShoppingListId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("visitorId") != null) {
			returnVal.setVisitorId((String) fields.get("visitorId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("listName") != null) {
			returnVal.setListName((String) fields.get("listName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("isPublic") != null) {
			returnVal.setIsPublic((boolean) fields.get("isPublic"));
}

		if(fields.get("isActive") != null) {
			returnVal.setIsActive((boolean) fields.get("isActive"));
}

		if(fields.get("currencyUom") != null) {
			returnVal.setCurrencyUom((String) fields.get("currencyUom"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("carrierRoleTypeId") != null) {
			returnVal.setCarrierRoleTypeId((String) fields.get("carrierRoleTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}

		if(fields.get("lastOrderedDate") != null) {
			returnVal.setLastOrderedDate((Timestamp) fields.get("lastOrderedDate"));
}

		if(fields.get("lastAdminModified") != null) {
			returnVal.setLastAdminModified((Timestamp) fields.get("lastAdminModified"));
}

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}


		return returnVal;
 } 
	public static ShoppingList mapstrstr(Map<String, String> fields) throws Exception {

		ShoppingList returnVal = new ShoppingList();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("shoppingListTypeId") != null) {
			returnVal.setShoppingListTypeId((String) fields.get("shoppingListTypeId"));
}

		if(fields.get("parentShoppingListId") != null) {
			returnVal.setParentShoppingListId((String) fields.get("parentShoppingListId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("visitorId") != null) {
			returnVal.setVisitorId((String) fields.get("visitorId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("listName") != null) {
			returnVal.setListName((String) fields.get("listName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("isPublic") != null) {
String buf;
buf = fields.get("isPublic");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPublic(ibuf);
}

		if(fields.get("isActive") != null) {
String buf;
buf = fields.get("isActive");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsActive(ibuf);
}

		if(fields.get("currencyUom") != null) {
			returnVal.setCurrencyUom((String) fields.get("currencyUom"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("carrierRoleTypeId") != null) {
			returnVal.setCarrierRoleTypeId((String) fields.get("carrierRoleTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}

		if(fields.get("lastOrderedDate") != null) {
String buf = fields.get("lastOrderedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastOrderedDate(ibuf);
}

		if(fields.get("lastAdminModified") != null) {
String buf = fields.get("lastAdminModified");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastAdminModified(ibuf);
}

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}


		return returnVal;
 } 
	public static ShoppingList map(GenericValue val) {

ShoppingList returnVal = new ShoppingList();
		returnVal.setShoppingListId(val.getString("shoppingListId"));
		returnVal.setShoppingListTypeId(val.getString("shoppingListTypeId"));
		returnVal.setParentShoppingListId(val.getString("parentShoppingListId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setVisitorId(val.getString("visitorId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setListName(val.getString("listName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setIsPublic(val.getBoolean("isPublic"));
		returnVal.setIsActive(val.getBoolean("isActive"));
		returnVal.setCurrencyUom(val.getString("currencyUom"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setCarrierPartyId(val.getString("carrierPartyId"));
		returnVal.setCarrierRoleTypeId(val.getString("carrierRoleTypeId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setRecurrenceInfoId(val.getString("recurrenceInfoId"));
		returnVal.setLastOrderedDate(val.getTimestamp("lastOrderedDate"));
		returnVal.setLastAdminModified(val.getTimestamp("lastAdminModified"));
		returnVal.setProductPromoCodeId(val.getString("productPromoCodeId"));


return returnVal;

}

public static ShoppingList map(HttpServletRequest request) throws Exception {

		ShoppingList returnVal = new ShoppingList();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shoppingListId")) {
returnVal.setShoppingListId(request.getParameter("shoppingListId"));
}

		if(paramMap.containsKey("shoppingListTypeId"))  {
returnVal.setShoppingListTypeId(request.getParameter("shoppingListTypeId"));
}
		if(paramMap.containsKey("parentShoppingListId"))  {
returnVal.setParentShoppingListId(request.getParameter("parentShoppingListId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("visitorId"))  {
returnVal.setVisitorId(request.getParameter("visitorId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("listName"))  {
returnVal.setListName(request.getParameter("listName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("isPublic"))  {
String buf = request.getParameter("isPublic");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsPublic(ibuf);
}
		if(paramMap.containsKey("isActive"))  {
String buf = request.getParameter("isActive");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsActive(ibuf);
}
		if(paramMap.containsKey("currencyUom"))  {
returnVal.setCurrencyUom(request.getParameter("currencyUom"));
}
		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
		if(paramMap.containsKey("carrierPartyId"))  {
returnVal.setCarrierPartyId(request.getParameter("carrierPartyId"));
}
		if(paramMap.containsKey("carrierRoleTypeId"))  {
returnVal.setCarrierRoleTypeId(request.getParameter("carrierRoleTypeId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("paymentMethodId"))  {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}
		if(paramMap.containsKey("recurrenceInfoId"))  {
returnVal.setRecurrenceInfoId(request.getParameter("recurrenceInfoId"));
}
		if(paramMap.containsKey("lastOrderedDate"))  {
String buf = request.getParameter("lastOrderedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastOrderedDate(ibuf);
}
		if(paramMap.containsKey("lastAdminModified"))  {
String buf = request.getParameter("lastAdminModified");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastAdminModified(ibuf);
}
		if(paramMap.containsKey("productPromoCodeId"))  {
returnVal.setProductPromoCodeId(request.getParameter("productPromoCodeId"));
}
return returnVal;

}
}
