package com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.billing;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.billing.OrderAdjustmentBillingMapper;

public class OrderAdjustmentBilling implements Serializable{

private static final long serialVersionUID = 1L;
private String orderAdjustmentId;
private String invoiceId;
private String invoiceItemSeqId;
private BigDecimal amount;

public String getOrderAdjustmentId() {
return orderAdjustmentId;
}

public void setOrderAdjustmentId(String  orderAdjustmentId) {
this.orderAdjustmentId = orderAdjustmentId;
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

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}


public Map<String, Object> mapAttributeField() {
return OrderAdjustmentBillingMapper.map(this);
}
}
