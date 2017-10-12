package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;

public class WebSitePathAliasMapper  {


	public static Map<String, Object> map(WebSitePathAlias websitepathalias) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(websitepathalias.getWebSiteId() != null ){
			returnVal.put("webSiteId",websitepathalias.getWebSiteId());
}

		if(websitepathalias.getPathAlias() != null ){
			returnVal.put("pathAlias",websitepathalias.getPathAlias());
}

		if(websitepathalias.getFromDate() != null ){
			returnVal.put("fromDate",websitepathalias.getFromDate());
}

		if(websitepathalias.getThruDate() != null ){
			returnVal.put("thruDate",websitepathalias.getThruDate());
}

		if(websitepathalias.getAliasTo() != null ){
			returnVal.put("aliasTo",websitepathalias.getAliasTo());
}

		if(websitepathalias.getContentId() != null ){
			returnVal.put("contentId",websitepathalias.getContentId());
}

		if(websitepathalias.getMapKey() != null ){
			returnVal.put("mapKey",websitepathalias.getMapKey());
}

		return returnVal;
}


	public static WebSitePathAlias map(Map<String, Object> fields) {

		WebSitePathAlias returnVal = new WebSitePathAlias();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("pathAlias") != null) {
			returnVal.setPathAlias((String) fields.get("pathAlias"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("aliasTo") != null) {
			returnVal.setAliasTo((String) fields.get("aliasTo"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("mapKey") != null) {
			returnVal.setMapKey((String) fields.get("mapKey"));
}


		return returnVal;
 } 
	public static WebSitePathAlias mapstrstr(Map<String, String> fields) throws Exception {

		WebSitePathAlias returnVal = new WebSitePathAlias();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("pathAlias") != null) {
			returnVal.setPathAlias((String) fields.get("pathAlias"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("aliasTo") != null) {
			returnVal.setAliasTo((String) fields.get("aliasTo"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("mapKey") != null) {
			returnVal.setMapKey((String) fields.get("mapKey"));
}


		return returnVal;
 } 
	public static WebSitePathAlias map(GenericValue val) {

WebSitePathAlias returnVal = new WebSitePathAlias();
		returnVal.setWebSiteId(val.getString("webSiteId"));
		returnVal.setPathAlias(val.getString("pathAlias"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAliasTo(val.getString("aliasTo"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setMapKey(val.getString("mapKey"));


return returnVal;

}

public static WebSitePathAlias map(HttpServletRequest request) throws Exception {

		WebSitePathAlias returnVal = new WebSitePathAlias();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("webSiteId")) {
returnVal.setWebSiteId(request.getParameter("webSiteId"));
}

		if(paramMap.containsKey("pathAlias"))  {
returnVal.setPathAlias(request.getParameter("pathAlias"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("aliasTo"))  {
returnVal.setAliasTo(request.getParameter("aliasTo"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("mapKey"))  {
returnVal.setMapKey(request.getParameter("mapKey"));
}
return returnVal;

}
}
