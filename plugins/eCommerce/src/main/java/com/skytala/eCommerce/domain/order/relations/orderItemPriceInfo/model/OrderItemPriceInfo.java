package com.skytala.eCommerce.domain.order.relations.orderItemPriceInfo.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemPriceInfo.mapper.OrderItemPriceInfoMapper;

public class OrderItemPriceInfo implements Serializable{

private static final long serialVersionUID = 1L;
private String orderItemPriceInfoId;
private String orderId;
private String orderItemSeqId;
private String productPriceRuleId;
private String productPriceActionSeqId;
private BigDecimal modifyAmount;
private String description;
private String rateCode;

public String getOrderItemPriceInfoId() {
return orderItemPriceInfoId;
}

public void setOrderItemPriceInfoId(String  orderItemPriceInfoId) {
this.orderItemPriceInfoId = orderItemPriceInfoId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public String getProductPriceRuleId() {
return productPriceRuleId;
}

public void setProductPriceRuleId(String  productPriceRuleId) {
this.productPriceRuleId = productPriceRuleId;
}

public String getProductPriceActionSeqId() {
return productPriceActionSeqId;
}

public void setProductPriceActionSeqId(String  productPriceActionSeqId) {
this.productPriceActionSeqId = productPriceActionSeqId;
}

public BigDecimal getModifyAmount() {
return modifyAmount;
}

public void setModifyAmount(BigDecimal  modifyAmount) {
this.modifyAmount = modifyAmount;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getRateCode() {
return rateCode;
}

public void setRateCode(String  rateCode) {
this.rateCode = rateCode;
}


public Map<String, Object> mapAttributeField() {
return OrderItemPriceInfoMapper.map(this);
}
}
