package com.skytala.eCommerce.domain.order.relations.quote.mapper.note;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.model.note.QuoteNote;

public class QuoteNoteMapper  {


	public static Map<String, Object> map(QuoteNote quotenote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quotenote.getQuoteId() != null ){
			returnVal.put("quoteId",quotenote.getQuoteId());
}

		if(quotenote.getNoteId() != null ){
			returnVal.put("noteId",quotenote.getNoteId());
}

		return returnVal;
}


	public static QuoteNote map(Map<String, Object> fields) {

		QuoteNote returnVal = new QuoteNote();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static QuoteNote mapstrstr(Map<String, String> fields) throws Exception {

		QuoteNote returnVal = new QuoteNote();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static QuoteNote map(GenericValue val) {

QuoteNote returnVal = new QuoteNote();
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setNoteId(val.getString("noteId"));


return returnVal;

}

public static QuoteNote map(HttpServletRequest request) throws Exception {

		QuoteNote returnVal = new QuoteNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteId")) {
returnVal.setQuoteId(request.getParameter("quoteId"));
}

		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
return returnVal;

}
}
