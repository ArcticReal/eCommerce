package com.skytala.eCommerce.domain.content.relations.content.mapper.purpose;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;

public class ContentPurposeMapper  {


	public static Map<String, Object> map(ContentPurpose contentpurpose) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentpurpose.getContentId() != null ){
			returnVal.put("contentId",contentpurpose.getContentId());
}

		if(contentpurpose.getContentPurposeTypeId() != null ){
			returnVal.put("contentPurposeTypeId",contentpurpose.getContentPurposeTypeId());
}

		if(contentpurpose.getSequenceNum() != null ){
			returnVal.put("sequenceNum",contentpurpose.getSequenceNum());
}

		return returnVal;
}


	public static ContentPurpose map(Map<String, Object> fields) {

		ContentPurpose returnVal = new ContentPurpose();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ContentPurpose mapstrstr(Map<String, String> fields) throws Exception {

		ContentPurpose returnVal = new ContentPurpose();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ContentPurpose map(GenericValue val) {

ContentPurpose returnVal = new ContentPurpose();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setContentPurposeTypeId(val.getString("contentPurposeTypeId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ContentPurpose map(HttpServletRequest request) throws Exception {

		ContentPurpose returnVal = new ContentPurpose();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("contentPurposeTypeId"))  {
returnVal.setContentPurposeTypeId(request.getParameter("contentPurposeTypeId"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
