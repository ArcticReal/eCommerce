package com.skytala.eCommerce.domain.order.relations.returnItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.ReturnItemMapper;

public class ReturnItem implements Serializable{

private static final long serialVersionUID = 1L;
private String returnId;
private String returnItemSeqId;
private String returnReasonId;
private String returnTypeId;
private String returnItemTypeId;
private String productId;
private String description;
private String orderId;
private String orderItemSeqId;
private String statusId;
private String expectedItemStatus;
private BigDecimal returnQuantity;
private BigDecimal receivedQuantity;
private BigDecimal returnPrice;
private String returnItemResponseId;

public String getReturnId() {
return returnId;
}

public void setReturnId(String  returnId) {
this.returnId = returnId;
}

public String getReturnItemSeqId() {
return returnItemSeqId;
}

public void setReturnItemSeqId(String  returnItemSeqId) {
this.returnItemSeqId = returnItemSeqId;
}

public String getReturnReasonId() {
return returnReasonId;
}

public void setReturnReasonId(String  returnReasonId) {
this.returnReasonId = returnReasonId;
}

public String getReturnTypeId() {
return returnTypeId;
}

public void setReturnTypeId(String  returnTypeId) {
this.returnTypeId = returnTypeId;
}

public String getReturnItemTypeId() {
return returnItemTypeId;
}

public void setReturnItemTypeId(String  returnItemTypeId) {
this.returnItemTypeId = returnItemTypeId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
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

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getExpectedItemStatus() {
return expectedItemStatus;
}

public void setExpectedItemStatus(String  expectedItemStatus) {
this.expectedItemStatus = expectedItemStatus;
}

public BigDecimal getReturnQuantity() {
return returnQuantity;
}

public void setReturnQuantity(BigDecimal  returnQuantity) {
this.returnQuantity = returnQuantity;
}

public BigDecimal getReceivedQuantity() {
return receivedQuantity;
}

public void setReceivedQuantity(BigDecimal  receivedQuantity) {
this.receivedQuantity = receivedQuantity;
}

public BigDecimal getReturnPrice() {
return returnPrice;
}

public void setReturnPrice(BigDecimal  returnPrice) {
this.returnPrice = returnPrice;
}

public String getReturnItemResponseId() {
return returnItemResponseId;
}

public void setReturnItemResponseId(String  returnItemResponseId) {
this.returnItemResponseId = returnItemResponseId;
}


public Map<String, Object> mapAttributeField() {
return ReturnItemMapper.map(this);
}
}
