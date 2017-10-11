package com.skytala.eCommerce.domain.order.relations.custRequestItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequestItem.mapper.CustRequestItemMapper;

public class CustRequestItem implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String custRequestItemSeqId;
private String custRequestResolutionId;
private String statusId;
private Long priority;
private Long sequenceNum;
private Timestamp requiredByDate;
private String productId;
private BigDecimal quantity;
private BigDecimal selectedAmount;
private BigDecimal maximumAmount;
private Timestamp reservStart;
private BigDecimal reservLength;
private BigDecimal reservPersons;
private String configId;
private String description;
private String story;

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

public String getCustRequestResolutionId() {
return custRequestResolutionId;
}

public void setCustRequestResolutionId(String  custRequestResolutionId) {
this.custRequestResolutionId = custRequestResolutionId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Long getPriority() {
return priority;
}

public void setPriority(Long  priority) {
this.priority = priority;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public Timestamp getRequiredByDate() {
return requiredByDate;
}

public void setRequiredByDate(Timestamp  requiredByDate) {
this.requiredByDate = requiredByDate;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
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

public BigDecimal getMaximumAmount() {
return maximumAmount;
}

public void setMaximumAmount(BigDecimal  maximumAmount) {
this.maximumAmount = maximumAmount;
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

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getStory() {
return story;
}

public void setStory(String  story) {
this.story = story;
}


public Map<String, Object> mapAttributeField() {
return CustRequestItemMapper.map(this);
}
}
