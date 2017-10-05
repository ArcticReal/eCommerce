package com.skytala.eCommerce.domain.surveyQuestion.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.surveyQuestion.model.SurveyQuestion;

public class SurveyQuestionMapper  {


	public static Map<String, Object> map(SurveyQuestion surveyquestion) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyquestion.getSurveyQuestionId() != null ){
			returnVal.put("surveyQuestionId",surveyquestion.getSurveyQuestionId());
}

		if(surveyquestion.getSurveyQuestionCategoryId() != null ){
			returnVal.put("surveyQuestionCategoryId",surveyquestion.getSurveyQuestionCategoryId());
}

		if(surveyquestion.getSurveyQuestionTypeId() != null ){
			returnVal.put("surveyQuestionTypeId",surveyquestion.getSurveyQuestionTypeId());
}

		if(surveyquestion.getDescription() != null ){
			returnVal.put("description",surveyquestion.getDescription());
}

		if(surveyquestion.getQuestion() != null ){
			returnVal.put("question",surveyquestion.getQuestion());
}

		if(surveyquestion.getHint() != null ){
			returnVal.put("hint",surveyquestion.getHint());
}

		if(surveyquestion.getEnumTypeId() != null ){
			returnVal.put("enumTypeId",surveyquestion.getEnumTypeId());
}

		if(surveyquestion.getGeoId() != null ){
			returnVal.put("geoId",surveyquestion.getGeoId());
}

		if(surveyquestion.getFormatString() != null ){
			returnVal.put("formatString",surveyquestion.getFormatString());
}

		return returnVal;
}


	public static SurveyQuestion map(Map<String, Object> fields) {

		SurveyQuestion returnVal = new SurveyQuestion();

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
}

		if(fields.get("surveyQuestionCategoryId") != null) {
			returnVal.setSurveyQuestionCategoryId((String) fields.get("surveyQuestionCategoryId"));
}

		if(fields.get("surveyQuestionTypeId") != null) {
			returnVal.setSurveyQuestionTypeId((String) fields.get("surveyQuestionTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("question") != null) {
			returnVal.setQuestion((String) fields.get("question"));
}

		if(fields.get("hint") != null) {
			returnVal.setHint((String) fields.get("hint"));
}

		if(fields.get("enumTypeId") != null) {
			returnVal.setEnumTypeId((String) fields.get("enumTypeId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("formatString") != null) {
			returnVal.setFormatString((long) fields.get("formatString"));
}


		return returnVal;
 } 
	public static SurveyQuestion mapstrstr(Map<String, String> fields) throws Exception {

		SurveyQuestion returnVal = new SurveyQuestion();

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
}

		if(fields.get("surveyQuestionCategoryId") != null) {
			returnVal.setSurveyQuestionCategoryId((String) fields.get("surveyQuestionCategoryId"));
}

		if(fields.get("surveyQuestionTypeId") != null) {
			returnVal.setSurveyQuestionTypeId((String) fields.get("surveyQuestionTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("question") != null) {
			returnVal.setQuestion((String) fields.get("question"));
}

		if(fields.get("hint") != null) {
			returnVal.setHint((String) fields.get("hint"));
}

		if(fields.get("enumTypeId") != null) {
			returnVal.setEnumTypeId((String) fields.get("enumTypeId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("formatString") != null) {
String buf;
buf = fields.get("formatString");
long ibuf = Long.parseLong(buf);
			returnVal.setFormatString(ibuf);
}


		return returnVal;
 } 
	public static SurveyQuestion map(GenericValue val) {

SurveyQuestion returnVal = new SurveyQuestion();
		returnVal.setSurveyQuestionId(val.getString("surveyQuestionId"));
		returnVal.setSurveyQuestionCategoryId(val.getString("surveyQuestionCategoryId"));
		returnVal.setSurveyQuestionTypeId(val.getString("surveyQuestionTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setQuestion(val.getString("question"));
		returnVal.setHint(val.getString("hint"));
		returnVal.setEnumTypeId(val.getString("enumTypeId"));
		returnVal.setGeoId(val.getString("geoId"));
		returnVal.setFormatString(val.getLong("formatString"));


return returnVal;

}

public static SurveyQuestion map(HttpServletRequest request) throws Exception {

		SurveyQuestion returnVal = new SurveyQuestion();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyQuestionId")) {
returnVal.setSurveyQuestionId(request.getParameter("surveyQuestionId"));
}

		if(paramMap.containsKey("surveyQuestionCategoryId"))  {
returnVal.setSurveyQuestionCategoryId(request.getParameter("surveyQuestionCategoryId"));
}
		if(paramMap.containsKey("surveyQuestionTypeId"))  {
returnVal.setSurveyQuestionTypeId(request.getParameter("surveyQuestionTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("question"))  {
returnVal.setQuestion(request.getParameter("question"));
}
		if(paramMap.containsKey("hint"))  {
returnVal.setHint(request.getParameter("hint"));
}
		if(paramMap.containsKey("enumTypeId"))  {
returnVal.setEnumTypeId(request.getParameter("enumTypeId"));
}
		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
}
		if(paramMap.containsKey("formatString"))  {
String buf = request.getParameter("formatString");
Long ibuf = Long.parseLong(buf);
returnVal.setFormatString(ibuf);
}
return returnVal;

}
}
