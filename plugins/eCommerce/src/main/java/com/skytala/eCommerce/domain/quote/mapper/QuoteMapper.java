package com.skytala.eCommerce.domain.quote.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.quote.model.Quote;

public class QuoteMapper  {


	public static Map<String, Object> map(Quote quote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quote.getQuoteId() != null ){
			returnVal.put("quoteId",quote.getQuoteId());
}

		if(quote.getQuoteTypeId() != null ){
			returnVal.put("quoteTypeId",quote.getQuoteTypeId());
}

		if(quote.getPartyId() != null ){
			returnVal.put("partyId",quote.getPartyId());
}

		if(quote.getIssueDate() != null ){
			returnVal.put("issueDate",quote.getIssueDate());
}

		if(quote.getStatusId() != null ){
			returnVal.put("statusId",quote.getStatusId());
}

		if(quote.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",quote.getCurrencyUomId());
}

		if(quote.getProductStoreId() != null ){
			returnVal.put("productStoreId",quote.getProductStoreId());
}

		if(quote.getSalesChannelEnumId() != null ){
			returnVal.put("salesChannelEnumId",quote.getSalesChannelEnumId());
}

		if(quote.getValidFromDate() != null ){
			returnVal.put("validFromDate",quote.getValidFromDate());
}

		if(quote.getValidThruDate() != null ){
			returnVal.put("validThruDate",quote.getValidThruDate());
}

		if(quote.getQuoteName() != null ){
			returnVal.put("quoteName",quote.getQuoteName());
}

		if(quote.getDescription() != null ){
			returnVal.put("description",quote.getDescription());
}

		return returnVal;
}


	public static Quote map(Map<String, Object> fields) {

		Quote returnVal = new Quote();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteTypeId") != null) {
			returnVal.setQuoteTypeId((String) fields.get("quoteTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("issueDate") != null) {
			returnVal.setIssueDate((Timestamp) fields.get("issueDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("salesChannelEnumId") != null) {
			returnVal.setSalesChannelEnumId((String) fields.get("salesChannelEnumId"));
}

		if(fields.get("validFromDate") != null) {
			returnVal.setValidFromDate((Timestamp) fields.get("validFromDate"));
}

		if(fields.get("validThruDate") != null) {
			returnVal.setValidThruDate((Timestamp) fields.get("validThruDate"));
}

		if(fields.get("quoteName") != null) {
			returnVal.setQuoteName((String) fields.get("quoteName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static Quote mapstrstr(Map<String, String> fields) throws Exception {

		Quote returnVal = new Quote();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteTypeId") != null) {
			returnVal.setQuoteTypeId((String) fields.get("quoteTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("issueDate") != null) {
String buf = fields.get("issueDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setIssueDate(ibuf);
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("salesChannelEnumId") != null) {
			returnVal.setSalesChannelEnumId((String) fields.get("salesChannelEnumId"));
}

		if(fields.get("validFromDate") != null) {
String buf = fields.get("validFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setValidFromDate(ibuf);
}

		if(fields.get("validThruDate") != null) {
String buf = fields.get("validThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setValidThruDate(ibuf);
}

		if(fields.get("quoteName") != null) {
			returnVal.setQuoteName((String) fields.get("quoteName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static Quote map(GenericValue val) {

Quote returnVal = new Quote();
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setQuoteTypeId(val.getString("quoteTypeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setIssueDate(val.getTimestamp("issueDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setSalesChannelEnumId(val.getString("salesChannelEnumId"));
		returnVal.setValidFromDate(val.getTimestamp("validFromDate"));
		returnVal.setValidThruDate(val.getTimestamp("validThruDate"));
		returnVal.setQuoteName(val.getString("quoteName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static Quote map(HttpServletRequest request) throws Exception {

		Quote returnVal = new Quote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteId")) {
returnVal.setQuoteId(request.getParameter("quoteId"));
}

		if(paramMap.containsKey("quoteTypeId"))  {
returnVal.setQuoteTypeId(request.getParameter("quoteTypeId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("issueDate"))  {
String buf = request.getParameter("issueDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setIssueDate(ibuf);
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("salesChannelEnumId"))  {
returnVal.setSalesChannelEnumId(request.getParameter("salesChannelEnumId"));
}
		if(paramMap.containsKey("validFromDate"))  {
String buf = request.getParameter("validFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setValidFromDate(ibuf);
}
		if(paramMap.containsKey("validThruDate"))  {
String buf = request.getParameter("validThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setValidThruDate(ibuf);
}
		if(paramMap.containsKey("quoteName"))  {
returnVal.setQuoteName(request.getParameter("quoteName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
