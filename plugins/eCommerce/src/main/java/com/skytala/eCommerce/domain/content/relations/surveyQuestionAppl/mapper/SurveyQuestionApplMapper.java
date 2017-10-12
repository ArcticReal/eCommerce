package com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.model.SurveyQuestionAppl;

public class SurveyQuestionApplMapper  {


	public static Map<String, Object> map(SurveyQuestionAppl surveyquestionappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyquestionappl.getSurveyId() != null ){
			returnVal.put("surveyId",surveyquestionappl.getSurveyId());
}

		if(surveyquestionappl.getSurveyQuestionId() != null ){
			returnVal.put("surveyQuestionId",surveyquestionappl.getSurveyQuestionId());
}

		if(surveyquestionappl.getFromDate() != null ){
			returnVal.put("fromDate",surveyquestionappl.getFromDate());
}

		if(surveyquestionappl.getThruDate() != null ){
			returnVal.put("thruDate",surveyquestionappl.getThruDate());
}

		if(surveyquestionappl.getSurveyPageSeqId() != null ){
			returnVal.put("surveyPageSeqId",surveyquestionappl.getSurveyPageSeqId());
}

		if(surveyquestionappl.getSurveyMultiRespId() != null ){
			returnVal.put("surveyMultiRespId",surveyquestionappl.getSurveyMultiRespId());
}

		if(surveyquestionappl.getSurveyMultiRespColId() != null ){
			returnVal.put("surveyMultiRespColId",surveyquestionappl.getSurveyMultiRespColId());
}

		if(surveyquestionappl.getRequiredField() != null ){
			returnVal.put("requiredField",surveyquestionappl.getRequiredField());
}

		if(surveyquestionappl.getSequenceNum() != null ){
			returnVal.put("sequenceNum",surveyquestionappl.getSequenceNum());
}

		if(surveyquestionappl.getExternalFieldRef() != null ){
			returnVal.put("externalFieldRef",surveyquestionappl.getExternalFieldRef());
}

		if(surveyquestionappl.getWithSurveyQuestionId() != null ){
			returnVal.put("withSurveyQuestionId",surveyquestionappl.getWithSurveyQuestionId());
}

		if(surveyquestionappl.getWithSurveyOptionSeqId() != null ){
			returnVal.put("withSurveyOptionSeqId",surveyquestionappl.getWithSurveyOptionSeqId());
}

		return returnVal;
}


	public static SurveyQuestionAppl map(Map<String, Object> fields) {

		SurveyQuestionAppl returnVal = new SurveyQuestionAppl();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("surveyPageSeqId") != null) {
			returnVal.setSurveyPageSeqId((String) fields.get("surveyPageSeqId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("surveyMultiRespColId") != null) {
			returnVal.setSurveyMultiRespColId((String) fields.get("surveyMultiRespColId"));
}

		if(fields.get("requiredField") != null) {
			returnVal.setRequiredField((boolean) fields.get("requiredField"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("externalFieldRef") != null) {
			returnVal.setExternalFieldRef((String) fields.get("externalFieldRef"));
}

		if(fields.get("withSurveyQuestionId") != null) {
			returnVal.setWithSurveyQuestionId((String) fields.get("withSurveyQuestionId"));
}

		if(fields.get("withSurveyOptionSeqId") != null) {
			returnVal.setWithSurveyOptionSeqId((String) fields.get("withSurveyOptionSeqId"));
}


		return returnVal;
 } 
	public static SurveyQuestionAppl mapstrstr(Map<String, String> fields) throws Exception {

		SurveyQuestionAppl returnVal = new SurveyQuestionAppl();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
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

		if(fields.get("surveyPageSeqId") != null) {
			returnVal.setSurveyPageSeqId((String) fields.get("surveyPageSeqId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("surveyMultiRespColId") != null) {
			returnVal.setSurveyMultiRespColId((String) fields.get("surveyMultiRespColId"));
}

		if(fields.get("requiredField") != null) {
String buf;
buf = fields.get("requiredField");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequiredField(ibuf);
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("externalFieldRef") != null) {
			returnVal.setExternalFieldRef((String) fields.get("externalFieldRef"));
}

		if(fields.get("withSurveyQuestionId") != null) {
			returnVal.setWithSurveyQuestionId((String) fields.get("withSurveyQuestionId"));
}

		if(fields.get("withSurveyOptionSeqId") != null) {
			returnVal.setWithSurveyOptionSeqId((String) fields.get("withSurveyOptionSeqId"));
}


		return returnVal;
 } 
	public static SurveyQuestionAppl map(GenericValue val) {

SurveyQuestionAppl returnVal = new SurveyQuestionAppl();
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setSurveyQuestionId(val.getString("surveyQuestionId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSurveyPageSeqId(val.getString("surveyPageSeqId"));
		returnVal.setSurveyMultiRespId(val.getString("surveyMultiRespId"));
		returnVal.setSurveyMultiRespColId(val.getString("surveyMultiRespColId"));
		returnVal.setRequiredField(val.getBoolean("requiredField"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setExternalFieldRef(val.getString("externalFieldRef"));
		returnVal.setWithSurveyQuestionId(val.getString("withSurveyQuestionId"));
		returnVal.setWithSurveyOptionSeqId(val.getString("withSurveyOptionSeqId"));


return returnVal;

}

public static SurveyQuestionAppl map(HttpServletRequest request) throws Exception {

		SurveyQuestionAppl returnVal = new SurveyQuestionAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyId")) {
returnVal.setSurveyId(request.getParameter("surveyId"));
}

		if(paramMap.containsKey("surveyQuestionId"))  {
returnVal.setSurveyQuestionId(request.getParameter("surveyQuestionId"));
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
		if(paramMap.containsKey("surveyPageSeqId"))  {
returnVal.setSurveyPageSeqId(request.getParameter("surveyPageSeqId"));
}
		if(paramMap.containsKey("surveyMultiRespId"))  {
returnVal.setSurveyMultiRespId(request.getParameter("surveyMultiRespId"));
}
		if(paramMap.containsKey("surveyMultiRespColId"))  {
returnVal.setSurveyMultiRespColId(request.getParameter("surveyMultiRespColId"));
}
		if(paramMap.containsKey("requiredField"))  {
String buf = request.getParameter("requiredField");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequiredField(ibuf);
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("externalFieldRef"))  {
returnVal.setExternalFieldRef(request.getParameter("externalFieldRef"));
}
		if(paramMap.containsKey("withSurveyQuestionId"))  {
returnVal.setWithSurveyQuestionId(request.getParameter("withSurveyQuestionId"));
}
		if(paramMap.containsKey("withSurveyOptionSeqId"))  {
returnVal.setWithSurveyOptionSeqId(request.getParameter("withSurveyOptionSeqId"));
}
return returnVal;

}
}
