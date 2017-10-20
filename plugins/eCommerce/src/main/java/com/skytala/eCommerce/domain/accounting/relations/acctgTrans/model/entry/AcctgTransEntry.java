package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entry.AcctgTransEntryMapper;

public class AcctgTransEntry implements Serializable{

private static final long serialVersionUID = 1L;
private String acctgTransId;
private String acctgTransEntrySeqId;
private String acctgTransEntryTypeId;
private String description;
private String voucherRef;
private String partyId;
private String roleTypeId;
private String theirPartyId;
private String productId;
private String theirProductId;
private String inventoryItemId;
private String glAccountTypeId;
private String glAccountId;
private String organizationPartyId;
private BigDecimal amount;
private String currencyUomId;
private BigDecimal origAmount;
private String origCurrencyUomId;
private Boolean debitCreditFlag;
private Timestamp dueDate;
private String groupId;
private String taxId;
private String reconcileStatusId;
private String settlementTermId;
private Boolean isSummary;

public String getAcctgTransId() {
return acctgTransId;
}

public void setAcctgTransId(String  acctgTransId) {
this.acctgTransId = acctgTransId;
}

public String getAcctgTransEntrySeqId() {
return acctgTransEntrySeqId;
}

public void setAcctgTransEntrySeqId(String  acctgTransEntrySeqId) {
this.acctgTransEntrySeqId = acctgTransEntrySeqId;
}

public String getAcctgTransEntryTypeId() {
return acctgTransEntryTypeId;
}

public void setAcctgTransEntryTypeId(String  acctgTransEntryTypeId) {
this.acctgTransEntryTypeId = acctgTransEntryTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getVoucherRef() {
return voucherRef;
}

public void setVoucherRef(String  voucherRef) {
this.voucherRef = voucherRef;
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

public String getTheirPartyId() {
return theirPartyId;
}

public void setTheirPartyId(String  theirPartyId) {
this.theirPartyId = theirPartyId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getTheirProductId() {
return theirProductId;
}

public void setTheirProductId(String  theirProductId) {
this.theirProductId = theirProductId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getGlAccountTypeId() {
return glAccountTypeId;
}

public void setGlAccountTypeId(String  glAccountTypeId) {
this.glAccountTypeId = glAccountTypeId;
}

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public BigDecimal getOrigAmount() {
return origAmount;
}

public void setOrigAmount(BigDecimal  origAmount) {
this.origAmount = origAmount;
}

public String getOrigCurrencyUomId() {
return origCurrencyUomId;
}

public void setOrigCurrencyUomId(String  origCurrencyUomId) {
this.origCurrencyUomId = origCurrencyUomId;
}

public Boolean getDebitCreditFlag() {
return debitCreditFlag;
}

public void setDebitCreditFlag(Boolean  debitCreditFlag) {
this.debitCreditFlag = debitCreditFlag;
}

public Timestamp getDueDate() {
return dueDate;
}

public void setDueDate(Timestamp  dueDate) {
this.dueDate = dueDate;
}

public String getGroupId() {
return groupId;
}

public void setGroupId(String  groupId) {
this.groupId = groupId;
}

public String getTaxId() {
return taxId;
}

public void setTaxId(String  taxId) {
this.taxId = taxId;
}

public String getReconcileStatusId() {
return reconcileStatusId;
}

public void setReconcileStatusId(String  reconcileStatusId) {
this.reconcileStatusId = reconcileStatusId;
}

public String getSettlementTermId() {
return settlementTermId;
}

public void setSettlementTermId(String  settlementTermId) {
this.settlementTermId = settlementTermId;
}

public Boolean getIsSummary() {
return isSummary;
}

public void setIsSummary(Boolean  isSummary) {
this.isSummary = isSummary;
}


public Map<String, Object> mapAttributeField() {
return AcctgTransEntryMapper.map(this);
}
}
