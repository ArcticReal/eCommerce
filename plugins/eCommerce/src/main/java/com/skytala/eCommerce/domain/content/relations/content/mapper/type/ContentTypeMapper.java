package com.skytala.eCommerce.domain.content.relations.content.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.model.type.ContentType;

public class ContentTypeMapper  {


	public static Map<String, Object> map(ContentType contenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contenttype.getContentTypeId() != null ){
			returnVal.put("contentTypeId",contenttype.getContentTypeId());
}

		if(contenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",contenttype.getParentTypeId());
}

		if(contenttype.getHasTable() != null ){
			returnVal.put("hasTable",contenttype.getHasTable());
}

		if(contenttype.getDescription() != null ){
			returnVal.put("description",contenttype.getDescription());
}

		return returnVal;
}


	public static ContentType map(Map<String, Object> fields) {

		ContentType returnVal = new ContentType();

		if(fields.get("contentTypeId") != null) {
			returnVal.setContentTypeId((String) fields.get("contentTypeId"));
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
	public static ContentType mapstrstr(Map<String, String> fields) throws Exception {

		ContentType returnVal = new ContentType();

		if(fields.get("contentTypeId") != null) {
			returnVal.setContentTypeId((String) fields.get("contentTypeId"));
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
	public static ContentType map(GenericValue val) {

ContentType returnVal = new ContentType();
		returnVal.setContentTypeId(val.getString("contentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContentType map(HttpServletRequest request) throws Exception {

		ContentType returnVal = new ContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentTypeId")) {
returnVal.setContentTypeId(request.getParameter("contentTypeId"));
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
