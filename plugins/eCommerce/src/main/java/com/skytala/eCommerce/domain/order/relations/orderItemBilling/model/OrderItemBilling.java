package com.skytala.eCommerce.domain.order.relations.orderItemBilling.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.mapper.OrderItemBillingMapper;

public class OrderItemBilling implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private String invoiceId;
private String invoiceItemSeqId;
private String itemIssuanceId;
private String shipmentReceiptId;
private BigDecimal quantity;
private BigDecimal amount;

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

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getInvoiceItemSeqId() {
return invoiceItemSeqId;
}

public void setInvoiceItemSeqId(String  invoiceItemSeqId) {
this.invoiceItemSeqId = invoiceItemSeqId;
}

public String getItemIssuanceId() {
return itemIssuanceId;
}

public void setItemIssuanceId(String  itemIssuanceId) {
this.itemIssuanceId = itemIssuanceId;
}

public String getShipmentReceiptId() {
return shipmentReceiptId;
}

public void setShipmentReceiptId(String  shipmentReceiptId) {
this.shipmentReceiptId = shipmentReceiptId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}


public Map<String, Object> mapAttributeField() {
return OrderItemBillingMapper.map(this);
}
}
