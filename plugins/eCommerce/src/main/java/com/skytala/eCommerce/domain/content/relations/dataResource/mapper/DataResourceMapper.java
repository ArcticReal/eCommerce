package com.skytala.eCommerce.domain.content.relations.dataResource.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;

public class DataResourceMapper  {


	public static Map<String, Object> map(DataResource dataresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(dataresource.getDataResourceId() != null ){
			returnVal.put("dataResourceId",dataresource.getDataResourceId());
}

		if(dataresource.getDataResourceTypeId() != null ){
			returnVal.put("dataResourceTypeId",dataresource.getDataResourceTypeId());
}

		if(dataresource.getDataTemplateTypeId() != null ){
			returnVal.put("dataTemplateTypeId",dataresource.getDataTemplateTypeId());
}

		if(dataresource.getDataCategoryId() != null ){
			returnVal.put("dataCategoryId",dataresource.getDataCategoryId());
}

		if(dataresource.getDataSourceId() != null ){
			returnVal.put("dataSourceId",dataresource.getDataSourceId());
}

		if(dataresource.getStatusId() != null ){
			returnVal.put("statusId",dataresource.getStatusId());
}

		if(dataresource.getDataResourceName() != null ){
			returnVal.put("dataResourceName",dataresource.getDataResourceName());
}

		if(dataresource.getLocaleString() != null ){
			returnVal.put("localeString",dataresource.getLocaleString());
}

		if(dataresource.getMimeTypeId() != null ){
			returnVal.put("mimeTypeId",dataresource.getMimeTypeId());
}

		if(dataresource.getCharacterSetId() != null ){
			returnVal.put("characterSetId",dataresource.getCharacterSetId());
}

		if(dataresource.getObjectInfo() != null ){
			returnVal.put("objectInfo",dataresource.getObjectInfo());
}

		if(dataresource.getSurveyId() != null ){
			returnVal.put("surveyId",dataresource.getSurveyId());
}

		if(dataresource.getSurveyResponseId() != null ){
			returnVal.put("surveyResponseId",dataresource.getSurveyResponseId());
}

		if(dataresource.getRelatedDetailId() != null ){
			returnVal.put("relatedDetailId",dataresource.getRelatedDetailId());
}

		if(dataresource.getIsPublic() != null ){
			returnVal.put("isPublic",dataresource.getIsPublic());
}

		if(dataresource.getCreatedDate() != null ){
			returnVal.put("createdDate",dataresource.getCreatedDate());
}

		if(dataresource.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",dataresource.getCreatedByUserLogin());
}

		if(dataresource.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",dataresource.getLastModifiedDate());
}

		if(dataresource.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",dataresource.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static DataResource map(Map<String, Object> fields) {

		DataResource returnVal = new DataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("dataResourceTypeId") != null) {
			returnVal.setDataResourceTypeId((String) fields.get("dataResourceTypeId"));
}

		if(fields.get("dataTemplateTypeId") != null) {
			returnVal.setDataTemplateTypeId((String) fields.get("dataTemplateTypeId"));
}

		if(fields.get("dataCategoryId") != null) {
			returnVal.setDataCategoryId((String) fields.get("dataCategoryId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("dataResourceName") != null) {
			returnVal.setDataResourceName((String) fields.get("dataResourceName"));
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

		if(fields.get("objectInfo") != null) {
			returnVal.setObjectInfo((String) fields.get("objectInfo"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("relatedDetailId") != null) {
			returnVal.setRelatedDetailId((String) fields.get("relatedDetailId"));
}

		if(fields.get("isPublic") != null) {
			returnVal.setIsPublic((boolean) fields.get("isPublic"));
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
	public static DataResource mapstrstr(Map<String, String> fields) throws Exception {

		DataResource returnVal = new DataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("dataResourceTypeId") != null) {
			returnVal.setDataResourceTypeId((String) fields.get("dataResourceTypeId"));
}

		if(fields.get("dataTemplateTypeId") != null) {
			returnVal.setDataTemplateTypeId((String) fields.get("dataTemplateTypeId"));
}

		if(fields.get("dataCategoryId") != null) {
			returnVal.setDataCategoryId((String) fields.get("dataCategoryId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("dataResourceName") != null) {
			returnVal.setDataResourceName((String) fields.get("dataResourceName"));
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

		if(fields.get("objectInfo") != null) {
			returnVal.setObjectInfo((String) fields.get("objectInfo"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("relatedDetailId") != null) {
			returnVal.setRelatedDetailId((String) fields.get("relatedDetailId"));
}

		if(fields.get("isPublic") != null) {
String buf;
buf = fields.get("isPublic");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPublic(ibuf);
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
	public static DataResource map(GenericValue val) {

DataResource returnVal = new DataResource();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setDataResourceTypeId(val.getString("dataResourceTypeId"));
		returnVal.setDataTemplateTypeId(val.getString("dataTemplateTypeId"));
		returnVal.setDataCategoryId(val.getString("dataCategoryId"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setDataResourceName(val.getString("dataResourceName"));
		returnVal.setLocaleString(val.getString("localeString"));
		returnVal.setMimeTypeId(val.getString("mimeTypeId"));
		returnVal.setCharacterSetId(val.getString("characterSetId"));
		returnVal.setObjectInfo(val.getString("objectInfo"));
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setSurveyResponseId(val.getString("surveyResponseId"));
		returnVal.setRelatedDetailId(val.getString("relatedDetailId"));
		returnVal.setIsPublic(val.getBoolean("isPublic"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static DataResource map(HttpServletRequest request) throws Exception {

		DataResource returnVal = new DataResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("dataResourceTypeId"))  {
returnVal.setDataResourceTypeId(request.getParameter("dataResourceTypeId"));
}
		if(paramMap.containsKey("dataTemplateTypeId"))  {
returnVal.setDataTemplateTypeId(request.getParameter("dataTemplateTypeId"));
}
		if(paramMap.containsKey("dataCategoryId"))  {
returnVal.setDataCategoryId(request.getParameter("dataCategoryId"));
}
		if(paramMap.containsKey("dataSourceId"))  {
returnVal.setDataSourceId(request.getParameter("dataSourceId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("dataResourceName"))  {
returnVal.setDataResourceName(request.getParameter("dataResourceName"));
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
		if(paramMap.containsKey("objectInfo"))  {
returnVal.setObjectInfo(request.getParameter("objectInfo"));
}
		if(paramMap.containsKey("surveyId"))  {
returnVal.setSurveyId(request.getParameter("surveyId"));
}
		if(paramMap.containsKey("surveyResponseId"))  {
returnVal.setSurveyResponseId(request.getParameter("surveyResponseId"));
}
		if(paramMap.containsKey("relatedDetailId"))  {
returnVal.setRelatedDetailId(request.getParameter("relatedDetailId"));
}
		if(paramMap.containsKey("isPublic"))  {
String buf = request.getParameter("isPublic");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsPublic(ibuf);
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
