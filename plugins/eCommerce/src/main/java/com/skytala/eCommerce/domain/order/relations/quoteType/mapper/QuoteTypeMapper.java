package com.skytala.eCommerce.domain.order.relations.quoteType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteType.model.QuoteType;

public class QuoteTypeMapper  {


	public static Map<String, Object> map(QuoteType quotetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quotetype.getQuoteTypeId() != null ){
			returnVal.put("quoteTypeId",quotetype.getQuoteTypeId());
}

		if(quotetype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",quotetype.getParentTypeId());
}

		if(quotetype.getHasTable() != null ){
			returnVal.put("hasTable",quotetype.getHasTable());
}

		if(quotetype.getDescription() != null ){
			returnVal.put("description",quotetype.getDescription());
}

		return returnVal;
}


	public static QuoteType map(Map<String, Object> fields) {

		QuoteType returnVal = new QuoteType();

		if(fields.get("quoteTypeId") != null) {
			returnVal.setQuoteTypeId((String) fields.get("quoteTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuoteType mapstrstr(Map<String, String> fields) throws Exception {

		QuoteType returnVal = new QuoteType();

		if(fields.get("quoteTypeId") != null) {
			returnVal.setQuoteTypeId((String) fields.get("quoteTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static QuoteType map(GenericValue val) {

QuoteType returnVal = new QuoteType();
		returnVal.setQuoteTypeId(val.getString("quoteTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static QuoteType map(HttpServletRequest request) throws Exception {

		QuoteType returnVal = new QuoteType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteTypeId")) {
returnVal.setQuoteTypeId(request.getParameter("quoteTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
