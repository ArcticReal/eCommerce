package com.skytala.eCommerce.domain.order.relations.quote.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.QuoteMapper;

public class Quote implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteId;
private String quoteTypeId;
private String partyId;
private Timestamp issueDate;
private String statusId;
private String currencyUomId;
private String productStoreId;
private String salesChannelEnumId;
private Timestamp validFromDate;
private Timestamp validThruDate;
private String quoteName;
private String description;

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}

public String getQuoteTypeId() {
return quoteTypeId;
}

public void setQuoteTypeId(String  quoteTypeId) {
this.quoteTypeId = quoteTypeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Timestamp getIssueDate() {
return issueDate;
}

public void setIssueDate(Timestamp  issueDate) {
this.issueDate = issueDate;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getSalesChannelEnumId() {
return salesChannelEnumId;
}

public void setSalesChannelEnumId(String  salesChannelEnumId) {
this.salesChannelEnumId = salesChannelEnumId;
}

public Timestamp getValidFromDate() {
return validFromDate;
}

public void setValidFromDate(Timestamp  validFromDate) {
this.validFromDate = validFromDate;
}

public Timestamp getValidThruDate() {
return validThruDate;
}

public void setValidThruDate(Timestamp  validThruDate) {
this.validThruDate = validThruDate;
}

public String getQuoteName() {
return quoteName;
}

public void setQuoteName(String  quoteName) {
this.quoteName = quoteName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return QuoteMapper.map(this);
}
}
