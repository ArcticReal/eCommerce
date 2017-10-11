package com.skytala.eCommerce.domain.order.relations.orderNotification.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderNotification.mapper.OrderNotificationMapper;

public class OrderNotification implements Serializable{

private static final long serialVersionUID = 1L;
private String orderNotificationId;
private String orderId;
private String emailType;
private String comments;
private Timestamp notificationDate;

public String getOrderNotificationId() {
return orderNotificationId;
}

public void setOrderNotificationId(String  orderNotificationId) {
this.orderNotificationId = orderNotificationId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getEmailType() {
return emailType;
}

public void setEmailType(String  emailType) {
this.emailType = emailType;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Timestamp getNotificationDate() {
return notificationDate;
}

public void setNotificationDate(Timestamp  notificationDate) {
this.notificationDate = notificationDate;
}


public Map<String, Object> mapAttributeField() {
return OrderNotificationMapper.map(this);
}
}
