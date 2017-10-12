package com.skytala.eCommerce.domain.accounting.relations.glAccount.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.GlAccountMapper;

public class GlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountId;
private String glAccountTypeId;
private String glAccountClassId;
private String glResourceTypeId;
private String glXbrlClassId;
private String parentGlAccountId;
private String accountCode;
private String accountName;
private String description;
private String productId;
private String externalId;

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getGlAccountTypeId() {
return glAccountTypeId;
}

public void setGlAccountTypeId(String  glAccountTypeId) {
this.glAccountTypeId = glAccountTypeId;
}

public String getGlAccountClassId() {
return glAccountClassId;
}

public void setGlAccountClassId(String  glAccountClassId) {
this.glAccountClassId = glAccountClassId;
}

public String getGlResourceTypeId() {
return glResourceTypeId;
}

public void setGlResourceTypeId(String  glResourceTypeId) {
this.glResourceTypeId = glResourceTypeId;
}

public String getGlXbrlClassId() {
return glXbrlClassId;
}

public void setGlXbrlClassId(String  glXbrlClassId) {
this.glXbrlClassId = glXbrlClassId;
}

public String getParentGlAccountId() {
return parentGlAccountId;
}

public void setParentGlAccountId(String  parentGlAccountId) {
this.parentGlAccountId = parentGlAccountId;
}

public String getAccountCode() {
return accountCode;
}

public void setAccountCode(String  accountCode) {
this.accountCode = accountCode;
}

public String getAccountName() {
return accountName;
}

public void setAccountName(String  accountName) {
this.accountName = accountName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getExternalId() {
return externalId;
}

public void setExternalId(String  externalId) {
this.externalId = externalId;
}


public Map<String, Object> mapAttributeField() {
return GlAccountMapper.map(this);
}
}
