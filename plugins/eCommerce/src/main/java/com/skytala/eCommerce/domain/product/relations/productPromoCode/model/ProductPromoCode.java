package com.skytala.eCommerce.domain.product.relations.productPromoCode.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPromoCode.mapper.ProductPromoCodeMapper;

public class ProductPromoCode implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoCodeId;
private String productPromoId;
private Boolean userEntered;
private Boolean requireEmailOrParty;
private Long useLimitPerCode;
private Long useLimitPerCustomer;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getProductPromoCodeId() {
return productPromoCodeId;
}

public void setProductPromoCodeId(String  productPromoCodeId) {
this.productPromoCodeId = productPromoCodeId;
}

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public Boolean getUserEntered() {
return userEntered;
}

public void setUserEntered(Boolean  userEntered) {
this.userEntered = userEntered;
}

public Boolean getRequireEmailOrParty() {
return requireEmailOrParty;
}

public void setRequireEmailOrParty(Boolean  requireEmailOrParty) {
this.requireEmailOrParty = requireEmailOrParty;
}

public Long getUseLimitPerCode() {
return useLimitPerCode;
}

public void setUseLimitPerCode(Long  useLimitPerCode) {
this.useLimitPerCode = useLimitPerCode;
}

public Long getUseLimitPerCustomer() {
return useLimitPerCustomer;
}

public void setUseLimitPerCustomer(Long  useLimitPerCustomer) {
this.useLimitPerCustomer = useLimitPerCustomer;
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
return ProductPromoCodeMapper.map(this);
}
}
