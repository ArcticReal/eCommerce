package com.skytala.eCommerce.domain.content.relations.webSiteContentType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSiteContentType.model.WebSiteContentType;

public class WebSiteContentTypeMapper  {


	public static Map<String, Object> map(WebSiteContentType websitecontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(websitecontenttype.getWebSiteContentTypeId() != null ){
			returnVal.put("webSiteContentTypeId",websitecontenttype.getWebSiteContentTypeId());
}

		if(websitecontenttype.getDescription() != null ){
			returnVal.put("description",websitecontenttype.getDescription());
}

		if(websitecontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",websitecontenttype.getParentTypeId());
}

		if(websitecontenttype.getHasTable() != null ){
			returnVal.put("hasTable",websitecontenttype.getHasTable());
}

		return returnVal;
}


	public static WebSiteContentType map(Map<String, Object> fields) {

		WebSiteContentType returnVal = new WebSiteContentType();

		if(fields.get("webSiteContentTypeId") != null) {
			returnVal.setWebSiteContentTypeId((String) fields.get("webSiteContentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}


		return returnVal;
 } 
	public static WebSiteContentType mapstrstr(Map<String, String> fields) throws Exception {

		WebSiteContentType returnVal = new WebSiteContentType();

		if(fields.get("webSiteContentTypeId") != null) {
			returnVal.setWebSiteContentTypeId((String) fields.get("webSiteContentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
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


		return returnVal;
 } 
	public static WebSiteContentType map(GenericValue val) {

WebSiteContentType returnVal = new WebSiteContentType();
		returnVal.setWebSiteContentTypeId(val.getString("webSiteContentTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));


return returnVal;

}

public static WebSiteContentType map(HttpServletRequest request) throws Exception {

		WebSiteContentType returnVal = new WebSiteContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("webSiteContentTypeId")) {
returnVal.setWebSiteContentTypeId(request.getParameter("webSiteContentTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
return returnVal;

}
}
