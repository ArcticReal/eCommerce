package com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityGroup;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;

public class UserLoginSecurityGroupMapper  {


	public static Map<String, Object> map(UserLoginSecurityGroup userloginsecuritygroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(userloginsecuritygroup.getUserLoginId() != null ){
			returnVal.put("userLoginId",userloginsecuritygroup.getUserLoginId());
}

		if(userloginsecuritygroup.getGroupId() != null ){
			returnVal.put("groupId",userloginsecuritygroup.getGroupId());
}

		if(userloginsecuritygroup.getFromDate() != null ){
			returnVal.put("fromDate",userloginsecuritygroup.getFromDate());
}

		if(userloginsecuritygroup.getThruDate() != null ){
			returnVal.put("thruDate",userloginsecuritygroup.getThruDate());
}

		return returnVal;
}


	public static UserLoginSecurityGroup map(Map<String, Object> fields) {

		UserLoginSecurityGroup returnVal = new UserLoginSecurityGroup();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static UserLoginSecurityGroup mapstrstr(Map<String, String> fields) throws Exception {

		UserLoginSecurityGroup returnVal = new UserLoginSecurityGroup();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
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


		return returnVal;
 } 
	public static UserLoginSecurityGroup map(GenericValue val) {

UserLoginSecurityGroup returnVal = new UserLoginSecurityGroup();
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setGroupId(val.getString("groupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static UserLoginSecurityGroup map(HttpServletRequest request) throws Exception {

		UserLoginSecurityGroup returnVal = new UserLoginSecurityGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("userLoginId")) {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}

		if(paramMap.containsKey("groupId"))  {
returnVal.setGroupId(request.getParameter("groupId"));
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
return returnVal;

}
}
