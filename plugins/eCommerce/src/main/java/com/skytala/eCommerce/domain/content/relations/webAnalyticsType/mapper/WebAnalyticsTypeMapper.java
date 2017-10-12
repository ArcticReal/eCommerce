package com.skytala.eCommerce.domain.content.relations.webAnalyticsType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.model.WebAnalyticsType;

public class WebAnalyticsTypeMapper  {


	public static Map<String, Object> map(WebAnalyticsType webanalyticstype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(webanalyticstype.getWebAnalyticsTypeId() != null ){
			returnVal.put("webAnalyticsTypeId",webanalyticstype.getWebAnalyticsTypeId());
}

		if(webanalyticstype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",webanalyticstype.getParentTypeId());
}

		if(webanalyticstype.getHasTable() != null ){
			returnVal.put("hasTable",webanalyticstype.getHasTable());
}

		if(webanalyticstype.getDescription() != null ){
			returnVal.put("description",webanalyticstype.getDescription());
}

		return returnVal;
}


	public static WebAnalyticsType map(Map<String, Object> fields) {

		WebAnalyticsType returnVal = new WebAnalyticsType();

		if(fields.get("webAnalyticsTypeId") != null) {
			returnVal.setWebAnalyticsTypeId((String) fields.get("webAnalyticsTypeId"));
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
	public static WebAnalyticsType mapstrstr(Map<String, String> fields) throws Exception {

		WebAnalyticsType returnVal = new WebAnalyticsType();

		if(fields.get("webAnalyticsTypeId") != null) {
			returnVal.setWebAnalyticsTypeId((String) fields.get("webAnalyticsTypeId"));
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
	public static WebAnalyticsType map(GenericValue val) {

WebAnalyticsType returnVal = new WebAnalyticsType();
		returnVal.setWebAnalyticsTypeId(val.getString("webAnalyticsTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WebAnalyticsType map(HttpServletRequest request) throws Exception {

		WebAnalyticsType returnVal = new WebAnalyticsType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("webAnalyticsTypeId")) {
returnVal.setWebAnalyticsTypeId(request.getParameter("webAnalyticsTypeId"));
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
