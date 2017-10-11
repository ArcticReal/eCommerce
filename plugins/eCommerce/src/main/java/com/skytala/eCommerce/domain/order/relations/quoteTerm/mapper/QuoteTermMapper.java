package com.skytala.eCommerce.domain.order.relations.quoteTerm.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.model.QuoteTerm;

public class QuoteTermMapper  {


	public static Map<String, Object> map(QuoteTerm quoteterm) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quoteterm.getTermTypeId() != null ){
			returnVal.put("termTypeId",quoteterm.getTermTypeId());
}

		if(quoteterm.getQuoteId() != null ){
			returnVal.put("quoteId",quoteterm.getQuoteId());
}

		if(quoteterm.getQuoteItemSeqId() != null ){
			returnVal.put("quoteItemSeqId",quoteterm.getQuoteItemSeqId());
}

		if(quoteterm.getTermValue() != null ){
			returnVal.put("termValue",quoteterm.getTermValue());
}

		if(quoteterm.getUomId() != null ){
			returnVal.put("uomId",quoteterm.getUomId());
}

		if(quoteterm.getTermDays() != null ){
			returnVal.put("termDays",quoteterm.getTermDays());
}

		if(quoteterm.getTextValue() != null ){
			returnVal.put("textValue",quoteterm.getTextValue());
}

		if(quoteterm.getDescription() != null ){
			returnVal.put("description",quoteterm.getDescription());
}

		return returnVal;
}


	public static QuoteTerm map(Map<String, Object> fields) {

		QuoteTerm returnVal = new QuoteTerm();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
}

		if(fields.get("termValue") != null) {
			returnVal.setTermValue((long) fields.get("termValue"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("termDays") != null) {
			returnVal.setTermDays((long) fields.get("termDays"));
}

		if(fields.get("textValue") != null) {
			returnVal.setTextValue((String) fields.get("textValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuoteTerm mapstrstr(Map<String, String> fields) throws Exception {

		QuoteTerm returnVal = new QuoteTerm();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
}

		if(fields.get("termValue") != null) {
String buf;
buf = fields.get("termValue");
long ibuf = Long.parseLong(buf);
			returnVal.setTermValue(ibuf);
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("termDays") != null) {
String buf;
buf = fields.get("termDays");
long ibuf = Long.parseLong(buf);
			returnVal.setTermDays(ibuf);
}

		if(fields.get("textValue") != null) {
			returnVal.setTextValue((String) fields.get("textValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuoteTerm map(GenericValue val) {

QuoteTerm returnVal = new QuoteTerm();
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setQuoteItemSeqId(val.getString("quoteItemSeqId"));
		returnVal.setTermValue(val.getLong("termValue"));
		returnVal.setUomId(val.getString("uomId"));
		returnVal.setTermDays(val.getLong("termDays"));
		returnVal.setTextValue(val.getString("textValue"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static QuoteTerm map(HttpServletRequest request) throws Exception {

		QuoteTerm returnVal = new QuoteTerm();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("termTypeId")) {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
}

		if(paramMap.containsKey("quoteId"))  {
returnVal.setQuoteId(request.getParameter("quoteId"));
}
		if(paramMap.containsKey("quoteItemSeqId"))  {
returnVal.setQuoteItemSeqId(request.getParameter("quoteItemSeqId"));
}
		if(paramMap.containsKey("termValue"))  {
String buf = request.getParameter("termValue");
Long ibuf = Long.parseLong(buf);
returnVal.setTermValue(ibuf);
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
		if(paramMap.containsKey("termDays"))  {
String buf = request.getParameter("termDays");
Long ibuf = Long.parseLong(buf);
returnVal.setTermDays(ibuf);
}
		if(paramMap.containsKey("textValue"))  {
returnVal.setTextValue(request.getParameter("textValue"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
