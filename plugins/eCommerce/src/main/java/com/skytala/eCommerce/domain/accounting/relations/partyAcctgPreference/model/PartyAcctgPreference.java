package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.mapper.PartyAcctgPreferenceMapper;

public class PartyAcctgPreference implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private Long fiscalYearStartMonth;
private Long fiscalYearStartDay;
private String taxFormId;
private String cogsMethodId;
private String baseCurrencyUomId;
private String invoiceSeqCustMethId;
private String invoiceIdPrefix;
private Long lastInvoiceNumber;
private Timestamp lastInvoiceRestartDate;
private Boolean useInvoiceIdForReturns;
private String quoteSeqCustMethId;
private String quoteIdPrefix;
private Long lastQuoteNumber;
private String orderSeqCustMethId;
private String orderIdPrefix;
private Long lastOrderNumber;
private String refundPaymentMethodId;
private String errorGlJournalId;
private String oldInvoiceSequenceEnumId;
private String oldOrderSequenceEnumId;
private String oldQuoteSequenceEnumId;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Long getFiscalYearStartMonth() {
return fiscalYearStartMonth;
}

public void setFiscalYearStartMonth(Long  fiscalYearStartMonth) {
this.fiscalYearStartMonth = fiscalYearStartMonth;
}

public Long getFiscalYearStartDay() {
return fiscalYearStartDay;
}

public void setFiscalYearStartDay(Long  fiscalYearStartDay) {
this.fiscalYearStartDay = fiscalYearStartDay;
}

public String getTaxFormId() {
return taxFormId;
}

public void setTaxFormId(String  taxFormId) {
this.taxFormId = taxFormId;
}

public String getCogsMethodId() {
return cogsMethodId;
}

public void setCogsMethodId(String  cogsMethodId) {
this.cogsMethodId = cogsMethodId;
}

public String getBaseCurrencyUomId() {
return baseCurrencyUomId;
}

public void setBaseCurrencyUomId(String  baseCurrencyUomId) {
this.baseCurrencyUomId = baseCurrencyUomId;
}

public String getInvoiceSeqCustMethId() {
return invoiceSeqCustMethId;
}

public void setInvoiceSeqCustMethId(String  invoiceSeqCustMethId) {
this.invoiceSeqCustMethId = invoiceSeqCustMethId;
}

public String getInvoiceIdPrefix() {
return invoiceIdPrefix;
}

public void setInvoiceIdPrefix(String  invoiceIdPrefix) {
this.invoiceIdPrefix = invoiceIdPrefix;
}

public Long getLastInvoiceNumber() {
return lastInvoiceNumber;
}

public void setLastInvoiceNumber(Long  lastInvoiceNumber) {
this.lastInvoiceNumber = lastInvoiceNumber;
}

public Timestamp getLastInvoiceRestartDate() {
return lastInvoiceRestartDate;
}

public void setLastInvoiceRestartDate(Timestamp  lastInvoiceRestartDate) {
this.lastInvoiceRestartDate = lastInvoiceRestartDate;
}

public Boolean getUseInvoiceIdForReturns() {
return useInvoiceIdForReturns;
}

public void setUseInvoiceIdForReturns(Boolean  useInvoiceIdForReturns) {
this.useInvoiceIdForReturns = useInvoiceIdForReturns;
}

public String getQuoteSeqCustMethId() {
return quoteSeqCustMethId;
}

public void setQuoteSeqCustMethId(String  quoteSeqCustMethId) {
this.quoteSeqCustMethId = quoteSeqCustMethId;
}

public String getQuoteIdPrefix() {
return quoteIdPrefix;
}

public void setQuoteIdPrefix(String  quoteIdPrefix) {
this.quoteIdPrefix = quoteIdPrefix;
}

public Long getLastQuoteNumber() {
return lastQuoteNumber;
}

public void setLastQuoteNumber(Long  lastQuoteNumber) {
this.lastQuoteNumber = lastQuoteNumber;
}

public String getOrderSeqCustMethId() {
return orderSeqCustMethId;
}

public void setOrderSeqCustMethId(String  orderSeqCustMethId) {
this.orderSeqCustMethId = orderSeqCustMethId;
}

public String getOrderIdPrefix() {
return orderIdPrefix;
}

public void setOrderIdPrefix(String  orderIdPrefix) {
this.orderIdPrefix = orderIdPrefix;
}

public Long getLastOrderNumber() {
return lastOrderNumber;
}

public void setLastOrderNumber(Long  lastOrderNumber) {
this.lastOrderNumber = lastOrderNumber;
}

public String getRefundPaymentMethodId() {
return refundPaymentMethodId;
}

public void setRefundPaymentMethodId(String  refundPaymentMethodId) {
this.refundPaymentMethodId = refundPaymentMethodId;
}

public String getErrorGlJournalId() {
return errorGlJournalId;
}

public void setErrorGlJournalId(String  errorGlJournalId) {
this.errorGlJournalId = errorGlJournalId;
}

public String getOldInvoiceSequenceEnumId() {
return oldInvoiceSequenceEnumId;
}

public void setOldInvoiceSequenceEnumId(String  oldInvoiceSequenceEnumId) {
this.oldInvoiceSequenceEnumId = oldInvoiceSequenceEnumId;
}

public String getOldOrderSequenceEnumId() {
return oldOrderSequenceEnumId;
}

public void setOldOrderSequenceEnumId(String  oldOrderSequenceEnumId) {
this.oldOrderSequenceEnumId = oldOrderSequenceEnumId;
}

public String getOldQuoteSequenceEnumId() {
return oldQuoteSequenceEnumId;
}

public void setOldQuoteSequenceEnumId(String  oldQuoteSequenceEnumId) {
this.oldQuoteSequenceEnumId = oldQuoteSequenceEnumId;
}


public Map<String, Object> mapAttributeField() {
return PartyAcctgPreferenceMapper.map(this);
}
}
