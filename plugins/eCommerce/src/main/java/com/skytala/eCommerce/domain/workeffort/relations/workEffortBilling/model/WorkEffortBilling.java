package com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.mapper.WorkEffortBillingMapper;

public class WorkEffortBilling implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String invoiceId;
private String invoiceItemSeqId;
private BigDecimal percentage;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
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

public BigDecimal getPercentage() {
return percentage;
}

public void setPercentage(BigDecimal  percentage) {
this.percentage = percentage;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortBillingMapper.map(this);
}
}
