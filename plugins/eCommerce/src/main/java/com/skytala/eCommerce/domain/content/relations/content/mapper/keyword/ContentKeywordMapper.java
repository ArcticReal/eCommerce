package com.skytala.eCommerce.domain.content.relations.content.mapper.keyword;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;

public class ContentKeywordMapper  {


	public static Map<String, Object> map(ContentKeyword contentkeyword) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentkeyword.getContentId() != null ){
			returnVal.put("contentId",contentkeyword.getContentId());
}

		if(contentkeyword.getKeyword() != null ){
			returnVal.put("keyword",contentkeyword.getKeyword());
}

		if(contentkeyword.getRelevancyWeight() != null ){
			returnVal.put("relevancyWeight",contentkeyword.getRelevancyWeight());
}

		return returnVal;
}


	public static ContentKeyword map(Map<String, Object> fields) {

		ContentKeyword returnVal = new ContentKeyword();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
}

		if(fields.get("relevancyWeight") != null) {
			returnVal.setRelevancyWeight((long) fields.get("relevancyWeight"));
}


		return returnVal;
 } 
	public static ContentKeyword mapstrstr(Map<String, String> fields) throws Exception {

		ContentKeyword returnVal = new ContentKeyword();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
}

		if(fields.get("relevancyWeight") != null) {
String buf;
buf = fields.get("relevancyWeight");
long ibuf = Long.parseLong(buf);
			returnVal.setRelevancyWeight(ibuf);
}


		return returnVal;
 } 
	public static ContentKeyword map(GenericValue val) {

ContentKeyword returnVal = new ContentKeyword();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setKeyword(val.getString("keyword"));
		returnVal.setRelevancyWeight(val.getLong("relevancyWeight"));


return returnVal;

}

public static ContentKeyword map(HttpServletRequest request) throws Exception {

		ContentKeyword returnVal = new ContentKeyword();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("keyword"))  {
returnVal.setKeyword(request.getParameter("keyword"));
}
		if(paramMap.containsKey("relevancyWeight"))  {
String buf = request.getParameter("relevancyWeight");
Long ibuf = Long.parseLong(buf);
returnVal.setRelevancyWeight(ibuf);
}
return returnVal;

}
}
