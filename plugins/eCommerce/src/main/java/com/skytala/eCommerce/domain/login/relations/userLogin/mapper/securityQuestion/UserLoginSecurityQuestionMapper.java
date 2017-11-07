package com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityQuestion;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;

public class UserLoginSecurityQuestionMapper  {


	public static Map<String, Object> map(UserLoginSecurityQuestion userloginsecurityquestion) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(userloginsecurityquestion.getQuestionEnumId() != null ){
			returnVal.put("questionEnumId",userloginsecurityquestion.getQuestionEnumId());
}

		if(userloginsecurityquestion.getUserLoginId() != null ){
			returnVal.put("userLoginId",userloginsecurityquestion.getUserLoginId());
}

		if(userloginsecurityquestion.getSecurityAnswer() != null ){
			returnVal.put("securityAnswer",userloginsecurityquestion.getSecurityAnswer());
}

		return returnVal;
}


	public static UserLoginSecurityQuestion map(Map<String, Object> fields) {

		UserLoginSecurityQuestion returnVal = new UserLoginSecurityQuestion();

		if(fields.get("questionEnumId") != null) {
			returnVal.setQuestionEnumId((String) fields.get("questionEnumId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("securityAnswer") != null) {
			returnVal.setSecurityAnswer((String) fields.get("securityAnswer"));
}


		return returnVal;
 } 
	public static UserLoginSecurityQuestion mapstrstr(Map<String, String> fields) throws Exception {

		UserLoginSecurityQuestion returnVal = new UserLoginSecurityQuestion();

		if(fields.get("questionEnumId") != null) {
			returnVal.setQuestionEnumId((String) fields.get("questionEnumId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("securityAnswer") != null) {
			returnVal.setSecurityAnswer((String) fields.get("securityAnswer"));
}


		return returnVal;
 } 
	public static UserLoginSecurityQuestion map(GenericValue val) {

UserLoginSecurityQuestion returnVal = new UserLoginSecurityQuestion();
		returnVal.setQuestionEnumId(val.getString("questionEnumId"));
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setSecurityAnswer(val.getString("securityAnswer"));


return returnVal;

}

public static UserLoginSecurityQuestion map(HttpServletRequest request) throws Exception {

		UserLoginSecurityQuestion returnVal = new UserLoginSecurityQuestion();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("questionEnumId")) {
returnVal.setQuestionEnumId(request.getParameter("questionEnumId"));
}

		if(paramMap.containsKey("userLoginId"))  {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}
		if(paramMap.containsKey("securityAnswer"))  {
returnVal.setSecurityAnswer(request.getParameter("securityAnswer"));
}
return returnVal;

}
}
