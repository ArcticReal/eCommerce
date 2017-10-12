package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.mapper.ProductManufacturingRuleMapper;

public class ProductManufacturingRule implements Serializable{

private static final long serialVersionUID = 1L;
private String ruleId;
private String productId;
private String productIdFor;
private String productIdIn;
private String ruleSeqId;
private Timestamp fromDate;
private String productIdInSubst;
private String productFeature;
private String ruleOperator;
private BigDecimal quantity;
private String description;
private Timestamp thruDate;

public String getRuleId() {
return ruleId;
}

public void setRuleId(String  ruleId) {
this.ruleId = ruleId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductIdFor() {
return productIdFor;
}

public void setProductIdFor(String  productIdFor) {
this.productIdFor = productIdFor;
}

public String getProductIdIn() {
return productIdIn;
}

public void setProductIdIn(String  productIdIn) {
this.productIdIn = productIdIn;
}

public String getRuleSeqId() {
return ruleSeqId;
}

public void setRuleSeqId(String  ruleSeqId) {
this.ruleSeqId = ruleSeqId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public String getProductIdInSubst() {
return productIdInSubst;
}

public void setProductIdInSubst(String  productIdInSubst) {
this.productIdInSubst = productIdInSubst;
}

public String getProductFeature() {
return productFeature;
}

public void setProductFeature(String  productFeature) {
this.productFeature = productFeature;
}

public String getRuleOperator() {
return ruleOperator;
}

public void setRuleOperator(String  ruleOperator) {
this.ruleOperator = ruleOperator;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return ProductManufacturingRuleMapper.map(this);
}
}
