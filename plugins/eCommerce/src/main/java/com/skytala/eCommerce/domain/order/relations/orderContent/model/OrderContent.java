package com.skytala.eCommerce.domain.order.relations.orderContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderContent.mapper.OrderContentMapper;

public class OrderContent implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private String contentId;
private String orderContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

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

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getOrderContentTypeId() {
return orderContentTypeId;
}

public void setOrderContentTypeId(String  orderContentTypeId) {
this.orderContentTypeId = orderContentTypeId;
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


public Map<String, Object> mapAttributeField() {
return OrderContentMapper.map(this);
}
}
