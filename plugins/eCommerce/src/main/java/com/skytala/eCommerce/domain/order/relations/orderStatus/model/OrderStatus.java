package com.skytala.eCommerce.domain.order.relations.orderStatus.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderStatus.mapper.OrderStatusMapper;

public class OrderStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String orderStatusId;
private String statusId;
private String orderId;
private String orderItemSeqId;
private String orderPaymentPreferenceId;
private Timestamp statusDatetime;
private String statusUserLogin;
private String changeReason;

public String getOrderStatusId() {
return orderStatusId;
}

public void setOrderStatusId(String  orderStatusId) {
this.orderStatusId = orderStatusId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
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

public String getOrderPaymentPreferenceId() {
return orderPaymentPreferenceId;
}

public void setOrderPaymentPreferenceId(String  orderPaymentPreferenceId) {
this.orderPaymentPreferenceId = orderPaymentPreferenceId;
}

public Timestamp getStatusDatetime() {
return statusDatetime;
}

public void setStatusDatetime(Timestamp  statusDatetime) {
this.statusDatetime = statusDatetime;
}

public String getStatusUserLogin() {
return statusUserLogin;
}

public void setStatusUserLogin(String  statusUserLogin) {
this.statusUserLogin = statusUserLogin;
}

public String getChangeReason() {
return changeReason;
}

public void setChangeReason(String  changeReason) {
this.changeReason = changeReason;
}


public Map<String, Object> mapAttributeField() {
return OrderStatusMapper.map(this);
}
}
