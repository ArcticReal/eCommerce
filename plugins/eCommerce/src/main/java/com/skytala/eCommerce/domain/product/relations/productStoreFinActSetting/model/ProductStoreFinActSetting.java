package com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.mapper.ProductStoreFinActSettingMapper;

public class ProductStoreFinActSetting implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreId;
private String finAccountTypeId;
private Boolean requirePinCode;
private Boolean validateGCFinAcct;
private Long accountCodeLength;
private Long pinCodeLength;
private Long accountValidDays;
private Long authValidDays;
private String purchaseSurveyId;
private String purchSurveySendTo;
private String purchSurveyCopyMe;
private Boolean allowAuthToNegative;
private BigDecimal minBalance;
private BigDecimal replenishThreshold;
private String replenishMethodEnumId;

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getFinAccountTypeId() {
return finAccountTypeId;
}

public void setFinAccountTypeId(String  finAccountTypeId) {
this.finAccountTypeId = finAccountTypeId;
}

public Boolean getRequirePinCode() {
return requirePinCode;
}

public void setRequirePinCode(Boolean  requirePinCode) {
this.requirePinCode = requirePinCode;
}

public Boolean getValidateGCFinAcct() {
return validateGCFinAcct;
}

public void setValidateGCFinAcct(Boolean  validateGCFinAcct) {
this.validateGCFinAcct = validateGCFinAcct;
}

public Long getAccountCodeLength() {
return accountCodeLength;
}

public void setAccountCodeLength(Long  accountCodeLength) {
this.accountCodeLength = accountCodeLength;
}

public Long getPinCodeLength() {
return pinCodeLength;
}

public void setPinCodeLength(Long  pinCodeLength) {
this.pinCodeLength = pinCodeLength;
}

public Long getAccountValidDays() {
return accountValidDays;
}

public void setAccountValidDays(Long  accountValidDays) {
this.accountValidDays = accountValidDays;
}

public Long getAuthValidDays() {
return authValidDays;
}

public void setAuthValidDays(Long  authValidDays) {
this.authValidDays = authValidDays;
}

public String getPurchaseSurveyId() {
return purchaseSurveyId;
}

public void setPurchaseSurveyId(String  purchaseSurveyId) {
this.purchaseSurveyId = purchaseSurveyId;
}

public String getPurchSurveySendTo() {
return purchSurveySendTo;
}

public void setPurchSurveySendTo(String  purchSurveySendTo) {
this.purchSurveySendTo = purchSurveySendTo;
}

public String getPurchSurveyCopyMe() {
return purchSurveyCopyMe;
}

public void setPurchSurveyCopyMe(String  purchSurveyCopyMe) {
this.purchSurveyCopyMe = purchSurveyCopyMe;
}

public Boolean getAllowAuthToNegative() {
return allowAuthToNegative;
}

public void setAllowAuthToNegative(Boolean  allowAuthToNegative) {
this.allowAuthToNegative = allowAuthToNegative;
}

public BigDecimal getMinBalance() {
return minBalance;
}

public void setMinBalance(BigDecimal  minBalance) {
this.minBalance = minBalance;
}

public BigDecimal getReplenishThreshold() {
return replenishThreshold;
}

public void setReplenishThreshold(BigDecimal  replenishThreshold) {
this.replenishThreshold = replenishThreshold;
}

public String getReplenishMethodEnumId() {
return replenishMethodEnumId;
}

public void setReplenishMethodEnumId(String  replenishMethodEnumId) {
this.replenishMethodEnumId = replenishMethodEnumId;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreFinActSettingMapper.map(this);
}
}
