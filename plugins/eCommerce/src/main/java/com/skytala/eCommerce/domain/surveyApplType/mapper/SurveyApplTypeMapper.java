package com.skytala.eCommerce.domain.surveyApplType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.surveyApplType.model.SurveyApplType;

public class SurveyApplTypeMapper  {


	public static Map<String, Object> map(SurveyApplType surveyappltype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveyappltype.getSurveyApplTypeId() != null ){
			returnVal.put("surveyApplTypeId",surveyappltype.getSurveyApplTypeId());
}

		if(surveyappltype.getDescription() != null ){
			returnVal.put("description",surveyappltype.getDescription());
}

		return returnVal;
}


	public static SurveyApplType map(Map<String, Object> fields) {

		SurveyApplType returnVal = new SurveyApplType();

		if(fields.get("surveyApplTypeId") != null) {
			returnVal.setSurveyApplTypeId((String) fields.get("surveyApplTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SurveyApplType mapstrstr(Map<String, String> fields) throws Exception {

		SurveyApplType returnVal = new SurveyApplType();

		if(fields.get("surveyApplTypeId") != null) {
			returnVal.setSurveyApplTypeId((String) fields.get("surveyApplTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SurveyApplType map(GenericValue val) {

SurveyApplType returnVal = new SurveyApplType();
		returnVal.setSurveyApplTypeId(val.getString("surveyApplTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SurveyApplType map(HttpServletRequest request) throws Exception {

		SurveyApplType returnVal = new SurveyApplType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyApplTypeId")) {
returnVal.setSurveyApplTypeId(request.getParameter("surveyApplTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
