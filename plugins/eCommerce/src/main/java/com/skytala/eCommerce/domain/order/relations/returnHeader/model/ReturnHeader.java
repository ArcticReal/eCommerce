package com.skytala.eCommerce.domain.order.relations.returnHeader.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.ReturnHeaderMapper;

public class ReturnHeader implements Serializable{

private static final long serialVersionUID = 1L;
private String returnId;
private String returnHeaderTypeId;
private String statusId;
private String createdBy;
private String fromPartyId;
private String toPartyId;
private String paymentMethodId;
private String finAccountId;
private String billingAccountId;
private Timestamp entryDate;
private String originContactMechId;
private String destinationFacilityId;
private Boolean needsInventoryReceive;
private String currencyUomId;
private String supplierRmaId;

public String getReturnId() {
return returnId;
}

public void setReturnId(String  returnId) {
this.returnId = returnId;
}

public String getReturnHeaderTypeId() {
return returnHeaderTypeId;
}

public void setReturnHeaderTypeId(String  returnHeaderTypeId) {
this.returnHeaderTypeId = returnHeaderTypeId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getCreatedBy() {
return createdBy;
}

public void setCreatedBy(String  createdBy) {
this.createdBy = createdBy;
}

public String getFromPartyId() {
return fromPartyId;
}

public void setFromPartyId(String  fromPartyId) {
this.fromPartyId = fromPartyId;
}

public String getToPartyId() {
return toPartyId;
}

public void setToPartyId(String  toPartyId) {
this.toPartyId = toPartyId;
}

public String getPaymentMethodId() {
return paymentMethodId;
}

public void setPaymentMethodId(String  paymentMethodId) {
this.paymentMethodId = paymentMethodId;
}

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public String getBillingAccountId() {
return billingAccountId;
}

public void setBillingAccountId(String  billingAccountId) {
this.billingAccountId = billingAccountId;
}

public Timestamp getEntryDate() {
return entryDate;
}

public void setEntryDate(Timestamp  entryDate) {
this.entryDate = entryDate;
}

public String getOriginContactMechId() {
return originContactMechId;
}

public void setOriginContactMechId(String  originContactMechId) {
this.originContactMechId = originContactMechId;
}

public String getDestinationFacilityId() {
return destinationFacilityId;
}

public void setDestinationFacilityId(String  destinationFacilityId) {
this.destinationFacilityId = destinationFacilityId;
}

public Boolean getNeedsInventoryReceive() {
return needsInventoryReceive;
}

public void setNeedsInventoryReceive(Boolean  needsInventoryReceive) {
this.needsInventoryReceive = needsInventoryReceive;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getSupplierRmaId() {
return supplierRmaId;
}

public void setSupplierRmaId(String  supplierRmaId) {
this.supplierRmaId = supplierRmaId;
}


public Map<String, Object> mapAttributeField() {
return ReturnHeaderMapper.map(this);
}
}
