package com.skytala.eCommerce.domain.order.relations.quote.mapper.termAttribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.model.termAttribute.QuoteTermAttribute;

public class QuoteTermAttributeMapper  {


	public static Map<String, Object> map(QuoteTermAttribute quotetermattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quotetermattribute.getTermTypeId() != null ){
			returnVal.put("termTypeId",quotetermattribute.getTermTypeId());
}

		if(quotetermattribute.getQuoteId() != null ){
			returnVal.put("quoteId",quotetermattribute.getQuoteId());
}

		if(quotetermattribute.getQuoteItemSeqId() != null ){
			returnVal.put("quoteItemSeqId",quotetermattribute.getQuoteItemSeqId());
}

		if(quotetermattribute.getAttrName() != null ){
			returnVal.put("attrName",quotetermattribute.getAttrName());
}

		if(quotetermattribute.getAttrValue() != null ){
			returnVal.put("attrValue",quotetermattribute.getAttrValue());
}

		if(quotetermattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",quotetermattribute.getAttrDescription());
}

		return returnVal;
}


	public static QuoteTermAttribute map(Map<String, Object> fields) {

		QuoteTermAttribute returnVal = new QuoteTermAttribute();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static QuoteTermAttribute mapstrstr(Map<String, String> fields) throws Exception {

		QuoteTermAttribute returnVal = new QuoteTermAttribute();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static QuoteTermAttribute map(GenericValue val) {

QuoteTermAttribute returnVal = new QuoteTermAttribute();
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setQuoteItemSeqId(val.getString("quoteItemSeqId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static QuoteTermAttribute map(HttpServletRequest request) throws Exception {

		QuoteTermAttribute returnVal = new QuoteTermAttribute();

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
		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
