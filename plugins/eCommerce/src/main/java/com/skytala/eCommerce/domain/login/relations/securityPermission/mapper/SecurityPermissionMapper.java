package com.skytala.eCommerce.domain.login.relations.securityPermission.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;

public class SecurityPermissionMapper  {


	public static Map<String, Object> map(SecurityPermission securitypermission) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(securitypermission.getPermissionId() != null ){
			returnVal.put("permissionId",securitypermission.getPermissionId());
}

		if(securitypermission.getDescription() != null ){
			returnVal.put("description",securitypermission.getDescription());
}

		return returnVal;
}


	public static SecurityPermission map(Map<String, Object> fields) {

		SecurityPermission returnVal = new SecurityPermission();

		if(fields.get("permissionId") != null) {
			returnVal.setPermissionId((String) fields.get("permissionId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SecurityPermission mapstrstr(Map<String, String> fields) throws Exception {

		SecurityPermission returnVal = new SecurityPermission();

		if(fields.get("permissionId") != null) {
			returnVal.setPermissionId((String) fields.get("permissionId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SecurityPermission map(GenericValue val) {

SecurityPermission returnVal = new SecurityPermission();
		returnVal.setPermissionId(val.getString("permissionId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SecurityPermission map(HttpServletRequest request) throws Exception {

		SecurityPermission returnVal = new SecurityPermission();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("permissionId")) {
returnVal.setPermissionId(request.getParameter("permissionId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
