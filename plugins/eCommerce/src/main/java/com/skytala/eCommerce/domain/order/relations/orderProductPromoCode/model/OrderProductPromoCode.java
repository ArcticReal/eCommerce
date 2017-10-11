package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.mapper.OrderProductPromoCodeMapper;

public class OrderProductPromoCode implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String productPromoCodeId;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getProductPromoCodeId() {
return productPromoCodeId;
}

public void setProductPromoCodeId(String  productPromoCodeId) {
this.productPromoCodeId = productPromoCodeId;
}


public Map<String, Object> mapAttributeField() {
return OrderProductPromoCodeMapper.map(this);
}
}
