package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.AcctgTransMapper;

public class AcctgTrans implements Serializable{

private static final long serialVersionUID = 1L;
private String acctgTransId;
private String acctgTransTypeId;
private String description;
private Timestamp transactionDate;
private Boolean isPosted;
private Timestamp postedDate;
private Timestamp scheduledPostingDate;
private String glJournalId;
private String glFiscalTypeId;
private String voucherRef;
private Timestamp voucherDate;
private String groupStatusId;
private String fixedAssetId;
private String inventoryItemId;
private String physicalInventoryId;
private String partyId;
private String roleTypeId;
private String invoiceId;
private String paymentId;
private String finAccountTransId;
private String shipmentId;
private String receiptId;
private String workEffortId;
private String theirAcctgTransId;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getAcctgTransId() {
return acctgTransId;
}

public void setAcctgTransId(String  acctgTransId) {
this.acctgTransId = acctgTransId;
}

public String getAcctgTransTypeId() {
return acctgTransTypeId;
}

public void setAcctgTransTypeId(String  acctgTransTypeId) {
this.acctgTransTypeId = acctgTransTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Timestamp getTransactionDate() {
return transactionDate;
}

public void setTransactionDate(Timestamp  transactionDate) {
this.transactionDate = transactionDate;
}

public Boolean getIsPosted() {
return isPosted;
}

public void setIsPosted(Boolean  isPosted) {
this.isPosted = isPosted;
}

public Timestamp getPostedDate() {
return postedDate;
}

public void setPostedDate(Timestamp  postedDate) {
this.postedDate = postedDate;
}

public Timestamp getScheduledPostingDate() {
return scheduledPostingDate;
}

public void setScheduledPostingDate(Timestamp  scheduledPostingDate) {
this.scheduledPostingDate = scheduledPostingDate;
}

public String getGlJournalId() {
return glJournalId;
}

public void setGlJournalId(String  glJournalId) {
this.glJournalId = glJournalId;
}

public String getGlFiscalTypeId() {
return glFiscalTypeId;
}

public void setGlFiscalTypeId(String  glFiscalTypeId) {
this.glFiscalTypeId = glFiscalTypeId;
}

public String getVoucherRef() {
return voucherRef;
}

public void setVoucherRef(String  voucherRef) {
this.voucherRef = voucherRef;
}

public Timestamp getVoucherDate() {
return voucherDate;
}

public void setVoucherDate(Timestamp  voucherDate) {
this.voucherDate = voucherDate;
}

public String getGroupStatusId() {
return groupStatusId;
}

public void setGroupStatusId(String  groupStatusId) {
this.groupStatusId = groupStatusId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getPhysicalInventoryId() {
return physicalInventoryId;
}

public void setPhysicalInventoryId(String  physicalInventoryId) {
this.physicalInventoryId = physicalInventoryId;
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

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public String getFinAccountTransId() {
return finAccountTransId;
}

public void setFinAccountTransId(String  finAccountTransId) {
this.finAccountTransId = finAccountTransId;
}

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getReceiptId() {
return receiptId;
}

public void setReceiptId(String  receiptId) {
this.receiptId = receiptId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getTheirAcctgTransId() {
return theirAcctgTransId;
}

public void setTheirAcctgTransId(String  theirAcctgTransId) {
this.theirAcctgTransId = theirAcctgTransId;
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
return AcctgTransMapper.map(this);
}
}
