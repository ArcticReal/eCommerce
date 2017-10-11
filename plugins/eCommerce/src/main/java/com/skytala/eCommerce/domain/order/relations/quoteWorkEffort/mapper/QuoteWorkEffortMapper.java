package com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.model.QuoteWorkEffort;

public class QuoteWorkEffortMapper  {


	public static Map<String, Object> map(QuoteWorkEffort quoteworkeffort) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quoteworkeffort.getQuoteId() != null ){
			returnVal.put("quoteId",quoteworkeffort.getQuoteId());
}

		if(quoteworkeffort.getWorkEffortId() != null ){
			returnVal.put("workEffortId",quoteworkeffort.getWorkEffortId());
}

		return returnVal;
}


	public static QuoteWorkEffort map(Map<String, Object> fields) {

		QuoteWorkEffort returnVal = new QuoteWorkEffort();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static QuoteWorkEffort mapstrstr(Map<String, String> fields) throws Exception {

		QuoteWorkEffort returnVal = new QuoteWorkEffort();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static QuoteWorkEffort map(GenericValue val) {

QuoteWorkEffort returnVal = new QuoteWorkEffort();
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static QuoteWorkEffort map(HttpServletRequest request) throws Exception {

		QuoteWorkEffort returnVal = new QuoteWorkEffort();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteId")) {
returnVal.setQuoteId(request.getParameter("quoteId"));
}

		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
