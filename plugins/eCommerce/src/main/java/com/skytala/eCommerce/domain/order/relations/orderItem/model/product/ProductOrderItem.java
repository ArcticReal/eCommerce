package com.skytala.eCommerce.domain.order.relations.orderItem.model.product;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.product.ProductOrderItemMapper;

public class ProductOrderItem implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private String engagementId;
private String engagementItemSeqId;
private String productId;

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

public String getEngagementId() {
return engagementId;
}

public void setEngagementId(String  engagementId) {
this.engagementId = engagementId;
}

public String getEngagementItemSeqId() {
return engagementItemSeqId;
}

public void setEngagementItemSeqId(String  engagementItemSeqId) {
this.engagementItemSeqId = engagementItemSeqId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}


public Map<String, Object> mapAttributeField() {
return ProductOrderItemMapper.map(this);
}
}
