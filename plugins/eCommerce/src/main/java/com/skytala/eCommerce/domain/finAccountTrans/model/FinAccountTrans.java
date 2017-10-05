package com.skytala.eCommerce.domain.finAccountTrans.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.finAccountTrans.mapper.FinAccountTransMapper;

public class FinAccountTrans implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountTransId;
private String finAccountTransTypeId;
private String finAccountId;
private String partyId;
private String glReconciliationId;
private Timestamp transactionDate;
private Timestamp entryDate;
private BigDecimal amount;
private String paymentId;
private String orderId;
private String orderItemSeqId;
private String performedByPartyId;
private String reasonEnumId;
private String comments;
private String statusId;

public String getFinAccountTransId() {
return finAccountTransId;
}

public void setFinAccountTransId(String  finAccountTransId) {
this.finAccountTransId = finAccountTransId;
}

public String getFinAccountTransTypeId() {
return finAccountTransTypeId;
}

public void setFinAccountTransTypeId(String  finAccountTransTypeId) {
this.finAccountTransTypeId = finAccountTransTypeId;
}

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getGlReconciliationId() {
return glReconciliationId;
}

public void setGlReconciliationId(String  glReconciliationId) {
this.glReconciliationId = glReconciliationId;
}

public Timestamp getTransactionDate() {
return transactionDate;
}

public void setTransactionDate(Timestamp  transactionDate) {
this.transactionDate = transactionDate;
}

public Timestamp getEntryDate() {
return entryDate;
}

public void setEntryDate(Timestamp  entryDate) {
this.entryDate = entryDate;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public String getPerformedByPartyId() {
return performedByPartyId;
}

public void setPerformedByPartyId(String  performedByPartyId) {
this.performedByPartyId = performedByPartyId;
}

public String getReasonEnumId() {
return reasonEnumId;
}

public void setReasonEnumId(String  reasonEnumId) {
this.reasonEnumId = reasonEnumId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}


public Map<String, Object> mapAttributeField() {
return FinAccountTransMapper.map(this);
}
}
