package com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.mapper.ProductPaymentMethodTypeMapper;

public class ProductPaymentMethodType implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String paymentMethodTypeId;
private String productPricePurposeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getProductPricePurposeId() {
return productPricePurposeId;
}

public void setProductPricePurposeId(String  productPricePurposeId) {
this.productPricePurposeId = productPricePurposeId;
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

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProductPaymentMethodTypeMapper.map(this);
}
}
