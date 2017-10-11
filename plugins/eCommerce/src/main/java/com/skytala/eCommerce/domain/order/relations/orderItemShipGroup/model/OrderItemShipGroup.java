package com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.mapper.OrderItemShipGroupMapper;

public class OrderItemShipGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String shipGroupSeqId;
private String shipmentMethodTypeId;
private String supplierPartyId;
private String vendorPartyId;
private String carrierPartyId;
private String carrierRoleTypeId;
private String facilityId;
private String contactMechId;
private String telecomContactMechId;
private String trackingNumber;
private String shippingInstructions;
private Boolean maySplit;
private String giftMessage;
private Boolean isGift;
private Timestamp shipAfterDate;
private Timestamp shipByDate;
private Timestamp estimatedShipDate;
private Timestamp estimatedDeliveryDate;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getShipGroupSeqId() {
return shipGroupSeqId;
}

public void setShipGroupSeqId(String  shipGroupSeqId) {
this.shipGroupSeqId = shipGroupSeqId;
}

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getSupplierPartyId() {
return supplierPartyId;
}

public void setSupplierPartyId(String  supplierPartyId) {
this.supplierPartyId = supplierPartyId;
}

public String getVendorPartyId() {
return vendorPartyId;
}

public void setVendorPartyId(String  vendorPartyId) {
this.vendorPartyId = vendorPartyId;
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

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getTelecomContactMechId() {
return telecomContactMechId;
}

public void setTelecomContactMechId(String  telecomContactMechId) {
this.telecomContactMechId = telecomContactMechId;
}

public String getTrackingNumber() {
return trackingNumber;
}

public void setTrackingNumber(String  trackingNumber) {
this.trackingNumber = trackingNumber;
}

public String getShippingInstructions() {
return shippingInstructions;
}

public void setShippingInstructions(String  shippingInstructions) {
this.shippingInstructions = shippingInstructions;
}

public Boolean getMaySplit() {
return maySplit;
}

public void setMaySplit(Boolean  maySplit) {
this.maySplit = maySplit;
}

public String getGiftMessage() {
return giftMessage;
}

public void setGiftMessage(String  giftMessage) {
this.giftMessage = giftMessage;
}

public Boolean getIsGift() {
return isGift;
}

public void setIsGift(Boolean  isGift) {
this.isGift = isGift;
}

public Timestamp getShipAfterDate() {
return shipAfterDate;
}

public void setShipAfterDate(Timestamp  shipAfterDate) {
this.shipAfterDate = shipAfterDate;
}

public Timestamp getShipByDate() {
return shipByDate;
}

public void setShipByDate(Timestamp  shipByDate) {
this.shipByDate = shipByDate;
}

public Timestamp getEstimatedShipDate() {
return estimatedShipDate;
}

public void setEstimatedShipDate(Timestamp  estimatedShipDate) {
this.estimatedShipDate = estimatedShipDate;
}

public Timestamp getEstimatedDeliveryDate() {
return estimatedDeliveryDate;
}

public void setEstimatedDeliveryDate(Timestamp  estimatedDeliveryDate) {
this.estimatedDeliveryDate = estimatedDeliveryDate;
}


public Map<String, Object> mapAttributeField() {
return OrderItemShipGroupMapper.map(this);
}
}
