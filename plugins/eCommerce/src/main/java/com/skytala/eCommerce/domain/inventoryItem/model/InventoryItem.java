package com.skytala.eCommerce.domain.inventoryItem.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.inventoryItem.mapper.InventoryItemMapper;

public class InventoryItem implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemId;
private String inventoryItemTypeId;
private String productId;
private String partyId;
private String ownerPartyId;
private String statusId;
private Timestamp datetimeReceived;
private Timestamp datetimeManufactured;
private Timestamp expireDate;
private String facilityId;
private String containerId;
private String lotId;
private String uomId;
private String binNumber;
private String locationSeqId;
private String comments;
private BigDecimal quantityOnHandTotal;
private BigDecimal availableToPromiseTotal;
private BigDecimal accountingQuantityTotal;
private BigDecimal oldQuantityOnHand;
private BigDecimal oldAvailableToPromise;
private Long serialNumber;
private Long softIdentifier;
private Long activationNumber;
private Timestamp activationValidThru;
private BigDecimal unitCost;
private String currencyUomId;

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getInventoryItemTypeId() {
return inventoryItemTypeId;
}

public void setInventoryItemTypeId(String  inventoryItemTypeId) {
this.inventoryItemTypeId = inventoryItemTypeId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getOwnerPartyId() {
return ownerPartyId;
}

public void setOwnerPartyId(String  ownerPartyId) {
this.ownerPartyId = ownerPartyId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getDatetimeReceived() {
return datetimeReceived;
}

public void setDatetimeReceived(Timestamp  datetimeReceived) {
this.datetimeReceived = datetimeReceived;
}

public Timestamp getDatetimeManufactured() {
return datetimeManufactured;
}

public void setDatetimeManufactured(Timestamp  datetimeManufactured) {
this.datetimeManufactured = datetimeManufactured;
}

public Timestamp getExpireDate() {
return expireDate;
}

public void setExpireDate(Timestamp  expireDate) {
this.expireDate = expireDate;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getContainerId() {
return containerId;
}

public void setContainerId(String  containerId) {
this.containerId = containerId;
}

public String getLotId() {
return lotId;
}

public void setLotId(String  lotId) {
this.lotId = lotId;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}

public String getBinNumber() {
return binNumber;
}

public void setBinNumber(String  binNumber) {
this.binNumber = binNumber;
}

public String getLocationSeqId() {
return locationSeqId;
}

public void setLocationSeqId(String  locationSeqId) {
this.locationSeqId = locationSeqId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public BigDecimal getQuantityOnHandTotal() {
return quantityOnHandTotal;
}

public void setQuantityOnHandTotal(BigDecimal  quantityOnHandTotal) {
this.quantityOnHandTotal = quantityOnHandTotal;
}

public BigDecimal getAvailableToPromiseTotal() {
return availableToPromiseTotal;
}

public void setAvailableToPromiseTotal(BigDecimal  availableToPromiseTotal) {
this.availableToPromiseTotal = availableToPromiseTotal;
}

public BigDecimal getAccountingQuantityTotal() {
return accountingQuantityTotal;
}

public void setAccountingQuantityTotal(BigDecimal  accountingQuantityTotal) {
this.accountingQuantityTotal = accountingQuantityTotal;
}

public BigDecimal getOldQuantityOnHand() {
return oldQuantityOnHand;
}

public void setOldQuantityOnHand(BigDecimal  oldQuantityOnHand) {
this.oldQuantityOnHand = oldQuantityOnHand;
}

public BigDecimal getOldAvailableToPromise() {
return oldAvailableToPromise;
}

public void setOldAvailableToPromise(BigDecimal  oldAvailableToPromise) {
this.oldAvailableToPromise = oldAvailableToPromise;
}

public Long getSerialNumber() {
return serialNumber;
}

public void setSerialNumber(Long  serialNumber) {
this.serialNumber = serialNumber;
}

public Long getSoftIdentifier() {
return softIdentifier;
}

public void setSoftIdentifier(Long  softIdentifier) {
this.softIdentifier = softIdentifier;
}

public Long getActivationNumber() {
return activationNumber;
}

public void setActivationNumber(Long  activationNumber) {
this.activationNumber = activationNumber;
}

public Timestamp getActivationValidThru() {
return activationValidThru;
}

public void setActivationValidThru(Timestamp  activationValidThru) {
this.activationValidThru = activationValidThru;
}

public BigDecimal getUnitCost() {
return unitCost;
}

public void setUnitCost(BigDecimal  unitCost) {
this.unitCost = unitCost;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemMapper.map(this);
}
}
