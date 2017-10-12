package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.FixedAssetMapper;

public class FixedAsset implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String fixedAssetTypeId;
private String parentFixedAssetId;
private String instanceOfProductId;
private String classEnumId;
private String partyId;
private String roleTypeId;
private String fixedAssetName;
private String acquireOrderId;
private String acquireOrderItemSeqId;
private Timestamp dateAcquired;
private Timestamp dateLastServiced;
private Timestamp dateNextService;
private Timestamp expectedEndOfLife;
private Timestamp actualEndOfLife;
private BigDecimal productionCapacity;
private String uomId;
private String calendarId;
private String serialNumber;
private String locatedAtFacilityId;
private String locatedAtLocationSeqId;
private BigDecimal salvageValue;
private BigDecimal depreciation;
private BigDecimal purchaseCost;
private String purchaseCostUomId;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getFixedAssetTypeId() {
return fixedAssetTypeId;
}

public void setFixedAssetTypeId(String  fixedAssetTypeId) {
this.fixedAssetTypeId = fixedAssetTypeId;
}

public String getParentFixedAssetId() {
return parentFixedAssetId;
}

public void setParentFixedAssetId(String  parentFixedAssetId) {
this.parentFixedAssetId = parentFixedAssetId;
}

public String getInstanceOfProductId() {
return instanceOfProductId;
}

public void setInstanceOfProductId(String  instanceOfProductId) {
this.instanceOfProductId = instanceOfProductId;
}

public String getClassEnumId() {
return classEnumId;
}

public void setClassEnumId(String  classEnumId) {
this.classEnumId = classEnumId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getFixedAssetName() {
return fixedAssetName;
}

public void setFixedAssetName(String  fixedAssetName) {
this.fixedAssetName = fixedAssetName;
}

public String getAcquireOrderId() {
return acquireOrderId;
}

public void setAcquireOrderId(String  acquireOrderId) {
this.acquireOrderId = acquireOrderId;
}

public String getAcquireOrderItemSeqId() {
return acquireOrderItemSeqId;
}

public void setAcquireOrderItemSeqId(String  acquireOrderItemSeqId) {
this.acquireOrderItemSeqId = acquireOrderItemSeqId;
}

public Timestamp getDateAcquired() {
return dateAcquired;
}

public void setDateAcquired(Timestamp  dateAcquired) {
this.dateAcquired = dateAcquired;
}

public Timestamp getDateLastServiced() {
return dateLastServiced;
}

public void setDateLastServiced(Timestamp  dateLastServiced) {
this.dateLastServiced = dateLastServiced;
}

public Timestamp getDateNextService() {
return dateNextService;
}

public void setDateNextService(Timestamp  dateNextService) {
this.dateNextService = dateNextService;
}

public Timestamp getExpectedEndOfLife() {
return expectedEndOfLife;
}

public void setExpectedEndOfLife(Timestamp  expectedEndOfLife) {
this.expectedEndOfLife = expectedEndOfLife;
}

public Timestamp getActualEndOfLife() {
return actualEndOfLife;
}

public void setActualEndOfLife(Timestamp  actualEndOfLife) {
this.actualEndOfLife = actualEndOfLife;
}

public BigDecimal getProductionCapacity() {
return productionCapacity;
}

public void setProductionCapacity(BigDecimal  productionCapacity) {
this.productionCapacity = productionCapacity;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}

public String getCalendarId() {
return calendarId;
}

public void setCalendarId(String  calendarId) {
this.calendarId = calendarId;
}

public String getSerialNumber() {
return serialNumber;
}

public void setSerialNumber(String  serialNumber) {
this.serialNumber = serialNumber;
}

public String getLocatedAtFacilityId() {
return locatedAtFacilityId;
}

public void setLocatedAtFacilityId(String  locatedAtFacilityId) {
this.locatedAtFacilityId = locatedAtFacilityId;
}

public String getLocatedAtLocationSeqId() {
return locatedAtLocationSeqId;
}

public void setLocatedAtLocationSeqId(String  locatedAtLocationSeqId) {
this.locatedAtLocationSeqId = locatedAtLocationSeqId;
}

public BigDecimal getSalvageValue() {
return salvageValue;
}

public void setSalvageValue(BigDecimal  salvageValue) {
this.salvageValue = salvageValue;
}

public BigDecimal getDepreciation() {
return depreciation;
}

public void setDepreciation(BigDecimal  depreciation) {
this.depreciation = depreciation;
}

public BigDecimal getPurchaseCost() {
return purchaseCost;
}

public void setPurchaseCost(BigDecimal  purchaseCost) {
this.purchaseCost = purchaseCost;
}

public String getPurchaseCostUomId() {
return purchaseCostUomId;
}

public void setPurchaseCostUomId(String  purchaseCostUomId) {
this.purchaseCostUomId = purchaseCostUomId;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetMapper.map(this);
}
}
