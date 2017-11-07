package com.skytala.eCommerce.domain.login.relations.userLogin.mapper.session;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;

public class UserLoginSessionMapper  {


	public static Map<String, Object> map(UserLoginSession userloginsession) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(userloginsession.getUserLoginId() != null ){
			returnVal.put("userLoginId",userloginsession.getUserLoginId());
}

		if(userloginsession.getSavedDate() != null ){
			returnVal.put("savedDate",userloginsession.getSavedDate());
}

		if(userloginsession.getSessionData() != null ){
			returnVal.put("sessionData",userloginsession.getSessionData());
}

		return returnVal;
}


	public static UserLoginSession map(Map<String, Object> fields) {

		UserLoginSession returnVal = new UserLoginSession();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("savedDate") != null) {
			returnVal.setSavedDate((Timestamp) fields.get("savedDate"));
}

		if(fields.get("sessionData") != null) {
			returnVal.setSessionData((String) fields.get("sessionData"));
}


		return returnVal;
 } 
	public static UserLoginSession mapstrstr(Map<String, String> fields) throws Exception {

		UserLoginSession returnVal = new UserLoginSession();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("savedDate") != null) {
String buf = fields.get("savedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSavedDate(ibuf);
}

		if(fields.get("sessionData") != null) {
			returnVal.setSessionData((String) fields.get("sessionData"));
}


		return returnVal;
 } 
	public static UserLoginSession map(GenericValue val) {

UserLoginSession returnVal = new UserLoginSession();
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setSavedDate(val.getTimestamp("savedDate"));
		returnVal.setSessionData(val.getString("sessionData"));


return returnVal;

}

public static UserLoginSession map(HttpServletRequest request) throws Exception {

		UserLoginSession returnVal = new UserLoginSession();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("userLoginId")) {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}

		if(paramMap.containsKey("savedDate"))  {
String buf = request.getParameter("savedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setSavedDate(ibuf);
}
		if(paramMap.containsKey("sessionData"))  {
returnVal.setSessionData(request.getParameter("sessionData"));
}
return returnVal;

}
}
