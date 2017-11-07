package com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.permission;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission.SecurityGroupPermission;

public class SecurityGroupPermissionMapper  {


	public static Map<String, Object> map(SecurityGroupPermission securitygrouppermission) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(securitygrouppermission.getGroupId() != null ){
			returnVal.put("groupId",securitygrouppermission.getGroupId());
}

		if(securitygrouppermission.getPermissionId() != null ){
			returnVal.put("permissionId",securitygrouppermission.getPermissionId());
}

		return returnVal;
}


	public static SecurityGroupPermission map(Map<String, Object> fields) {

		SecurityGroupPermission returnVal = new SecurityGroupPermission();

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("permissionId") != null) {
			returnVal.setPermissionId((String) fields.get("permissionId"));
}


		return returnVal;
 } 
	public static SecurityGroupPermission mapstrstr(Map<String, String> fields) throws Exception {

		SecurityGroupPermission returnVal = new SecurityGroupPermission();

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("permissionId") != null) {
			returnVal.setPermissionId((String) fields.get("permissionId"));
}


		return returnVal;
 } 
	public static SecurityGroupPermission map(GenericValue val) {

SecurityGroupPermission returnVal = new SecurityGroupPermission();
		returnVal.setGroupId(val.getString("groupId"));
		returnVal.setPermissionId(val.getString("permissionId"));


return returnVal;

}

public static SecurityGroupPermission map(HttpServletRequest request) throws Exception {

		SecurityGroupPermission returnVal = new SecurityGroupPermission();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("groupId")) {
returnVal.setGroupId(request.getParameter("groupId"));
}

		if(paramMap.containsKey("permissionId"))  {
returnVal.setPermissionId(request.getParameter("permissionId"));
}
return returnVal;

}
}
