package com.skytala.eCommerce.domain.content.relations.survey.mapper.page;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.survey.model.page.SurveyPage;

public class SurveyPageMapper  {


	public static Map<String, Object> map(SurveyPage surveypage) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveypage.getSurveyId() != null ){
			returnVal.put("surveyId",surveypage.getSurveyId());
}

		if(surveypage.getSurveyPageSeqId() != null ){
			returnVal.put("surveyPageSeqId",surveypage.getSurveyPageSeqId());
}

		if(surveypage.getPageName() != null ){
			returnVal.put("pageName",surveypage.getPageName());
}

		if(surveypage.getSequenceNum() != null ){
			returnVal.put("sequenceNum",surveypage.getSequenceNum());
}

		return returnVal;
}


	public static SurveyPage map(Map<String, Object> fields) {

		SurveyPage returnVal = new SurveyPage();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyPageSeqId") != null) {
			returnVal.setSurveyPageSeqId((String) fields.get("surveyPageSeqId"));
}

		if(fields.get("pageName") != null) {
			returnVal.setPageName((String) fields.get("pageName"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static SurveyPage mapstrstr(Map<String, String> fields) throws Exception {

		SurveyPage returnVal = new SurveyPage();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyPageSeqId") != null) {
			returnVal.setSurveyPageSeqId((String) fields.get("surveyPageSeqId"));
}

		if(fields.get("pageName") != null) {
			returnVal.setPageName((String) fields.get("pageName"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static SurveyPage map(GenericValue val) {

SurveyPage returnVal = new SurveyPage();
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setSurveyPageSeqId(val.getString("surveyPageSeqId"));
		returnVal.setPageName(val.getString("pageName"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static SurveyPage map(HttpServletRequest request) throws Exception {

		SurveyPage returnVal = new SurveyPage();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyId")) {
returnVal.setSurveyId(request.getParameter("surveyId"));
}

		if(paramMap.containsKey("surveyPageSeqId"))  {
returnVal.setSurveyPageSeqId(request.getParameter("surveyPageSeqId"));
}
		if(paramMap.containsKey("pageName"))  {
returnVal.setPageName(request.getParameter("pageName"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
