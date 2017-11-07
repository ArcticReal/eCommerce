package com.skytala.eCommerce.domain.login.relations.userLogin.mapper.history;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;

public class UserLoginHistoryMapper  {


	public static Map<String, Object> map(UserLoginHistory userloginhistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(userloginhistory.getUserLoginId() != null ){
			returnVal.put("userLoginId",userloginhistory.getUserLoginId());
}

		if(userloginhistory.getVisitId() != null ){
			returnVal.put("visitId",userloginhistory.getVisitId());
}

		if(userloginhistory.getFromDate() != null ){
			returnVal.put("fromDate",userloginhistory.getFromDate());
}

		if(userloginhistory.getThruDate() != null ){
			returnVal.put("thruDate",userloginhistory.getThruDate());
}

		if(userloginhistory.getPasswordUsed() != null ){
			returnVal.put("passwordUsed",userloginhistory.getPasswordUsed());
}

		if(userloginhistory.getSuccessfulLogin() != null ){
			returnVal.put("successfulLogin",userloginhistory.getSuccessfulLogin());
}

		return returnVal;
}


	public static UserLoginHistory map(Map<String, Object> fields) {

		UserLoginHistory returnVal = new UserLoginHistory();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("passwordUsed") != null) {
			returnVal.setPasswordUsed((String) fields.get("passwordUsed"));
}

		if(fields.get("successfulLogin") != null) {
			returnVal.setSuccessfulLogin((boolean) fields.get("successfulLogin"));
}


		return returnVal;
 } 
	public static UserLoginHistory mapstrstr(Map<String, String> fields) throws Exception {

		UserLoginHistory returnVal = new UserLoginHistory();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("passwordUsed") != null) {
			returnVal.setPasswordUsed((String) fields.get("passwordUsed"));
}

		if(fields.get("successfulLogin") != null) {
String buf;
buf = fields.get("successfulLogin");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSuccessfulLogin(ibuf);
}


		return returnVal;
 } 
	public static UserLoginHistory map(GenericValue val) {

UserLoginHistory returnVal = new UserLoginHistory();
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPasswordUsed(val.getString("passwordUsed"));
		returnVal.setSuccessfulLogin(val.getBoolean("successfulLogin"));


return returnVal;

}

public static UserLoginHistory map(HttpServletRequest request) throws Exception {

		UserLoginHistory returnVal = new UserLoginHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("userLoginId")) {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}

		if(paramMap.containsKey("visitId"))  {
returnVal.setVisitId(request.getParameter("visitId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("passwordUsed"))  {
returnVal.setPasswordUsed(request.getParameter("passwordUsed"));
}
		if(paramMap.containsKey("successfulLogin"))  {
String buf = request.getParameter("successfulLogin");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSuccessfulLogin(ibuf);
}
return returnVal;

}
}
