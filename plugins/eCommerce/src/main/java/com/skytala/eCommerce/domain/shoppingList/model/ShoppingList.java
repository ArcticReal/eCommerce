package com.skytala.eCommerce.domain.shoppingList.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shoppingList.mapper.ShoppingListMapper;

public class ShoppingList implements Serializable{

private static final long serialVersionUID = 1L;
private String shoppingListId;
private String shoppingListTypeId;
private String parentShoppingListId;
private String productStoreId;
private String visitorId;
private String partyId;
private String listName;
private String description;
private Boolean isPublic;
private Boolean isActive;
private String currencyUom;
private String shipmentMethodTypeId;
private String carrierPartyId;
private String carrierRoleTypeId;
private String contactMechId;
private String paymentMethodId;
private String recurrenceInfoId;
private Timestamp lastOrderedDate;
private Timestamp lastAdminModified;
private String productPromoCodeId;

public String getShoppingListId() {
return shoppingListId;
}

public void setShoppingListId(String  shoppingListId) {
this.shoppingListId = shoppingListId;
}

public String getShoppingListTypeId() {
return shoppingListTypeId;
}

public void setShoppingListTypeId(String  shoppingListTypeId) {
this.shoppingListTypeId = shoppingListTypeId;
}

public String getParentShoppingListId() {
return parentShoppingListId;
}

public void setParentShoppingListId(String  parentShoppingListId) {
this.parentShoppingListId = parentShoppingListId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getVisitorId() {
return visitorId;
}

public void setVisitorId(String  visitorId) {
this.visitorId = visitorId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getListName() {
return listName;
}

public void setListName(String  listName) {
this.listName = listName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Boolean getIsPublic() {
return isPublic;
}

public void setIsPublic(Boolean  isPublic) {
this.isPublic = isPublic;
}

public Boolean getIsActive() {
return isActive;
}

public void setIsActive(Boolean  isActive) {
this.isActive = isActive;
}

public String getCurrencyUom() {
return currencyUom;
}

public void setCurrencyUom(String  currencyUom) {
this.currencyUom = currencyUom;
}

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getCarrierPartyId() {
return carrierPartyId;
}

public void setCarrierPartyId(String  carrierPartyId) {
this.carrierPartyId = carrierPartyId;
}

public String getCarrierRoleTypeId() {
return carrierRoleTypeId;
}

public void setCarrierRoleTypeId(String  carrierRoleTypeId) {
this.carrierRoleTypeId = carrierRoleTypeId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getPaymentMethodId() {
return paymentMethodId;
}

public void setPaymentMethodId(String  paymentMethodId) {
this.paymentMethodId = paymentMethodId;
}

public String getRecurrenceInfoId() {
return recurrenceInfoId;
}

public void setRecurrenceInfoId(String  recurrenceInfoId) {
this.recurrenceInfoId = recurrenceInfoId;
}

public Timestamp getLastOrderedDate() {
return lastOrderedDate;
}

public void setLastOrderedDate(Timestamp  lastOrderedDate) {
this.lastOrderedDate = lastOrderedDate;
}

public Timestamp getLastAdminModified() {
return lastAdminModified;
}

public void setLastAdminModified(Timestamp  lastAdminModified) {
this.lastAdminModified = lastAdminModified;
}

public String getProductPromoCodeId() {
return productPromoCodeId;
}

public void setProductPromoCodeId(String  productPromoCodeId) {
this.productPromoCodeId = productPromoCodeId;
}


public Map<String, Object> mapAttributeField() {
return ShoppingListMapper.map(this);
}
}
