package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.mapper.ValueLinkKeyMapper;

public class ValueLinkKey implements Serializable{

private static final long serialVersionUID = 1L;
private String merchantId;
private String publicKey;
private String privateKey;
private String exchangeKey;
private String workingKey;
private Long workingKeyIndex;
private String lastWorkingKey;
private Timestamp createdDate;
private String createdByTerminal;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByTerminal;
private String lastModifiedByUserLogin;

public String getMerchantId() {
return merchantId;
}

public void setMerchantId(String  merchantId) {
this.merchantId = merchantId;
}

public String getPublicKey() {
return publicKey;
}

public void setPublicKey(String  publicKey) {
this.publicKey = publicKey;
}

public String getPrivateKey() {
return privateKey;
}

public void setPrivateKey(String  privateKey) {
this.privateKey = privateKey;
}

public String getExchangeKey() {
return exchangeKey;
}

public void setExchangeKey(String  exchangeKey) {
this.exchangeKey = exchangeKey;
}

public String getWorkingKey() {
return workingKey;
}

public void setWorkingKey(String  workingKey) {
this.workingKey = workingKey;
}

public Long getWorkingKeyIndex() {
return workingKeyIndex;
}

public void setWorkingKeyIndex(Long  workingKeyIndex) {
this.workingKeyIndex = workingKeyIndex;
}

public String getLastWorkingKey() {
return lastWorkingKey;
}

public void setLastWorkingKey(String  lastWorkingKey) {
this.lastWorkingKey = lastWorkingKey;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp  createdDate) {
this.createdDate = createdDate;
}

public String getCreatedByTerminal() {
return createdByTerminal;
}

public void setCreatedByTerminal(String  createdByTerminal) {
this.createdByTerminal = createdByTerminal;
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

public String getLastModifiedByTerminal() {
return lastModifiedByTerminal;
}

public void setLastModifiedByTerminal(String  lastModifiedByTerminal) {
this.lastModifiedByTerminal = lastModifiedByTerminal;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return ValueLinkKeyMapper.map(this);
}
}
