package com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.mapper.ProductStorePaymentSettingMapper;

public class ProductStorePaymentSetting implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreId;
private String paymentMethodTypeId;
private String paymentServiceTypeEnumId;
private Long paymentService;
private String paymentCustomMethodId;
private String paymentGatewayConfigId;
private Long paymentPropertiesPath;
private Boolean applyToAllProducts;

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getPaymentServiceTypeEnumId() {
return paymentServiceTypeEnumId;
}

public void setPaymentServiceTypeEnumId(String  paymentServiceTypeEnumId) {
this.paymentServiceTypeEnumId = paymentServiceTypeEnumId;
}

public Long getPaymentService() {
return paymentService;
}

public void setPaymentService(Long  paymentService) {
this.paymentService = paymentService;
}

public String getPaymentCustomMethodId() {
return paymentCustomMethodId;
}

public void setPaymentCustomMethodId(String  paymentCustomMethodId) {
this.paymentCustomMethodId = paymentCustomMethodId;
}

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public Long getPaymentPropertiesPath() {
return paymentPropertiesPath;
}

public void setPaymentPropertiesPath(Long  paymentPropertiesPath) {
this.paymentPropertiesPath = paymentPropertiesPath;
}

public Boolean getApplyToAllProducts() {
return applyToAllProducts;
}

public void setApplyToAllProducts(Boolean  applyToAllProducts) {
this.applyToAllProducts = applyToAllProducts;
}


public Map<String, Object> mapAttributeField() {
return ProductStorePaymentSettingMapper.map(this);
}
}
