package com.skytala.eCommerce.domain.finAccount.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.finAccount.mapper.FinAccountMapper;

public class FinAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountId;
private String finAccountTypeId;
private String statusId;
private String finAccountName;
private String finAccountCode;
private String finAccountPin;
private String currencyUomId;
private String organizationPartyId;
private String ownerPartyId;
private String postToGlAccountId;
private Timestamp fromDate;
private Timestamp thruDate;
private Boolean isRefundable;
private String replenishPaymentId;
private BigDecimal replenishLevel;
private BigDecimal actualBalance;
private BigDecimal availableBalance;

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public String getFinAccountTypeId() {
return finAccountTypeId;
}

public void setFinAccountTypeId(String  finAccountTypeId) {
this.finAccountTypeId = finAccountTypeId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getFinAccountName() {
return finAccountName;
}

public void setFinAccountName(String  finAccountName) {
this.finAccountName = finAccountName;
}

public String getFinAccountCode() {
return finAccountCode;
}

public void setFinAccountCode(String  finAccountCode) {
this.finAccountCode = finAccountCode;
}

public String getFinAccountPin() {
return finAccountPin;
}

public void setFinAccountPin(String  finAccountPin) {
this.finAccountPin = finAccountPin;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getOwnerPartyId() {
return ownerPartyId;
}

public void setOwnerPartyId(String  ownerPartyId) {
this.ownerPartyId = ownerPartyId;
}

public String getPostToGlAccountId() {
return postToGlAccountId;
}

public void setPostToGlAccountId(String  postToGlAccountId) {
this.postToGlAccountId = postToGlAccountId;
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

public Boolean getIsRefundable() {
return isRefundable;
}

public void setIsRefundable(Boolean  isRefundable) {
this.isRefundable = isRefundable;
}

public String getReplenishPaymentId() {
return replenishPaymentId;
}

public void setReplenishPaymentId(String  replenishPaymentId) {
this.replenishPaymentId = replenishPaymentId;
}

public BigDecimal getReplenishLevel() {
return replenishLevel;
}

public void setReplenishLevel(BigDecimal  replenishLevel) {
this.replenishLevel = replenishLevel;
}

public BigDecimal getActualBalance() {
return actualBalance;
}

public void setActualBalance(BigDecimal  actualBalance) {
this.actualBalance = actualBalance;
}

public BigDecimal getAvailableBalance() {
return availableBalance;
}

public void setAvailableBalance(BigDecimal  availableBalance) {
this.availableBalance = availableBalance;
}


public Map<String, Object> mapAttributeField() {
return FinAccountMapper.map(this);
}
}
