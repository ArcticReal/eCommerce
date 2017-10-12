package com.skytala.eCommerce.domain.content.relations.surveyResponse.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyResponse.model.SurveyResponse;

public class SurveyResponseMapper  {


	public static Map<String, Object> map(SurveyResponse surveyresponse) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyresponse.getSurveyResponseId() != null ){
			returnVal.put("surveyResponseId",surveyresponse.getSurveyResponseId());
}

		if(surveyresponse.getSurveyId() != null ){
			returnVal.put("surveyId",surveyresponse.getSurveyId());
}

		if(surveyresponse.getPartyId() != null ){
			returnVal.put("partyId",surveyresponse.getPartyId());
}

		if(surveyresponse.getResponseDate() != null ){
			returnVal.put("responseDate",surveyresponse.getResponseDate());
}

		if(surveyresponse.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",surveyresponse.getLastModifiedDate());
}

		if(surveyresponse.getReferenceId() != null ){
			returnVal.put("referenceId",surveyresponse.getReferenceId());
}

		if(surveyresponse.getGeneralFeedback() != null ){
			returnVal.put("generalFeedback",surveyresponse.getGeneralFeedback());
}

		if(surveyresponse.getOrderId() != null ){
			returnVal.put("orderId",surveyresponse.getOrderId());
}

		if(surveyresponse.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",surveyresponse.getOrderItemSeqId());
}

		if(surveyresponse.getStatusId() != null ){
			returnVal.put("statusId",surveyresponse.getStatusId());
}

		return returnVal;
}


	public static SurveyResponse map(Map<String, Object> fields) {

		SurveyResponse returnVal = new SurveyResponse();

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("responseDate") != null) {
			returnVal.setResponseDate((Timestamp) fields.get("responseDate"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("referenceId") != null) {
			returnVal.setReferenceId((String) fields.get("referenceId"));
}

		if(fields.get("generalFeedback") != null) {
			returnVal.setGeneralFeedback((String) fields.get("generalFeedback"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static SurveyResponse mapstrstr(Map<String, String> fields) throws Exception {

		SurveyResponse returnVal = new SurveyResponse();

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("responseDate") != null) {
String buf = fields.get("responseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setResponseDate(ibuf);
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("referenceId") != null) {
			returnVal.setReferenceId((String) fields.get("referenceId"));
}

		if(fields.get("generalFeedback") != null) {
			returnVal.setGeneralFeedback((String) fields.get("generalFeedback"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static SurveyResponse map(GenericValue val) {

SurveyResponse returnVal = new SurveyResponse();
		returnVal.setSurveyResponseId(val.getString("surveyResponseId"));
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setResponseDate(val.getTimestamp("responseDate"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setReferenceId(val.getString("referenceId"));
		returnVal.setGeneralFeedback(val.getString("generalFeedback"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setStatusId(val.getString("statusId"));


return returnVal;

}

public static SurveyResponse map(HttpServletRequest request) throws Exception {

		SurveyResponse returnVal = new SurveyResponse();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyResponseId")) {
returnVal.setSurveyResponseId(request.getParameter("surveyResponseId"));
}

		if(paramMap.containsKey("surveyId"))  {
returnVal.setSurveyId(request.getParameter("surveyId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("responseDate"))  {
String buf = request.getParameter("responseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setResponseDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("referenceId"))  {
returnVal.setReferenceId(request.getParameter("referenceId"));
}
		if(paramMap.containsKey("generalFeedback"))  {
returnVal.setGeneralFeedback(request.getParameter("generalFeedback"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
return returnVal;

}
}
