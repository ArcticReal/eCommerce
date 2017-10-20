package com.skytala.eCommerce.domain.shipment.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.mapper.ShipmentMapper;

public class Shipment implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentTypeId;
private String statusId;
private String primaryOrderId;
private String primaryReturnId;
private String primaryShipGroupSeqId;
private String picklistBinId;
private Timestamp estimatedReadyDate;
private Timestamp estimatedShipDate;
private String estimatedShipWorkEffId;
private Timestamp estimatedArrivalDate;
private String estimatedArrivalWorkEffId;
private Timestamp latestCancelDate;
private BigDecimal estimatedShipCost;
private String currencyUomId;
private String handlingInstructions;
private String originFacilityId;
private String destinationFacilityId;
private String originContactMechId;
private String originTelecomNumberId;
private String destinationContactMechId;
private String destinationTelecomNumberId;
private String partyIdTo;
private String partyIdFrom;
private BigDecimal additionalShippingCharge;
private String addtlShippingChargeDesc;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentTypeId() {
return shipmentTypeId;
}

public void setShipmentTypeId(String  shipmentTypeId) {
this.shipmentTypeId = shipmentTypeId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getPrimaryOrderId() {
return primaryOrderId;
}

public void setPrimaryOrderId(String  primaryOrderId) {
this.primaryOrderId = primaryOrderId;
}

public String getPrimaryReturnId() {
return primaryReturnId;
}

public void setPrimaryReturnId(String  primaryReturnId) {
this.primaryReturnId = primaryReturnId;
}

public String getPrimaryShipGroupSeqId() {
return primaryShipGroupSeqId;
}

public void setPrimaryShipGroupSeqId(String  primaryShipGroupSeqId) {
this.primaryShipGroupSeqId = primaryShipGroupSeqId;
}

public String getPicklistBinId() {
return picklistBinId;
}

public void setPicklistBinId(String  picklistBinId) {
this.picklistBinId = picklistBinId;
}

public Timestamp getEstimatedReadyDate() {
return estimatedReadyDate;
}

public void setEstimatedReadyDate(Timestamp  estimatedReadyDate) {
this.estimatedReadyDate = estimatedReadyDate;
}

public Timestamp getEstimatedShipDate() {
return estimatedShipDate;
}

public void setEstimatedShipDate(Timestamp  estimatedShipDate) {
this.estimatedShipDate = estimatedShipDate;
}

public String getEstimatedShipWorkEffId() {
return estimatedShipWorkEffId;
}

public void setEstimatedShipWorkEffId(String  estimatedShipWorkEffId) {
this.estimatedShipWorkEffId = estimatedShipWorkEffId;
}

public Timestamp getEstimatedArrivalDate() {
return estimatedArrivalDate;
}

public void setEstimatedArrivalDate(Timestamp  estimatedArrivalDate) {
this.estimatedArrivalDate = estimatedArrivalDate;
}

public String getEstimatedArrivalWorkEffId() {
return estimatedArrivalWorkEffId;
}

public void setEstimatedArrivalWorkEffId(String  estimatedArrivalWorkEffId) {
this.estimatedArrivalWorkEffId = estimatedArrivalWorkEffId;
}

public Timestamp getLatestCancelDate() {
return latestCancelDate;
}

public void setLatestCancelDate(Timestamp  latestCancelDate) {
this.latestCancelDate = latestCancelDate;
}

public BigDecimal getEstimatedShipCost() {
return estimatedShipCost;
}

public void setEstimatedShipCost(BigDecimal  estimatedShipCost) {
this.estimatedShipCost = estimatedShipCost;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getHandlingInstructions() {
return handlingInstructions;
}

public void setHandlingInstructions(String  handlingInstructions) {
this.handlingInstructions = handlingInstructions;
}

public String getOriginFacilityId() {
return originFacilityId;
}

public void setOriginFacilityId(String  originFacilityId) {
this.originFacilityId = originFacilityId;
}

public String getDestinationFacilityId() {
return destinationFacilityId;
}

public void setDestinationFacilityId(String  destinationFacilityId) {
this.destinationFacilityId = destinationFacilityId;
}

public String getOriginContactMechId() {
return originContactMechId;
}

public void setOriginContactMechId(String  originContactMechId) {
this.originContactMechId = originContactMechId;
}

public String getOriginTelecomNumberId() {
return originTelecomNumberId;
}

public void setOriginTelecomNumberId(String  originTelecomNumberId) {
this.originTelecomNumberId = originTelecomNumberId;
}

public String getDestinationContactMechId() {
return destinationContactMechId;
}

public void setDestinationContactMechId(String  destinationContactMechId) {
this.destinationContactMechId = destinationContactMechId;
}

public String getDestinationTelecomNumberId() {
return destinationTelecomNumberId;
}

public void setDestinationTelecomNumberId(String  destinationTelecomNumberId) {
this.destinationTelecomNumberId = destinationTelecomNumberId;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public BigDecimal getAdditionalShippingCharge() {
return additionalShippingCharge;
}

public void setAdditionalShippingCharge(BigDecimal  additionalShippingCharge) {
this.additionalShippingCharge = additionalShippingCharge;
}

public String getAddtlShippingChargeDesc() {
return addtlShippingChargeDesc;
}

public void setAddtlShippingChargeDesc(String  addtlShippingChargeDesc) {
this.addtlShippingChargeDesc = addtlShippingChargeDesc;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp  createdDate) {
this.createdDate = createdDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public Timestamp getLastModifiedDate() {
return lastModifiedDate;
}

public void setLastModifiedDate(Timestamp  lastModifiedDate) {
this.lastModifiedDate = lastModifiedDate;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return ShipmentMapper.map(this);
}
}
