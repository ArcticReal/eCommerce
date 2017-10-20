package com.skytala.eCommerce.domain.product.relations.product.model.promo;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoMapper;

public class ProductPromo implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoId;
private String promoName;
private String promoText;
private Boolean userEntered;
private Boolean showToCustomer;
private Boolean requireCode;
private Long useLimitPerOrder;
private Long useLimitPerCustomer;
private Long useLimitPerPromotion;
private BigDecimal billbackFactor;
private String overrideOrgPartyId;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public String getPromoName() {
return promoName;
}

public void setPromoName(String  promoName) {
this.promoName = promoName;
}

public String getPromoText() {
return promoText;
}

public void setPromoText(String  promoText) {
this.promoText = promoText;
}

public Boolean getUserEntered() {
return userEntered;
}

public void setUserEntered(Boolean  userEntered) {
this.userEntered = userEntered;
}

public Boolean getShowToCustomer() {
return showToCustomer;
}

public void setShowToCustomer(Boolean  showToCustomer) {
this.showToCustomer = showToCustomer;
}

public Boolean getRequireCode() {
return requireCode;
}

public void setRequireCode(Boolean  requireCode) {
this.requireCode = requireCode;
}

public Long getUseLimitPerOrder() {
return useLimitPerOrder;
}

public void setUseLimitPerOrder(Long  useLimitPerOrder) {
this.useLimitPerOrder = useLimitPerOrder;
}

public Long getUseLimitPerCustomer() {
return useLimitPerCustomer;
}

public void setUseLimitPerCustomer(Long  useLimitPerCustomer) {
this.useLimitPerCustomer = useLimitPerCustomer;
}

public Long getUseLimitPerPromotion() {
return useLimitPerPromotion;
}

public void setUseLimitPerPromotion(Long  useLimitPerPromotion) {
this.useLimitPerPromotion = useLimitPerPromotion;
}

public BigDecimal getBillbackFactor() {
return billbackFactor;
}

public void setBillbackFactor(BigDecimal  billbackFactor) {
this.billbackFactor = billbackFactor;
}

public String getOverrideOrgPartyId() {
return overrideOrgPartyId;
}

public void setOverrideOrgPartyId(String  overrideOrgPartyId) {
this.overrideOrgPartyId = overrideOrgPartyId;
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
return ProductPromoMapper.map(this);
}
}
