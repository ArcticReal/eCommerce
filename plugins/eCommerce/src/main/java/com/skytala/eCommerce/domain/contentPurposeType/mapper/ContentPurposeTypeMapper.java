package com.skytala.eCommerce.domain.contentPurposeType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contentPurposeType.model.ContentPurposeType;

public class ContentPurposeTypeMapper  {


	public static Map<String, Object> map(ContentPurposeType contentpurposetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentpurposetype.getContentPurposeTypeId() != null ){
			returnVal.put("contentPurposeTypeId",contentpurposetype.getContentPurposeTypeId());
}

		if(contentpurposetype.getDescription() != null ){
			returnVal.put("description",contentpurposetype.getDescription());
}

		return returnVal;
}


	public static ContentPurposeType map(Map<String, Object> fields) {

		ContentPurposeType returnVal = new ContentPurposeType();

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentPurposeType mapstrstr(Map<String, String> fields) throws Exception {

		ContentPurposeType returnVal = new ContentPurposeType();

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentPurposeType map(GenericValue val) {

ContentPurposeType returnVal = new ContentPurposeType();
		returnVal.setContentPurposeTypeId(val.getString("contentPurposeTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContentPurposeType map(HttpServletRequest request) throws Exception {

		ContentPurposeType returnVal = new ContentPurposeType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentPurposeTypeId")) {
returnVal.setContentPurposeTypeId(request.getParameter("contentPurposeTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
