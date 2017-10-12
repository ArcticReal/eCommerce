package com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.mapper.PaymentBudgetAllocationMapper;

public class PaymentBudgetAllocation implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String budgetItemSeqId;
private String paymentId;
private BigDecimal amount;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getBudgetItemSeqId() {
return budgetItemSeqId;
}

public void setBudgetItemSeqId(String  budgetItemSeqId) {
this.budgetItemSeqId = budgetItemSeqId;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}


public Map<String, Object> mapAttributeField() {
return PaymentBudgetAllocationMapper.map(this);
}
}
