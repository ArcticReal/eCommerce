package com.skytala.eCommerce.domain.order.relations.quote.mapper.coefficient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.model.coefficient.QuoteCoefficient;

public class QuoteCoefficientMapper  {


	public static Map<String, Object> map(QuoteCoefficient quotecoefficient) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quotecoefficient.getQuoteId() != null ){
			returnVal.put("quoteId",quotecoefficient.getQuoteId());
}

		if(quotecoefficient.getCoeffName() != null ){
			returnVal.put("coeffName",quotecoefficient.getCoeffName());
}

		if(quotecoefficient.getCoeffValue() != null ){
			returnVal.put("coeffValue",quotecoefficient.getCoeffValue());
}

		return returnVal;
}


	public static QuoteCoefficient map(Map<String, Object> fields) {

		QuoteCoefficient returnVal = new QuoteCoefficient();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("coeffName") != null) {
			returnVal.setCoeffName((String) fields.get("coeffName"));
}

		if(fields.get("coeffValue") != null) {
			returnVal.setCoeffValue((BigDecimal) fields.get("coeffValue"));
}


		return returnVal;
 } 
	public static QuoteCoefficient mapstrstr(Map<String, String> fields) throws Exception {

		QuoteCoefficient returnVal = new QuoteCoefficient();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("coeffName") != null) {
			returnVal.setCoeffName((String) fields.get("coeffName"));
}

		if(fields.get("coeffValue") != null) {
String buf;
buf = fields.get("coeffValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCoeffValue(bd);
}


		return returnVal;
 } 
	public static QuoteCoefficient map(GenericValue val) {

QuoteCoefficient returnVal = new QuoteCoefficient();
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setCoeffName(val.getString("coeffName"));
		returnVal.setCoeffValue(val.getBigDecimal("coeffValue"));


return returnVal;

}

public static QuoteCoefficient map(HttpServletRequest request) throws Exception {

		QuoteCoefficient returnVal = new QuoteCoefficient();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteId")) {
returnVal.setQuoteId(request.getParameter("quoteId"));
}

		if(paramMap.containsKey("coeffName"))  {
returnVal.setCoeffName(request.getParameter("coeffName"));
}
		if(paramMap.containsKey("coeffValue"))  {
String buf = request.getParameter("coeffValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCoeffValue(bd);
}
return returnVal;

}
}
