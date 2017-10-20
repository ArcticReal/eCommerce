package com.skytala.eCommerce.domain.order.relations.quote.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.model.attribute.QuoteAttribute;

public class QuoteAttributeMapper  {


	public static Map<String, Object> map(QuoteAttribute quoteattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quoteattribute.getQuoteId() != null ){
			returnVal.put("quoteId",quoteattribute.getQuoteId());
}

		if(quoteattribute.getAttrName() != null ){
			returnVal.put("attrName",quoteattribute.getAttrName());
}

		if(quoteattribute.getAttrValue() != null ){
			returnVal.put("attrValue",quoteattribute.getAttrValue());
}

		if(quoteattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",quoteattribute.getAttrDescription());
}

		return returnVal;
}


	public static QuoteAttribute map(Map<String, Object> fields) {

		QuoteAttribute returnVal = new QuoteAttribute();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
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
	public static QuoteAttribute mapstrstr(Map<String, String> fields) throws Exception {

		QuoteAttribute returnVal = new QuoteAttribute();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
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
	public static QuoteAttribute map(GenericValue val) {

QuoteAttribute returnVal = new QuoteAttribute();
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static QuoteAttribute map(HttpServletRequest request) throws Exception {

		QuoteAttribute returnVal = new QuoteAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteId")) {
returnVal.setQuoteId(request.getParameter("quoteId"));
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
