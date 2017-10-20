package com.skytala.eCommerce.domain.order.relations.returnItem.model.billing;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.billing.ReturnItemBillingMapper;

public class ReturnItemBilling implements Serializable{

private static final long serialVersionUID = 1L;
private String returnId;
private String returnItemSeqId;
private String invoiceId;
private String invoiceItemSeqId;
private String shipmentReceiptId;
private BigDecimal quantity;
private BigDecimal amount;

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
return ReturnItemBillingMapper.map(this);
}
}
