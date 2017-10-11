package com.skytala.eCommerce.domain.product.relations.productAssoc.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productAssoc.mapper.ProductAssocMapper;

public class ProductAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String productIdTo;
private String productAssocTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;
private String reason;
private BigDecimal quantity;
private BigDecimal scrapFactor;
private String instruction;
private String routingWorkEffortId;
private String estimateCalcMethod;
private String recurrenceInfoId;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductIdTo() {
return productIdTo;
}

public void setProductIdTo(String  productIdTo) {
this.productIdTo = productIdTo;
}

public String getProductAssocTypeId() {
return productAssocTypeId;
}

public void setProductAssocTypeId(String  productAssocTypeId) {
this.productAssocTypeId = productAssocTypeId;
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

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public String getReason() {
return reason;
}

public void setReason(String  reason) {
this.reason = reason;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getScrapFactor() {
return scrapFactor;
}

public void setScrapFactor(BigDecimal  scrapFactor) {
this.scrapFactor = scrapFactor;
}

public String getInstruction() {
return instruction;
}

public void setInstruction(String  instruction) {
this.instruction = instruction;
}

public String getRoutingWorkEffortId() {
return routingWorkEffortId;
}

public void setRoutingWorkEffortId(String  routingWorkEffortId) {
this.routingWorkEffortId = routingWorkEffortId;
}

public String getEstimateCalcMethod() {
return estimateCalcMethod;
}

public void setEstimateCalcMethod(String  estimateCalcMethod) {
this.estimateCalcMethod = estimateCalcMethod;
}

public String getRecurrenceInfoId() {
return recurrenceInfoId;
}

public void setRecurrenceInfoId(String  recurrenceInfoId) {
this.recurrenceInfoId = recurrenceInfoId;
}


public Map<String, Object> mapAttributeField() {
return ProductAssocMapper.map(this);
}
}
