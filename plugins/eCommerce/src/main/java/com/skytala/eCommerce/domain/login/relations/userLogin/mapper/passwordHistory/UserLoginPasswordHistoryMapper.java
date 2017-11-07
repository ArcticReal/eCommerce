package com.skytala.eCommerce.domain.login.relations.userLogin.mapper.passwordHistory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;

public class UserLoginPasswordHistoryMapper  {


	public static Map<String, Object> map(UserLoginPasswordHistory userloginpasswordhistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(userloginpasswordhistory.getUserLoginId() != null ){
			returnVal.put("userLoginId",userloginpasswordhistory.getUserLoginId());
}

		if(userloginpasswordhistory.getFromDate() != null ){
			returnVal.put("fromDate",userloginpasswordhistory.getFromDate());
}

		if(userloginpasswordhistory.getThruDate() != null ){
			returnVal.put("thruDate",userloginpasswordhistory.getThruDate());
}

		if(userloginpasswordhistory.getCurrentPassword() != null ){
			returnVal.put("currentPassword",userloginpasswordhistory.getCurrentPassword());
}

		return returnVal;
}


	public static UserLoginPasswordHistory map(Map<String, Object> fields) {

		UserLoginPasswordHistory returnVal = new UserLoginPasswordHistory();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("currentPassword") != null) {
			returnVal.setCurrentPassword((String) fields.get("currentPassword"));
}


		return returnVal;
 } 
	public static UserLoginPasswordHistory mapstrstr(Map<String, String> fields) throws Exception {

		UserLoginPasswordHistory returnVal = new UserLoginPasswordHistory();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
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

		if(fields.get("currentPassword") != null) {
			returnVal.setCurrentPassword((String) fields.get("currentPassword"));
}


		return returnVal;
 } 
	public static UserLoginPasswordHistory map(GenericValue val) {

UserLoginPasswordHistory returnVal = new UserLoginPasswordHistory();
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setCurrentPassword(val.getString("currentPassword"));


return returnVal;

}

public static UserLoginPasswordHistory map(HttpServletRequest request) throws Exception {

		UserLoginPasswordHistory returnVal = new UserLoginPasswordHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("userLoginId")) {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
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
		if(paramMap.containsKey("currentPassword"))  {
returnVal.setCurrentPassword(request.getParameter("currentPassword"));
}
return returnVal;

}
}
