package com.skytala.eCommerce.domain.glReconciliation.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.glReconciliation.mapper.GlReconciliationMapper;

public class GlReconciliation implements Serializable{

private static final long serialVersionUID = 1L;
private String glReconciliationId;
private String glReconciliationName;
private String description;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;
private String glAccountId;
private String statusId;
private String organizationPartyId;
private BigDecimal reconciledBalance;
private BigDecimal openingBalance;
private Timestamp reconciledDate;

public String getGlReconciliationId() {
return glReconciliationId;
}

public void setGlReconciliationId(String  glReconciliationId) {
this.glReconciliationId = glReconciliationId;
}

public String getGlReconciliationName() {
return glReconciliationName;
}

public void setGlReconciliationName(String  glReconciliationName) {
this.glReconciliationName = glReconciliationName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
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

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public BigDecimal getReconciledBalance() {
return reconciledBalance;
}

public void setReconciledBalance(BigDecimal  reconciledBalance) {
this.reconciledBalance = reconciledBalance;
}

public BigDecimal getOpeningBalance() {
return openingBalance;
}

public void setOpeningBalance(BigDecimal  openingBalance) {
this.openingBalance = openingBalance;
}

public Timestamp getReconciledDate() {
return reconciledDate;
}

public void setReconciledDate(Timestamp  reconciledDate) {
this.reconciledDate = reconciledDate;
}


public Map<String, Object> mapAttributeField() {
return GlReconciliationMapper.map(this);
}
}
