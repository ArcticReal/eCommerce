package com.skytala.eCommerce.domain.content.relations.surveyMultiResp.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyMultiResp.model.SurveyMultiResp;

public class SurveyMultiRespMapper  {


	public static Map<String, Object> map(SurveyMultiResp surveymultiresp) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveymultiresp.getSurveyId() != null ){
			returnVal.put("surveyId",surveymultiresp.getSurveyId());
}

		if(surveymultiresp.getSurveyMultiRespId() != null ){
			returnVal.put("surveyMultiRespId",surveymultiresp.getSurveyMultiRespId());
}

		if(surveymultiresp.getMultiRespTitle() != null ){
			returnVal.put("multiRespTitle",surveymultiresp.getMultiRespTitle());
}

		return returnVal;
}


	public static SurveyMultiResp map(Map<String, Object> fields) {

		SurveyMultiResp returnVal = new SurveyMultiResp();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("multiRespTitle") != null) {
			returnVal.setMultiRespTitle((String) fields.get("multiRespTitle"));
}


		return returnVal;
 } 
	public static SurveyMultiResp mapstrstr(Map<String, String> fields) throws Exception {

		SurveyMultiResp returnVal = new SurveyMultiResp();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyMultiRespId") != null) {
			returnVal.setSurveyMultiRespId((String) fields.get("surveyMultiRespId"));
}

		if(fields.get("multiRespTitle") != null) {
			returnVal.setMultiRespTitle((String) fields.get("multiRespTitle"));
}


		return returnVal;
 } 
	public static SurveyMultiResp map(GenericValue val) {

SurveyMultiResp returnVal = new SurveyMultiResp();
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setSurveyMultiRespId(val.getString("surveyMultiRespId"));
		returnVal.setMultiRespTitle(val.getString("multiRespTitle"));


return returnVal;

}

public static SurveyMultiResp map(HttpServletRequest request) throws Exception {

		SurveyMultiResp returnVal = new SurveyMultiResp();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyId")) {
returnVal.setSurveyId(request.getParameter("surveyId"));
}

		if(paramMap.containsKey("surveyMultiRespId"))  {
returnVal.setSurveyMultiRespId(request.getParameter("surveyMultiRespId"));
}
		if(paramMap.containsKey("multiRespTitle"))  {
returnVal.setMultiRespTitle(request.getParameter("multiRespTitle"));
}
return returnVal;

}
}
