package com.skytala.eCommerce.domain.order.relations.quoteItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteItem.mapper.QuoteItemMapper;

public class QuoteItem implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteId;
private String quoteItemSeqId;
private String productId;
private String productFeatureId;
private String deliverableTypeId;
private String skillTypeId;
private String uomId;
private String workEffortId;
private String custRequestId;
private String custRequestItemSeqId;
private BigDecimal quantity;
private BigDecimal selectedAmount;
private BigDecimal quoteUnitPrice;
private Timestamp reservStart;
private BigDecimal reservLength;
private BigDecimal reservPersons;
private String configId;
private Timestamp estimatedDeliveryDate;
private String comments;
private Boolean isPromo;
private Long leadTimeDays;

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}

public String getQuoteItemSeqId() {
return quoteItemSeqId;
}

public void setQuoteItemSeqId(String  quoteItemSeqId) {
this.quoteItemSeqId = quoteItemSeqId;
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

public String getDeliverableTypeId() {
return deliverableTypeId;
}

public void setDeliverableTypeId(String  deliverableTypeId) {
this.deliverableTypeId = deliverableTypeId;
}

public String getSkillTypeId() {
return skillTypeId;
}

public void setSkillTypeId(String  skillTypeId) {
this.skillTypeId = skillTypeId;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getCustRequestItemSeqId() {
return custRequestItemSeqId;
}

public void setCustRequestItemSeqId(String  custRequestItemSeqId) {
this.custRequestItemSeqId = custRequestItemSeqId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getSelectedAmount() {
return selectedAmount;
}

public void setSelectedAmount(BigDecimal  selectedAmount) {
this.selectedAmount = selectedAmount;
}

public BigDecimal getQuoteUnitPrice() {
return quoteUnitPrice;
}

public void setQuoteUnitPrice(BigDecimal  quoteUnitPrice) {
this.quoteUnitPrice = quoteUnitPrice;
}

public Timestamp getReservStart() {
return reservStart;
}

public void setReservStart(Timestamp  reservStart) {
this.reservStart = reservStart;
}

public BigDecimal getReservLength() {
return reservLength;
}

public void setReservLength(BigDecimal  reservLength) {
this.reservLength = reservLength;
}

public BigDecimal getReservPersons() {
return reservPersons;
}

public void setReservPersons(BigDecimal  reservPersons) {
this.reservPersons = reservPersons;
}

public String getConfigId() {
return configId;
}

public void setConfigId(String  configId) {
this.configId = configId;
}

public Timestamp getEstimatedDeliveryDate() {
return estimatedDeliveryDate;
}

public void setEstimatedDeliveryDate(Timestamp  estimatedDeliveryDate) {
this.estimatedDeliveryDate = estimatedDeliveryDate;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Boolean getIsPromo() {
return isPromo;
}

public void setIsPromo(Boolean  isPromo) {
this.isPromo = isPromo;
}

public Long getLeadTimeDays() {
return leadTimeDays;
}

public void setLeadTimeDays(Long  leadTimeDays) {
this.leadTimeDays = leadTimeDays;
}


public Map<String, Object> mapAttributeField() {
return QuoteItemMapper.map(this);
}
}
