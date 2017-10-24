package com.skytala.eCommerce.domain.content.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.model.Content;

public class ContentMapper  {


	public static Map<String, Object> map(Content content) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(content.getContentId() != null ){
			returnVal.put("contentId",content.getContentId());
}

		if(content.getContentTypeId() != null ){
			returnVal.put("contentTypeId",content.getContentTypeId());
}

		if(content.getOwnerContentId() != null ){
			returnVal.put("ownerContentId",content.getOwnerContentId());
}

		if(content.getDecoratorContentId() != null ){
			returnVal.put("decoratorContentId",content.getDecoratorContentId());
}

		if(content.getInstanceOfContentId() != null ){
			returnVal.put("instanceOfContentId",content.getInstanceOfContentId());
}

		if(content.getDataResourceId() != null ){
			returnVal.put("dataResourceId",content.getDataResourceId());
}

		if(content.getTemplateDataResourceId() != null ){
			returnVal.put("templateDataResourceId",content.getTemplateDataResourceId());
}

		if(content.getDataSourceId() != null ){
			returnVal.put("dataSourceId",content.getDataSourceId());
}

		if(content.getStatusId() != null ){
			returnVal.put("statusId",content.getStatusId());
}

		if(content.getPrivilegeEnumId() != null ){
			returnVal.put("privilegeEnumId",content.getPrivilegeEnumId());
}

		if(content.getServiceName() != null ){
			returnVal.put("serviceName",content.getServiceName());
}

		if(content.getCustomMethodId() != null ){
			returnVal.put("customMethodId",content.getCustomMethodId());
}

		if(content.getContentName() != null ){
			returnVal.put("contentName",content.getContentName());
}

		if(content.getDescription() != null ){
			returnVal.put("description",content.getDescription());
}

		if(content.getLocaleString() != null ){
			returnVal.put("localeString",content.getLocaleString());
}

		if(content.getMimeTypeId() != null ){
			returnVal.put("mimeTypeId",content.getMimeTypeId());
}

		if(content.getCharacterSetId() != null ){
			returnVal.put("characterSetId",content.getCharacterSetId());
}

		if(content.getChildLeafCount() != null ){
			returnVal.put("childLeafCount",content.getChildLeafCount());
}

		if(content.getChildBranchCount() != null ){
			returnVal.put("childBranchCount",content.getChildBranchCount());
}

		if(content.getCreatedDate() != null ){
			returnVal.put("createdDate",content.getCreatedDate());
}

		if(content.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",content.getCreatedByUserLogin());
}

		if(content.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",content.getLastModifiedDate());
}

		if(content.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",content.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static Content map(Map<String, Object> fields) {

		Content returnVal = new Content();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentTypeId") != null) {
			returnVal.setContentTypeId((String) fields.get("contentTypeId"));
}

		if(fields.get("ownerContentId") != null) {
			returnVal.setOwnerContentId((String) fields.get("ownerContentId"));
}

		if(fields.get("decoratorContentId") != null) {
			returnVal.setDecoratorContentId((String) fields.get("decoratorContentId"));
}

		if(fields.get("instanceOfContentId") != null) {
			returnVal.setInstanceOfContentId((String) fields.get("instanceOfContentId"));
}

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("templateDataResourceId") != null) {
			returnVal.setTemplateDataResourceId((String) fields.get("templateDataResourceId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("privilegeEnumId") != null) {
			returnVal.setPrivilegeEnumId((String) fields.get("privilegeEnumId"));
}

		if(fields.get("serviceName") != null) {
			returnVal.setServiceName((String) fields.get("serviceName"));
}

		if(fields.get("customMethodId") != null) {
			returnVal.setCustomMethodId((String) fields.get("customMethodId"));
}

		if(fields.get("contentName") != null) {
			returnVal.setContentName((String) fields.get("contentName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("localeString") != null) {
			returnVal.setLocaleString((String) fields.get("localeString"));
}

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}

		if(fields.get("characterSetId") != null) {
			returnVal.setCharacterSetId((String) fields.get("characterSetId"));
}

		if(fields.get("childLeafCount") != null) {
			returnVal.setChildLeafCount((long) fields.get("childLeafCount"));
}

		if(fields.get("childBranchCount") != null) {
			returnVal.setChildBranchCount((long) fields.get("childBranchCount"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Content mapstrstr(Map<String, String> fields) throws Exception {

		Content returnVal = new Content();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentTypeId") != null) {
			returnVal.setContentTypeId((String) fields.get("contentTypeId"));
}

		if(fields.get("ownerContentId") != null) {
			returnVal.setOwnerContentId((String) fields.get("ownerContentId"));
}

		if(fields.get("decoratorContentId") != null) {
			returnVal.setDecoratorContentId((String) fields.get("decoratorContentId"));
}

		if(fields.get("instanceOfContentId") != null) {
			returnVal.setInstanceOfContentId((String) fields.get("instanceOfContentId"));
}

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("templateDataResourceId") != null) {
			returnVal.setTemplateDataResourceId((String) fields.get("templateDataResourceId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("privilegeEnumId") != null) {
			returnVal.setPrivilegeEnumId((String) fields.get("privilegeEnumId"));
}

		if(fields.get("serviceName") != null) {
			returnVal.setServiceName((String) fields.get("serviceName"));
}

		if(fields.get("customMethodId") != null) {
			returnVal.setCustomMethodId((String) fields.get("customMethodId"));
}

		if(fields.get("contentName") != null) {
			returnVal.setContentName((String) fields.get("contentName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("localeString") != null) {
			returnVal.setLocaleString((String) fields.get("localeString"));
}

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}

		if(fields.get("characterSetId") != null) {
			returnVal.setCharacterSetId((String) fields.get("characterSetId"));
}

		if(fields.get("childLeafCount") != null) {
String buf;
buf = fields.get("childLeafCount");
long ibuf = Long.parseLong(buf);
			returnVal.setChildLeafCount(ibuf);
}

		if(fields.get("childBranchCount") != null) {
String buf;
buf = fields.get("childBranchCount");
long ibuf = Long.parseLong(buf);
			returnVal.setChildBranchCount(ibuf);
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Content map(GenericValue val) {

Content returnVal = new Content();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setContentTypeId(val.getString("contentTypeId"));
		returnVal.setOwnerContentId(val.getString("ownerContentId"));
		returnVal.setDecoratorContentId(val.getString("decoratorContentId"));
		returnVal.setInstanceOfContentId(val.getString("instanceOfContentId"));
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setTemplateDataResourceId(val.getString("templateDataResourceId"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPrivilegeEnumId(val.getString("privilegeEnumId"));
		returnVal.setServiceName(val.getString("serviceName"));
		returnVal.setCustomMethodId(val.getString("customMethodId"));
		returnVal.setContentName(val.getString("contentName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setLocaleString(val.getString("localeString"));
		returnVal.setMimeTypeId(val.getString("mimeTypeId"));
		returnVal.setCharacterSetId(val.getString("characterSetId"));
		returnVal.setChildLeafCount(val.getLong("childLeafCount"));
		returnVal.setChildBranchCount(val.getLong("childBranchCount"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static Content map(HttpServletRequest request) throws Exception {

		Content returnVal = new Content();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("contentTypeId"))  {
returnVal.setContentTypeId(request.getParameter("contentTypeId"));
}
		if(paramMap.containsKey("ownerContentId"))  {
returnVal.setOwnerContentId(request.getParameter("ownerContentId"));
}
		if(paramMap.containsKey("decoratorContentId"))  {
returnVal.setDecoratorContentId(request.getParameter("decoratorContentId"));
}
		if(paramMap.containsKey("instanceOfContentId"))  {
returnVal.setInstanceOfContentId(request.getParameter("instanceOfContentId"));
}
		if(paramMap.containsKey("dataResourceId"))  {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}
		if(paramMap.containsKey("templateDataResourceId"))  {
returnVal.setTemplateDataResourceId(request.getParameter("templateDataResourceId"));
}
		if(paramMap.containsKey("dataSourceId"))  {
returnVal.setDataSourceId(request.getParameter("dataSourceId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("privilegeEnumId"))  {
returnVal.setPrivilegeEnumId(request.getParameter("privilegeEnumId"));
}
		if(paramMap.containsKey("serviceName"))  {
returnVal.setServiceName(request.getParameter("serviceName"));
}
		if(paramMap.containsKey("customMethodId"))  {
returnVal.setCustomMethodId(request.getParameter("customMethodId"));
}
		if(paramMap.containsKey("contentName"))  {
returnVal.setContentName(request.getParameter("contentName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("localeString"))  {
returnVal.setLocaleString(request.getParameter("localeString"));
}
		if(paramMap.containsKey("mimeTypeId"))  {
returnVal.setMimeTypeId(request.getParameter("mimeTypeId"));
}
		if(paramMap.containsKey("characterSetId"))  {
returnVal.setCharacterSetId(request.getParameter("characterSetId"));
}
		if(paramMap.containsKey("childLeafCount"))  {
String buf = request.getParameter("childLeafCount");
Long ibuf = Long.parseLong(buf);
returnVal.setChildLeafCount(ibuf);
}
		if(paramMap.containsKey("childBranchCount"))  {
String buf = request.getParameter("childBranchCount");
Long ibuf = Long.parseLong(buf);
returnVal.setChildBranchCount(ibuf);
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
