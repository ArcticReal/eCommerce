package com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.model.QuoteTypeAttr;

public class QuoteTypeAttrMapper  {


	public static Map<String, Object> map(QuoteTypeAttr quotetypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quotetypeattr.getQuoteTypeId() != null ){
			returnVal.put("quoteTypeId",quotetypeattr.getQuoteTypeId());
}

		if(quotetypeattr.getAttrName() != null ){
			returnVal.put("attrName",quotetypeattr.getAttrName());
}

		if(quotetypeattr.getDescription() != null ){
			returnVal.put("description",quotetypeattr.getDescription());
}

		return returnVal;
}


	public static QuoteTypeAttr map(Map<String, Object> fields) {

		QuoteTypeAttr returnVal = new QuoteTypeAttr();

		if(fields.get("quoteTypeId") != null) {
			returnVal.setQuoteTypeId((String) fields.get("quoteTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuoteTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		QuoteTypeAttr returnVal = new QuoteTypeAttr();

		if(fields.get("quoteTypeId") != null) {
			returnVal.setQuoteTypeId((String) fields.get("quoteTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuoteTypeAttr map(GenericValue val) {

QuoteTypeAttr returnVal = new QuoteTypeAttr();
		returnVal.setQuoteTypeId(val.getString("quoteTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static QuoteTypeAttr map(HttpServletRequest request) throws Exception {

		QuoteTypeAttr returnVal = new QuoteTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteTypeId")) {
returnVal.setQuoteTypeId(request.getParameter("quoteTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
