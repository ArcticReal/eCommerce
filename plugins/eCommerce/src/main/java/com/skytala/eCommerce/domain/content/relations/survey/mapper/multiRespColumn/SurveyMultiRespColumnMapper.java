package com.skytala.eCommerce.domain.content.relations.survey.mapper.multiRespColumn;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;

public class SurveyMultiRespColumnMapper  {


	public static Map<String, Object> map(SurveyMultiRespColumn surveymultirespcolumn) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveymultirespcolumn.getSurveyId() != null ){
			returnVal.put("surveyId",surveymultirespcolumn.getSurveyId());
}

		if(surveymultirespcolumn.getSurveyMultiRespId() != null ){
			returnVal.put("surveyMultiRespId",surveymultirespcolumn.getSurveyMultiRespId());
}

		if(surveymultirespcolumn.getSurveyMultiRespColId() != null ){
			returnVal.put("surveyMultiRespColId",surveymultirespcolumn.getSurveyMultiRespColId());
}

		if(surveymultirespcolumn.getColumnTitle() != null ){
			returnVal.put("columnTitle",surveymultirespcolumn.getColumnTitle());
}

		if(surveymultirespcolumn.getSequenceNum() != null ){
			returnVal.put("sequenceNum",surveymultirespcolumn.getSequenceNum());
}

		return returnVal;
}


	public static SurveyMultiRespColumn map(Map<String, Object> fields) {

		SurveyMultiRespColumn returnVal = new SurveyMultiRespColumn();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("surveyMultiRespColId") != null) {
			returnVal.setSurveyMultiRespColId((String) fields.get("surveyMultiRespColId"));
}

		if(fields.get("columnTitle") != null) {
			returnVal.setColumnTitle((String) fields.get("columnTitle"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static SurveyMultiRespColumn mapstrstr(Map<String, String> fields) throws Exception {

		SurveyMultiRespColumn returnVal = new SurveyMultiRespColumn();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("surveyMultiRespColId") != null) {
			returnVal.setSurveyMultiRespColId((String) fields.get("surveyMultiRespColId"));
}

		if(fields.get("columnTitle") != null) {
			returnVal.setColumnTitle((String) fields.get("columnTitle"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static SurveyMultiRespColumn map(GenericValue val) {

SurveyMultiRespColumn returnVal = new SurveyMultiRespColumn();
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setSurveyMultiRespId(val.getString("surveyMultiRespId"));
		returnVal.setSurveyMultiRespColId(val.getString("surveyMultiRespColId"));
		returnVal.setColumnTitle(val.getString("columnTitle"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static SurveyMultiRespColumn map(HttpServletRequest request) throws Exception {

		SurveyMultiRespColumn returnVal = new SurveyMultiRespColumn();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyId")) {
returnVal.setSurveyId(request.getParameter("surveyId"));
}

		if(paramMap.containsKey("surveyMultiRespId"))  {
returnVal.setSurveyMultiRespId(request.getParameter("surveyMultiRespId"));
}
		if(paramMap.containsKey("surveyMultiRespColId"))  {
returnVal.setSurveyMultiRespColId(request.getParameter("surveyMultiRespColId"));
}
		if(paramMap.containsKey("columnTitle"))  {
returnVal.setColumnTitle(request.getParameter("columnTitle"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
