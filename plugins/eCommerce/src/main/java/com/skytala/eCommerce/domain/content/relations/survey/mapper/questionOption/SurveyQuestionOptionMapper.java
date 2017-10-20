package com.skytala.eCommerce.domain.content.relations.survey.mapper.questionOption;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;

public class SurveyQuestionOptionMapper  {


	public static Map<String, Object> map(SurveyQuestionOption surveyquestionoption) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyquestionoption.getSurveyQuestionId() != null ){
			returnVal.put("surveyQuestionId",surveyquestionoption.getSurveyQuestionId());
}

		if(surveyquestionoption.getSurveyOptionSeqId() != null ){
			returnVal.put("surveyOptionSeqId",surveyquestionoption.getSurveyOptionSeqId());
}

		if(surveyquestionoption.getDescription() != null ){
			returnVal.put("description",surveyquestionoption.getDescription());
}

		if(surveyquestionoption.getSequenceNum() != null ){
			returnVal.put("sequenceNum",surveyquestionoption.getSequenceNum());
}

		if(surveyquestionoption.getAmountBase() != null ){
			returnVal.put("amountBase",surveyquestionoption.getAmountBase());
}

		if(surveyquestionoption.getAmountBaseUomId() != null ){
			returnVal.put("amountBaseUomId",surveyquestionoption.getAmountBaseUomId());
}

		if(surveyquestionoption.getWeightFactor() != null ){
			returnVal.put("weightFactor",surveyquestionoption.getWeightFactor());
}

		if(surveyquestionoption.getDuration() != null ){
			returnVal.put("duration",surveyquestionoption.getDuration());
}

		if(surveyquestionoption.getDurationUomId() != null ){
			returnVal.put("durationUomId",surveyquestionoption.getDurationUomId());
}

		return returnVal;
}


	public static SurveyQuestionOption map(Map<String, Object> fields) {

		SurveyQuestionOption returnVal = new SurveyQuestionOption();

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
}

		if(fields.get("surveyOptionSeqId") != null) {
			returnVal.setSurveyOptionSeqId((String) fields.get("surveyOptionSeqId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("amountBase") != null) {
			returnVal.setAmountBase((BigDecimal) fields.get("amountBase"));
}

		if(fields.get("amountBaseUomId") != null) {
			returnVal.setAmountBaseUomId((String) fields.get("amountBaseUomId"));
}

		if(fields.get("weightFactor") != null) {
			returnVal.setWeightFactor((BigDecimal) fields.get("weightFactor"));
}

		if(fields.get("duration") != null) {
			returnVal.setDuration((long) fields.get("duration"));
}

		if(fields.get("durationUomId") != null) {
			returnVal.setDurationUomId((String) fields.get("durationUomId"));
}


		return returnVal;
 } 
	public static SurveyQuestionOption mapstrstr(Map<String, String> fields) throws Exception {

		SurveyQuestionOption returnVal = new SurveyQuestionOption();

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
}

		if(fields.get("surveyOptionSeqId") != null) {
			returnVal.setSurveyOptionSeqId((String) fields.get("surveyOptionSeqId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("amountBase") != null) {
String buf;
buf = fields.get("amountBase");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountBase(bd);
}

		if(fields.get("amountBaseUomId") != null) {
			returnVal.setAmountBaseUomId((String) fields.get("amountBaseUomId"));
}

		if(fields.get("weightFactor") != null) {
String buf;
buf = fields.get("weightFactor");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeightFactor(bd);
}

		if(fields.get("duration") != null) {
String buf;
buf = fields.get("duration");
long ibuf = Long.parseLong(buf);
			returnVal.setDuration(ibuf);
}

		if(fields.get("durationUomId") != null) {
			returnVal.setDurationUomId((String) fields.get("durationUomId"));
}


		return returnVal;
 } 
	public static SurveyQuestionOption map(GenericValue val) {

SurveyQuestionOption returnVal = new SurveyQuestionOption();
		returnVal.setSurveyQuestionId(val.getString("surveyQuestionId"));
		returnVal.setSurveyOptionSeqId(val.getString("surveyOptionSeqId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setAmountBase(val.getBigDecimal("amountBase"));
		returnVal.setAmountBaseUomId(val.getString("amountBaseUomId"));
		returnVal.setWeightFactor(val.getBigDecimal("weightFactor"));
		returnVal.setDuration(val.getLong("duration"));
		returnVal.setDurationUomId(val.getString("durationUomId"));


return returnVal;

}

public static SurveyQuestionOption map(HttpServletRequest request) throws Exception {

		SurveyQuestionOption returnVal = new SurveyQuestionOption();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyQuestionId")) {
returnVal.setSurveyQuestionId(request.getParameter("surveyQuestionId"));
}

		if(paramMap.containsKey("surveyOptionSeqId"))  {
returnVal.setSurveyOptionSeqId(request.getParameter("surveyOptionSeqId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("amountBase"))  {
String buf = request.getParameter("amountBase");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountBase(bd);
}
		if(paramMap.containsKey("amountBaseUomId"))  {
returnVal.setAmountBaseUomId(request.getParameter("amountBaseUomId"));
}
		if(paramMap.containsKey("weightFactor"))  {
String buf = request.getParameter("weightFactor");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeightFactor(bd);
}
		if(paramMap.containsKey("duration"))  {
String buf = request.getParameter("duration");
Long ibuf = Long.parseLong(buf);
returnVal.setDuration(ibuf);
}
		if(paramMap.containsKey("durationUomId"))  {
returnVal.setDurationUomId(request.getParameter("durationUomId"));
}
return returnVal;

}
}
