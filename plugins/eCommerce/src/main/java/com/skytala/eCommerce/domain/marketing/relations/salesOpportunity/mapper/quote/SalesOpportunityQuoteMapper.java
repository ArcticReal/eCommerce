package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.quote;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.quote.SalesOpportunityQuote;

public class SalesOpportunityQuoteMapper  {


	public static Map<String, Object> map(SalesOpportunityQuote salesopportunityquote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunityquote.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",salesopportunityquote.getSalesOpportunityId());
}

		if(salesopportunityquote.getQuoteId() != null ){
			returnVal.put("quoteId",salesopportunityquote.getQuoteId());
}

		return returnVal;
}


	public static SalesOpportunityQuote map(Map<String, Object> fields) {

		SalesOpportunityQuote returnVal = new SalesOpportunityQuote();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}


		return returnVal;
 } 
	public static SalesOpportunityQuote mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunityQuote returnVal = new SalesOpportunityQuote();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}


		return returnVal;
 } 
	public static SalesOpportunityQuote map(GenericValue val) {

SalesOpportunityQuote returnVal = new SalesOpportunityQuote();
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setQuoteId(val.getString("quoteId"));


return returnVal;

}

public static SalesOpportunityQuote map(HttpServletRequest request) throws Exception {

		SalesOpportunityQuote returnVal = new SalesOpportunityQuote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesOpportunityId")) {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}

		if(paramMap.containsKey("quoteId"))  {
returnVal.setQuoteId(request.getParameter("quoteId"));
}
return returnVal;

}
}
