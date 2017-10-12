package com.skytala.eCommerce.domain.content.relations.survey.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.survey.model.Survey;

public class SurveyMapper  {


	public static Map<String, Object> map(Survey survey) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(survey.getSurveyId() != null ){
			returnVal.put("surveyId",survey.getSurveyId());
}

		if(survey.getSurveyName() != null ){
			returnVal.put("surveyName",survey.getSurveyName());
}

		if(survey.getDescription() != null ){
			returnVal.put("description",survey.getDescription());
}

		if(survey.getComments() != null ){
			returnVal.put("comments",survey.getComments());
}

		if(survey.getSubmitCaption() != null ){
			returnVal.put("submitCaption",survey.getSubmitCaption());
}

		if(survey.getResponseService() != null ){
			returnVal.put("responseService",survey.getResponseService());
}

		if(survey.getIsAnonymous() != null ){
			returnVal.put("isAnonymous",survey.getIsAnonymous());
}

		if(survey.getAllowMultiple() != null ){
			returnVal.put("allowMultiple",survey.getAllowMultiple());
}

		if(survey.getAllowUpdate() != null ){
			returnVal.put("allowUpdate",survey.getAllowUpdate());
}

		if(survey.getAcroFormContentId() != null ){
			returnVal.put("acroFormContentId",survey.getAcroFormContentId());
}

		return returnVal;
}


	public static Survey map(Map<String, Object> fields) {

		Survey returnVal = new Survey();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyName") != null) {
			returnVal.setSurveyName((String) fields.get("surveyName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("submitCaption") != null) {
			returnVal.setSubmitCaption((String) fields.get("submitCaption"));
}

		if(fields.get("responseService") != null) {
			returnVal.setResponseService((String) fields.get("responseService"));
}

		if(fields.get("isAnonymous") != null) {
			returnVal.setIsAnonymous((boolean) fields.get("isAnonymous"));
}

		if(fields.get("allowMultiple") != null) {
			returnVal.setAllowMultiple((boolean) fields.get("allowMultiple"));
}

		if(fields.get("allowUpdate") != null) {
			returnVal.setAllowUpdate((boolean) fields.get("allowUpdate"));
}

		if(fields.get("acroFormContentId") != null) {
			returnVal.setAcroFormContentId((String) fields.get("acroFormContentId"));
}


		return returnVal;
 } 
	public static Survey mapstrstr(Map<String, String> fields) throws Exception {

		Survey returnVal = new Survey();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyName") != null) {
			returnVal.setSurveyName((String) fields.get("surveyName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("submitCaption") != null) {
			returnVal.setSubmitCaption((String) fields.get("submitCaption"));
}

		if(fields.get("responseService") != null) {
			returnVal.setResponseService((String) fields.get("responseService"));
}

		if(fields.get("isAnonymous") != null) {
String buf;
buf = fields.get("isAnonymous");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsAnonymous(ibuf);
}

		if(fields.get("allowMultiple") != null) {
String buf;
buf = fields.get("allowMultiple");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowMultiple(ibuf);
}

		if(fields.get("allowUpdate") != null) {
String buf;
buf = fields.get("allowUpdate");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowUpdate(ibuf);
}

		if(fields.get("acroFormContentId") != null) {
			returnVal.setAcroFormContentId((String) fields.get("acroFormContentId"));
}


		return returnVal;
 } 
	public static Survey map(GenericValue val) {

Survey returnVal = new Survey();
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setSurveyName(val.getString("surveyName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setSubmitCaption(val.getString("submitCaption"));
		returnVal.setResponseService(val.getString("responseService"));
		returnVal.setIsAnonymous(val.getBoolean("isAnonymous"));
		returnVal.setAllowMultiple(val.getBoolean("allowMultiple"));
		returnVal.setAllowUpdate(val.getBoolean("allowUpdate"));
		returnVal.setAcroFormContentId(val.getString("acroFormContentId"));


return returnVal;

}

public static Survey map(HttpServletRequest request) throws Exception {

		Survey returnVal = new Survey();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyId")) {
returnVal.setSurveyId(request.getParameter("surveyId"));
}

		if(paramMap.containsKey("surveyName"))  {
returnVal.setSurveyName(request.getParameter("surveyName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("submitCaption"))  {
returnVal.setSubmitCaption(request.getParameter("submitCaption"));
}
		if(paramMap.containsKey("responseService"))  {
returnVal.setResponseService(request.getParameter("responseService"));
}
		if(paramMap.containsKey("isAnonymous"))  {
String buf = request.getParameter("isAnonymous");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsAnonymous(ibuf);
}
		if(paramMap.containsKey("allowMultiple"))  {
String buf = request.getParameter("allowMultiple");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowMultiple(ibuf);
}
		if(paramMap.containsKey("allowUpdate"))  {
String buf = request.getParameter("allowUpdate");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowUpdate(ibuf);
}
		if(paramMap.containsKey("acroFormContentId"))  {
returnVal.setAcroFormContentId(request.getParameter("acroFormContentId"));
}
return returnVal;

}
}
