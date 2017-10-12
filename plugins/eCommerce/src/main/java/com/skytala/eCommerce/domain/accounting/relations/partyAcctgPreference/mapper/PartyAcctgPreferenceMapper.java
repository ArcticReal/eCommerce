package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;

public class PartyAcctgPreferenceMapper  {


	public static Map<String, Object> map(PartyAcctgPreference partyacctgpreference) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyacctgpreference.getPartyId() != null ){
			returnVal.put("partyId",partyacctgpreference.getPartyId());
}

		if(partyacctgpreference.getFiscalYearStartMonth() != null ){
			returnVal.put("fiscalYearStartMonth",partyacctgpreference.getFiscalYearStartMonth());
}

		if(partyacctgpreference.getFiscalYearStartDay() != null ){
			returnVal.put("fiscalYearStartDay",partyacctgpreference.getFiscalYearStartDay());
}

		if(partyacctgpreference.getTaxFormId() != null ){
			returnVal.put("taxFormId",partyacctgpreference.getTaxFormId());
}

		if(partyacctgpreference.getCogsMethodId() != null ){
			returnVal.put("cogsMethodId",partyacctgpreference.getCogsMethodId());
}

		if(partyacctgpreference.getBaseCurrencyUomId() != null ){
			returnVal.put("baseCurrencyUomId",partyacctgpreference.getBaseCurrencyUomId());
}

		if(partyacctgpreference.getInvoiceSeqCustMethId() != null ){
			returnVal.put("invoiceSeqCustMethId",partyacctgpreference.getInvoiceSeqCustMethId());
}

		if(partyacctgpreference.getInvoiceIdPrefix() != null ){
			returnVal.put("invoiceIdPrefix",partyacctgpreference.getInvoiceIdPrefix());
}

		if(partyacctgpreference.getLastInvoiceNumber() != null ){
			returnVal.put("lastInvoiceNumber",partyacctgpreference.getLastInvoiceNumber());
}

		if(partyacctgpreference.getLastInvoiceRestartDate() != null ){
			returnVal.put("lastInvoiceRestartDate",partyacctgpreference.getLastInvoiceRestartDate());
}

		if(partyacctgpreference.getUseInvoiceIdForReturns() != null ){
			returnVal.put("useInvoiceIdForReturns",partyacctgpreference.getUseInvoiceIdForReturns());
}

		if(partyacctgpreference.getQuoteSeqCustMethId() != null ){
			returnVal.put("quoteSeqCustMethId",partyacctgpreference.getQuoteSeqCustMethId());
}

		if(partyacctgpreference.getQuoteIdPrefix() != null ){
			returnVal.put("quoteIdPrefix",partyacctgpreference.getQuoteIdPrefix());
}

		if(partyacctgpreference.getLastQuoteNumber() != null ){
			returnVal.put("lastQuoteNumber",partyacctgpreference.getLastQuoteNumber());
}

		if(partyacctgpreference.getOrderSeqCustMethId() != null ){
			returnVal.put("orderSeqCustMethId",partyacctgpreference.getOrderSeqCustMethId());
}

		if(partyacctgpreference.getOrderIdPrefix() != null ){
			returnVal.put("orderIdPrefix",partyacctgpreference.getOrderIdPrefix());
}

		if(partyacctgpreference.getLastOrderNumber() != null ){
			returnVal.put("lastOrderNumber",partyacctgpreference.getLastOrderNumber());
}

		if(partyacctgpreference.getRefundPaymentMethodId() != null ){
			returnVal.put("refundPaymentMethodId",partyacctgpreference.getRefundPaymentMethodId());
}

		if(partyacctgpreference.getErrorGlJournalId() != null ){
			returnVal.put("errorGlJournalId",partyacctgpreference.getErrorGlJournalId());
}

		if(partyacctgpreference.getOldInvoiceSequenceEnumId() != null ){
			returnVal.put("oldInvoiceSequenceEnumId",partyacctgpreference.getOldInvoiceSequenceEnumId());
}

		if(partyacctgpreference.getOldOrderSequenceEnumId() != null ){
			returnVal.put("oldOrderSequenceEnumId",partyacctgpreference.getOldOrderSequenceEnumId());
}

		if(partyacctgpreference.getOldQuoteSequenceEnumId() != null ){
			returnVal.put("oldQuoteSequenceEnumId",partyacctgpreference.getOldQuoteSequenceEnumId());
}

		return returnVal;
}


	public static PartyAcctgPreference map(Map<String, Object> fields) {

		PartyAcctgPreference returnVal = new PartyAcctgPreference();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("fiscalYearStartMonth") != null) {
			returnVal.setFiscalYearStartMonth((long) fields.get("fiscalYearStartMonth"));
}

		if(fields.get("fiscalYearStartDay") != null) {
			returnVal.setFiscalYearStartDay((long) fields.get("fiscalYearStartDay"));
}

		if(fields.get("taxFormId") != null) {
			returnVal.setTaxFormId((String) fields.get("taxFormId"));
}

		if(fields.get("cogsMethodId") != null) {
			returnVal.setCogsMethodId((String) fields.get("cogsMethodId"));
}

		if(fields.get("baseCurrencyUomId") != null) {
			returnVal.setBaseCurrencyUomId((String) fields.get("baseCurrencyUomId"));
}

		if(fields.get("invoiceSeqCustMethId") != null) {
			returnVal.setInvoiceSeqCustMethId((String) fields.get("invoiceSeqCustMethId"));
}

		if(fields.get("invoiceIdPrefix") != null) {
			returnVal.setInvoiceIdPrefix((String) fields.get("invoiceIdPrefix"));
}

		if(fields.get("lastInvoiceNumber") != null) {
			returnVal.setLastInvoiceNumber((long) fields.get("lastInvoiceNumber"));
}

		if(fields.get("lastInvoiceRestartDate") != null) {
			returnVal.setLastInvoiceRestartDate((Timestamp) fields.get("lastInvoiceRestartDate"));
}

		if(fields.get("useInvoiceIdForReturns") != null) {
			returnVal.setUseInvoiceIdForReturns((boolean) fields.get("useInvoiceIdForReturns"));
}

		if(fields.get("quoteSeqCustMethId") != null) {
			returnVal.setQuoteSeqCustMethId((String) fields.get("quoteSeqCustMethId"));
}

		if(fields.get("quoteIdPrefix") != null) {
			returnVal.setQuoteIdPrefix((String) fields.get("quoteIdPrefix"));
}

		if(fields.get("lastQuoteNumber") != null) {
			returnVal.setLastQuoteNumber((long) fields.get("lastQuoteNumber"));
}

		if(fields.get("orderSeqCustMethId") != null) {
			returnVal.setOrderSeqCustMethId((String) fields.get("orderSeqCustMethId"));
}

		if(fields.get("orderIdPrefix") != null) {
			returnVal.setOrderIdPrefix((String) fields.get("orderIdPrefix"));
}

		if(fields.get("lastOrderNumber") != null) {
			returnVal.setLastOrderNumber((long) fields.get("lastOrderNumber"));
}

		if(fields.get("refundPaymentMethodId") != null) {
			returnVal.setRefundPaymentMethodId((String) fields.get("refundPaymentMethodId"));
}

		if(fields.get("errorGlJournalId") != null) {
			returnVal.setErrorGlJournalId((String) fields.get("errorGlJournalId"));
}

		if(fields.get("oldInvoiceSequenceEnumId") != null) {
			returnVal.setOldInvoiceSequenceEnumId((String) fields.get("oldInvoiceSequenceEnumId"));
}

		if(fields.get("oldOrderSequenceEnumId") != null) {
			returnVal.setOldOrderSequenceEnumId((String) fields.get("oldOrderSequenceEnumId"));
}

		if(fields.get("oldQuoteSequenceEnumId") != null) {
			returnVal.setOldQuoteSequenceEnumId((String) fields.get("oldQuoteSequenceEnumId"));
}


		return returnVal;
 } 
	public static PartyAcctgPreference mapstrstr(Map<String, String> fields) throws Exception {

		PartyAcctgPreference returnVal = new PartyAcctgPreference();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("fiscalYearStartMonth") != null) {
String buf;
buf = fields.get("fiscalYearStartMonth");
long ibuf = Long.parseLong(buf);
			returnVal.setFiscalYearStartMonth(ibuf);
}

		if(fields.get("fiscalYearStartDay") != null) {
String buf;
buf = fields.get("fiscalYearStartDay");
long ibuf = Long.parseLong(buf);
			returnVal.setFiscalYearStartDay(ibuf);
}

		if(fields.get("taxFormId") != null) {
			returnVal.setTaxFormId((String) fields.get("taxFormId"));
}

		if(fields.get("cogsMethodId") != null) {
			returnVal.setCogsMethodId((String) fields.get("cogsMethodId"));
}

		if(fields.get("baseCurrencyUomId") != null) {
			returnVal.setBaseCurrencyUomId((String) fields.get("baseCurrencyUomId"));
}

		if(fields.get("invoiceSeqCustMethId") != null) {
			returnVal.setInvoiceSeqCustMethId((String) fields.get("invoiceSeqCustMethId"));
}

		if(fields.get("invoiceIdPrefix") != null) {
			returnVal.setInvoiceIdPrefix((String) fields.get("invoiceIdPrefix"));
}

		if(fields.get("lastInvoiceNumber") != null) {
String buf;
buf = fields.get("lastInvoiceNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setLastInvoiceNumber(ibuf);
}

		if(fields.get("lastInvoiceRestartDate") != null) {
String buf = fields.get("lastInvoiceRestartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastInvoiceRestartDate(ibuf);
}

		if(fields.get("useInvoiceIdForReturns") != null) {
String buf;
buf = fields.get("useInvoiceIdForReturns");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setUseInvoiceIdForReturns(ibuf);
}

		if(fields.get("quoteSeqCustMethId") != null) {
			returnVal.setQuoteSeqCustMethId((String) fields.get("quoteSeqCustMethId"));
}

		if(fields.get("quoteIdPrefix") != null) {
			returnVal.setQuoteIdPrefix((String) fields.get("quoteIdPrefix"));
}

		if(fields.get("lastQuoteNumber") != null) {
String buf;
buf = fields.get("lastQuoteNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setLastQuoteNumber(ibuf);
}

		if(fields.get("orderSeqCustMethId") != null) {
			returnVal.setOrderSeqCustMethId((String) fields.get("orderSeqCustMethId"));
}

		if(fields.get("orderIdPrefix") != null) {
			returnVal.setOrderIdPrefix((String) fields.get("orderIdPrefix"));
}

		if(fields.get("lastOrderNumber") != null) {
String buf;
buf = fields.get("lastOrderNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setLastOrderNumber(ibuf);
}

		if(fields.get("refundPaymentMethodId") != null) {
			returnVal.setRefundPaymentMethodId((String) fields.get("refundPaymentMethodId"));
}

		if(fields.get("errorGlJournalId") != null) {
			returnVal.setErrorGlJournalId((String) fields.get("errorGlJournalId"));
}

		if(fields.get("oldInvoiceSequenceEnumId") != null) {
			returnVal.setOldInvoiceSequenceEnumId((String) fields.get("oldInvoiceSequenceEnumId"));
}

		if(fields.get("oldOrderSequenceEnumId") != null) {
			returnVal.setOldOrderSequenceEnumId((String) fields.get("oldOrderSequenceEnumId"));
}

		if(fields.get("oldQuoteSequenceEnumId") != null) {
			returnVal.setOldQuoteSequenceEnumId((String) fields.get("oldQuoteSequenceEnumId"));
}


		return returnVal;
 } 
	public static PartyAcctgPreference map(GenericValue val) {

PartyAcctgPreference returnVal = new PartyAcctgPreference();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setFiscalYearStartMonth(val.getLong("fiscalYearStartMonth"));
		returnVal.setFiscalYearStartDay(val.getLong("fiscalYearStartDay"));
		returnVal.setTaxFormId(val.getString("taxFormId"));
		returnVal.setCogsMethodId(val.getString("cogsMethodId"));
		returnVal.setBaseCurrencyUomId(val.getString("baseCurrencyUomId"));
		returnVal.setInvoiceSeqCustMethId(val.getString("invoiceSeqCustMethId"));
		returnVal.setInvoiceIdPrefix(val.getString("invoiceIdPrefix"));
		returnVal.setLastInvoiceNumber(val.getLong("lastInvoiceNumber"));
		returnVal.setLastInvoiceRestartDate(val.getTimestamp("lastInvoiceRestartDate"));
		returnVal.setUseInvoiceIdForReturns(val.getBoolean("useInvoiceIdForReturns"));
		returnVal.setQuoteSeqCustMethId(val.getString("quoteSeqCustMethId"));
		returnVal.setQuoteIdPrefix(val.getString("quoteIdPrefix"));
		returnVal.setLastQuoteNumber(val.getLong("lastQuoteNumber"));
		returnVal.setOrderSeqCustMethId(val.getString("orderSeqCustMethId"));
		returnVal.setOrderIdPrefix(val.getString("orderIdPrefix"));
		returnVal.setLastOrderNumber(val.getLong("lastOrderNumber"));
		returnVal.setRefundPaymentMethodId(val.getString("refundPaymentMethodId"));
		returnVal.setErrorGlJournalId(val.getString("errorGlJournalId"));
		returnVal.setOldInvoiceSequenceEnumId(val.getString("oldInvoiceSequenceEnumId"));
		returnVal.setOldOrderSequenceEnumId(val.getString("oldOrderSequenceEnumId"));
		returnVal.setOldQuoteSequenceEnumId(val.getString("oldQuoteSequenceEnumId"));


return returnVal;

}

public static PartyAcctgPreference map(HttpServletRequest request) throws Exception {

		PartyAcctgPreference returnVal = new PartyAcctgPreference();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("fiscalYearStartMonth"))  {
String buf = request.getParameter("fiscalYearStartMonth");
Long ibuf = Long.parseLong(buf);
returnVal.setFiscalYearStartMonth(ibuf);
}
		if(paramMap.containsKey("fiscalYearStartDay"))  {
String buf = request.getParameter("fiscalYearStartDay");
Long ibuf = Long.parseLong(buf);
returnVal.setFiscalYearStartDay(ibuf);
}
		if(paramMap.containsKey("taxFormId"))  {
returnVal.setTaxFormId(request.getParameter("taxFormId"));
}
		if(paramMap.containsKey("cogsMethodId"))  {
returnVal.setCogsMethodId(request.getParameter("cogsMethodId"));
}
		if(paramMap.containsKey("baseCurrencyUomId"))  {
returnVal.setBaseCurrencyUomId(request.getParameter("baseCurrencyUomId"));
}
		if(paramMap.containsKey("invoiceSeqCustMethId"))  {
returnVal.setInvoiceSeqCustMethId(request.getParameter("invoiceSeqCustMethId"));
}
		if(paramMap.containsKey("invoiceIdPrefix"))  {
returnVal.setInvoiceIdPrefix(request.getParameter("invoiceIdPrefix"));
}
		if(paramMap.containsKey("lastInvoiceNumber"))  {
String buf = request.getParameter("lastInvoiceNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setLastInvoiceNumber(ibuf);
}
		if(paramMap.containsKey("lastInvoiceRestartDate"))  {
String buf = request.getParameter("lastInvoiceRestartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastInvoiceRestartDate(ibuf);
}
		if(paramMap.containsKey("useInvoiceIdForReturns"))  {
String buf = request.getParameter("useInvoiceIdForReturns");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setUseInvoiceIdForReturns(ibuf);
}
		if(paramMap.containsKey("quoteSeqCustMethId"))  {
returnVal.setQuoteSeqCustMethId(request.getParameter("quoteSeqCustMethId"));
}
		if(paramMap.containsKey("quoteIdPrefix"))  {
returnVal.setQuoteIdPrefix(request.getParameter("quoteIdPrefix"));
}
		if(paramMap.containsKey("lastQuoteNumber"))  {
String buf = request.getParameter("lastQuoteNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setLastQuoteNumber(ibuf);
}
		if(paramMap.containsKey("orderSeqCustMethId"))  {
returnVal.setOrderSeqCustMethId(request.getParameter("orderSeqCustMethId"));
}
		if(paramMap.containsKey("orderIdPrefix"))  {
returnVal.setOrderIdPrefix(request.getParameter("orderIdPrefix"));
}
		if(paramMap.containsKey("lastOrderNumber"))  {
String buf = request.getParameter("lastOrderNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setLastOrderNumber(ibuf);
}
		if(paramMap.containsKey("refundPaymentMethodId"))  {
returnVal.setRefundPaymentMethodId(request.getParameter("refundPaymentMethodId"));
}
		if(paramMap.containsKey("errorGlJournalId"))  {
returnVal.setErrorGlJournalId(request.getParameter("errorGlJournalId"));
}
		if(paramMap.containsKey("oldInvoiceSequenceEnumId"))  {
returnVal.setOldInvoiceSequenceEnumId(request.getParameter("oldInvoiceSequenceEnumId"));
}
		if(paramMap.containsKey("oldOrderSequenceEnumId"))  {
returnVal.setOldOrderSequenceEnumId(request.getParameter("oldOrderSequenceEnumId"));
}
		if(paramMap.containsKey("oldQuoteSequenceEnumId"))  {
returnVal.setOldQuoteSequenceEnumId(request.getParameter("oldQuoteSequenceEnumId"));
}
return returnVal;

}
}
