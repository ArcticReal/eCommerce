package com.skytala.eCommerce.domain.content.relations.surveyQuestionType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionType.model.SurveyQuestionType;

public class SurveyQuestionTypeMapper  {


	public static Map<String, Object> map(SurveyQuestionType surveyquestiontype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyquestiontype.getSurveyQuestionTypeId() != null ){
			returnVal.put("surveyQuestionTypeId",surveyquestiontype.getSurveyQuestionTypeId());
}

		if(surveyquestiontype.getDescription() != null ){
			returnVal.put("description",surveyquestiontype.getDescription());
}

		return returnVal;
}


	public static SurveyQuestionType map(Map<String, Object> fields) {

		SurveyQuestionType returnVal = new SurveyQuestionType();

		if(fields.get("surveyQuestionTypeId") != null) {
			returnVal.setSurveyQuestionTypeId((String) fields.get("surveyQuestionTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SurveyQuestionType mapstrstr(Map<String, String> fields) throws Exception {

		SurveyQuestionType returnVal = new SurveyQuestionType();

		if(fields.get("surveyQuestionTypeId") != null) {
			returnVal.setSurveyQuestionTypeId((String) fields.get("surveyQuestionTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SurveyQuestionType map(GenericValue val) {

SurveyQuestionType returnVal = new SurveyQuestionType();
		returnVal.setSurveyQuestionTypeId(val.getString("surveyQuestionTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SurveyQuestionType map(HttpServletRequest request) throws Exception {

		SurveyQuestionType returnVal = new SurveyQuestionType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyQuestionTypeId")) {
returnVal.setSurveyQuestionTypeId(request.getParameter("surveyQuestionTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
