package com.skytala.eCommerce.domain.product.relations.costComponent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.CostComponentMapper;

public class CostComponent implements Serializable{

private static final long serialVersionUID = 1L;
private String costComponentId;
private String costComponentTypeId;
private String productId;
private String productFeatureId;
private String partyId;
private String geoId;
private String workEffortId;
private String fixedAssetId;
private String costComponentCalcId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal cost;
private String costUomId;

public String getCostComponentId() {
return costComponentId;
}

public void setCostComponentId(String  costComponentId) {
this.costComponentId = costComponentId;
}

public String getCostComponentTypeId() {
return costComponentTypeId;
}

public void setCostComponentTypeId(String  costComponentTypeId) {
this.costComponentTypeId = costComponentTypeId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getCostComponentCalcId() {
return costComponentCalcId;
}

public void setCostComponentCalcId(String  costComponentCalcId) {
this.costComponentCalcId = costComponentCalcId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public BigDecimal getCost() {
return cost;
}

public void setCost(BigDecimal  cost) {
this.cost = cost;
}

public String getCostUomId() {
return costUomId;
}

public void setCostUomId(String  costUomId) {
this.costUomId = costUomId;
}


public Map<String, Object> mapAttributeField() {
return CostComponentMapper.map(this);
}
}
