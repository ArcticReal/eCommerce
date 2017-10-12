package com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.model.SurveyResponseAnswer;

public class SurveyResponseAnswerMapper  {


	public static Map<String, Object> map(SurveyResponseAnswer surveyresponseanswer) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyresponseanswer.getSurveyResponseId() != null ){
			returnVal.put("surveyResponseId",surveyresponseanswer.getSurveyResponseId());
}

		if(surveyresponseanswer.getSurveyQuestionId() != null ){
			returnVal.put("surveyQuestionId",surveyresponseanswer.getSurveyQuestionId());
}

		if(surveyresponseanswer.getSurveyMultiRespColId() != null ){
			returnVal.put("surveyMultiRespColId",surveyresponseanswer.getSurveyMultiRespColId());
}

		if(surveyresponseanswer.getSurveyMultiRespId() != null ){
			returnVal.put("surveyMultiRespId",surveyresponseanswer.getSurveyMultiRespId());
}

		if(surveyresponseanswer.getBooleanResponse() != null ){
			returnVal.put("booleanResponse",surveyresponseanswer.getBooleanResponse());
}

		if(surveyresponseanswer.getCurrencyResponse() != null ){
			returnVal.put("currencyResponse",surveyresponseanswer.getCurrencyResponse());
}

		if(surveyresponseanswer.getFloatResponse() != null ){
			returnVal.put("floatResponse",surveyresponseanswer.getFloatResponse());
}

		if(surveyresponseanswer.getNumericResponse() != null ){
			returnVal.put("numericResponse",surveyresponseanswer.getNumericResponse());
}

		if(surveyresponseanswer.getTextResponse() != null ){
			returnVal.put("textResponse",surveyresponseanswer.getTextResponse());
}

		if(surveyresponseanswer.getSurveyOptionSeqId() != null ){
			returnVal.put("surveyOptionSeqId",surveyresponseanswer.getSurveyOptionSeqId());
}

		if(surveyresponseanswer.getContentId() != null ){
			returnVal.put("contentId",surveyresponseanswer.getContentId());
}

		if(surveyresponseanswer.getAnsweredDate() != null ){
			returnVal.put("answeredDate",surveyresponseanswer.getAnsweredDate());
}

		if(surveyresponseanswer.getAmountBase() != null ){
			returnVal.put("amountBase",surveyresponseanswer.getAmountBase());
}

		if(surveyresponseanswer.getAmountBaseUomId() != null ){
			returnVal.put("amountBaseUomId",surveyresponseanswer.getAmountBaseUomId());
}

		if(surveyresponseanswer.getWeightFactor() != null ){
			returnVal.put("weightFactor",surveyresponseanswer.getWeightFactor());
}

		if(surveyresponseanswer.getDuration() != null ){
			returnVal.put("duration",surveyresponseanswer.getDuration());
}

		if(surveyresponseanswer.getDurationUomId() != null ){
			returnVal.put("durationUomId",surveyresponseanswer.getDurationUomId());
}

		if(surveyresponseanswer.getSequenceNum() != null ){
			returnVal.put("sequenceNum",surveyresponseanswer.getSequenceNum());
}

		return returnVal;
}


	public static SurveyResponseAnswer map(Map<String, Object> fields) {

		SurveyResponseAnswer returnVal = new SurveyResponseAnswer();

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
}

		if(fields.get("surveyMultiRespColId") != null) {
			returnVal.setSurveyMultiRespColId((String) fields.get("surveyMultiRespColId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("booleanResponse") != null) {
			returnVal.setBooleanResponse((boolean) fields.get("booleanResponse"));
}

		if(fields.get("currencyResponse") != null) {
			returnVal.setCurrencyResponse((BigDecimal) fields.get("currencyResponse"));
}

		if(fields.get("floatResponse") != null) {
			returnVal.setFloatResponse((BigDecimal) fields.get("floatResponse"));
}

		if(fields.get("numericResponse") != null) {
			returnVal.setNumericResponse((long) fields.get("numericResponse"));
}

		if(fields.get("textResponse") != null) {
			returnVal.setTextResponse((String) fields.get("textResponse"));
}

		if(fields.get("surveyOptionSeqId") != null) {
			returnVal.setSurveyOptionSeqId((String) fields.get("surveyOptionSeqId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("answeredDate") != null) {
			returnVal.setAnsweredDate((Timestamp) fields.get("answeredDate"));
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

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static SurveyResponseAnswer mapstrstr(Map<String, String> fields) throws Exception {

		SurveyResponseAnswer returnVal = new SurveyResponseAnswer();

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("surveyQuestionId") != null) {
			returnVal.setSurveyQuestionId((String) fields.get("surveyQuestionId"));
}

		if(fields.get("surveyMultiRespColId") != null) {
			returnVal.setSurveyMultiRespColId((String) fields.get("surveyMultiRespColId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("booleanResponse") != null) {
String buf;
buf = fields.get("booleanResponse");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setBooleanResponse(ibuf);
}

		if(fields.get("currencyResponse") != null) {
String buf;
buf = fields.get("currencyResponse");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCurrencyResponse(bd);
}

		if(fields.get("floatResponse") != null) {
String buf;
buf = fields.get("floatResponse");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFloatResponse(bd);
}

		if(fields.get("numericResponse") != null) {
String buf;
buf = fields.get("numericResponse");
long ibuf = Long.parseLong(buf);
			returnVal.setNumericResponse(ibuf);
}

		if(fields.get("textResponse") != null) {
			returnVal.setTextResponse((String) fields.get("textResponse"));
}

		if(fields.get("surveyOptionSeqId") != null) {
			returnVal.setSurveyOptionSeqId((String) fields.get("surveyOptionSeqId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("answeredDate") != null) {
String buf = fields.get("answeredDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAnsweredDate(ibuf);
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static SurveyResponseAnswer map(GenericValue val) {

SurveyResponseAnswer returnVal = new SurveyResponseAnswer();
		returnVal.setSurveyResponseId(val.getString("surveyResponseId"));
		returnVal.setSurveyQuestionId(val.getString("surveyQuestionId"));
		returnVal.setSurveyMultiRespColId(val.getString("surveyMultiRespColId"));
		returnVal.setSurveyMultiRespId(val.getString("surveyMultiRespId"));
		returnVal.setBooleanResponse(val.getBoolean("booleanResponse"));
		returnVal.setCurrencyResponse(val.getBigDecimal("currencyResponse"));
		returnVal.setFloatResponse(val.getBigDecimal("floatResponse"));
		returnVal.setNumericResponse(val.getLong("numericResponse"));
		returnVal.setTextResponse(val.getString("textResponse"));
		returnVal.setSurveyOptionSeqId(val.getString("surveyOptionSeqId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setAnsweredDate(val.getTimestamp("answeredDate"));
		returnVal.setAmountBase(val.getBigDecimal("amountBase"));
		returnVal.setAmountBaseUomId(val.getString("amountBaseUomId"));
		returnVal.setWeightFactor(val.getBigDecimal("weightFactor"));
		returnVal.setDuration(val.getLong("duration"));
		returnVal.setDurationUomId(val.getString("durationUomId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static SurveyResponseAnswer map(HttpServletRequest request) throws Exception {

		SurveyResponseAnswer returnVal = new SurveyResponseAnswer();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyResponseId")) {
returnVal.setSurveyResponseId(request.getParameter("surveyResponseId"));
}

		if(paramMap.containsKey("surveyQuestionId"))  {
returnVal.setSurveyQuestionId(request.getParameter("surveyQuestionId"));
}
		if(paramMap.containsKey("surveyMultiRespColId"))  {
returnVal.setSurveyMultiRespColId(request.getParameter("surveyMultiRespColId"));
}
		if(paramMap.containsKey("surveyMultiRespId"))  {
returnVal.setSurveyMultiRespId(request.getParameter("surveyMultiRespId"));
}
		if(paramMap.containsKey("booleanResponse"))  {
String buf = request.getParameter("booleanResponse");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setBooleanResponse(ibuf);
}
		if(paramMap.containsKey("currencyResponse"))  {
String buf = request.getParameter("currencyResponse");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCurrencyResponse(bd);
}
		if(paramMap.containsKey("floatResponse"))  {
String buf = request.getParameter("floatResponse");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFloatResponse(bd);
}
		if(paramMap.containsKey("numericResponse"))  {
String buf = request.getParameter("numericResponse");
Long ibuf = Long.parseLong(buf);
returnVal.setNumericResponse(ibuf);
}
		if(paramMap.containsKey("textResponse"))  {
returnVal.setTextResponse(request.getParameter("textResponse"));
}
		if(paramMap.containsKey("surveyOptionSeqId"))  {
returnVal.setSurveyOptionSeqId(request.getParameter("surveyOptionSeqId"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("answeredDate"))  {
String buf = request.getParameter("answeredDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAnsweredDate(ibuf);
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
