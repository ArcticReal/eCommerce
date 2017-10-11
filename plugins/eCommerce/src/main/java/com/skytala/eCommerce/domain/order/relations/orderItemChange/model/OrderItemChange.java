package com.skytala.eCommerce.domain.order.relations.orderItemChange.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemChange.mapper.OrderItemChangeMapper;

public class OrderItemChange implements Serializable{

private static final long serialVersionUID = 1L;
private String orderItemChangeId;
private String orderId;
private String orderItemSeqId;
private String changeTypeEnumId;
private Timestamp changeDatetime;
private String changeUserLogin;
private BigDecimal quantity;
private BigDecimal cancelQuantity;
private BigDecimal unitPrice;
private String itemDescription;
private String reasonEnumId;
private String changeComments;

public String getOrderItemChangeId() {
return orderItemChangeId;
}

public void setOrderItemChangeId(String  orderItemChangeId) {
this.orderItemChangeId = orderItemChangeId;
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

public String getChangeTypeEnumId() {
return changeTypeEnumId;
}

public void setChangeTypeEnumId(String  changeTypeEnumId) {
this.changeTypeEnumId = changeTypeEnumId;
}

public Timestamp getChangeDatetime() {
return changeDatetime;
}

public void setChangeDatetime(Timestamp  changeDatetime) {
this.changeDatetime = changeDatetime;
}

public String getChangeUserLogin() {
return changeUserLogin;
}

public void setChangeUserLogin(String  changeUserLogin) {
this.changeUserLogin = changeUserLogin;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getCancelQuantity() {
return cancelQuantity;
}

public void setCancelQuantity(BigDecimal  cancelQuantity) {
this.cancelQuantity = cancelQuantity;
}

public BigDecimal getUnitPrice() {
return unitPrice;
}

public void setUnitPrice(BigDecimal  unitPrice) {
this.unitPrice = unitPrice;
}

public String getItemDescription() {
return itemDescription;
}

public void setItemDescription(String  itemDescription) {
this.itemDescription = itemDescription;
}

public String getReasonEnumId() {
return reasonEnumId;
}

public void setReasonEnumId(String  reasonEnumId) {
this.reasonEnumId = reasonEnumId;
}

public String getChangeComments() {
return changeComments;
}

public void setChangeComments(String  changeComments) {
this.changeComments = changeComments;
}


public Map<String, Object> mapAttributeField() {
return OrderItemChangeMapper.map(this);
}
}
