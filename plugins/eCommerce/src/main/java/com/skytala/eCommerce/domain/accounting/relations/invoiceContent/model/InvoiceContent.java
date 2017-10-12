package com.skytala.eCommerce.domain.accounting.relations.invoiceContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContent.mapper.InvoiceContentMapper;

public class InvoiceContent implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceId;
private String invoiceContentTypeId;
private String contentId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getInvoiceContentTypeId() {
return invoiceContentTypeId;
}

public void setInvoiceContentTypeId(String  invoiceContentTypeId) {
this.invoiceContentTypeId = invoiceContentTypeId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
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
return InvoiceContentMapper.map(this);
}
}
