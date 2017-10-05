package com.skytala.eCommerce.domain.surveyQuestionCategory.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.surveyQuestionCategory.model.SurveyQuestionCategory;

public class SurveyQuestionCategoryMapper  {


	public static Map<String, Object> map(SurveyQuestionCategory surveyquestioncategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyquestioncategory.getSurveyQuestionCategoryId() != null ){
			returnVal.put("surveyQuestionCategoryId",surveyquestioncategory.getSurveyQuestionCategoryId());
}

		if(surveyquestioncategory.getParentCategoryId() != null ){
			returnVal.put("parentCategoryId",surveyquestioncategory.getParentCategoryId());
}

		if(surveyquestioncategory.getDescription() != null ){
			returnVal.put("description",surveyquestioncategory.getDescription());
}

		return returnVal;
}


	public static SurveyQuestionCategory map(Map<String, Object> fields) {

		SurveyQuestionCategory returnVal = new SurveyQuestionCategory();

		if(fields.get("surveyQuestionCategoryId") != null) {
			returnVal.setSurveyQuestionCategoryId((String) fields.get("surveyQuestionCategoryId"));
}

		if(fields.get("parentCategoryId") != null) {
			returnVal.setParentCategoryId((String) fields.get("parentCategoryId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SurveyQuestionCategory mapstrstr(Map<String, String> fields) throws Exception {

		SurveyQuestionCategory returnVal = new SurveyQuestionCategory();

		if(fields.get("surveyQuestionCategoryId") != null) {
			returnVal.setSurveyQuestionCategoryId((String) fields.get("surveyQuestionCategoryId"));
}

		if(fields.get("parentCategoryId") != null) {
			returnVal.setParentCategoryId((String) fields.get("parentCategoryId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SurveyQuestionCategory map(GenericValue val) {

SurveyQuestionCategory returnVal = new SurveyQuestionCategory();
		returnVal.setSurveyQuestionCategoryId(val.getString("surveyQuestionCategoryId"));
		returnVal.setParentCategoryId(val.getString("parentCategoryId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SurveyQuestionCategory map(HttpServletRequest request) throws Exception {

		SurveyQuestionCategory returnVal = new SurveyQuestionCategory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyQuestionCategoryId")) {
returnVal.setSurveyQuestionCategoryId(request.getParameter("surveyQuestionCategoryId"));
}

		if(paramMap.containsKey("parentCategoryId"))  {
returnVal.setParentCategoryId(request.getParameter("parentCategoryId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
