package com.skytala.eCommerce.domain.login.relations.securityGroup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;

public class SecurityGroupMapper  {


	public static Map<String, Object> map(SecurityGroup securitygroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(securitygroup.getGroupId() != null ){
			returnVal.put("groupId",securitygroup.getGroupId());
}

		if(securitygroup.getDescription() != null ){
			returnVal.put("description",securitygroup.getDescription());
}

		return returnVal;
}


	public static SecurityGroup map(Map<String, Object> fields) {

		SecurityGroup returnVal = new SecurityGroup();

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SecurityGroup mapstrstr(Map<String, String> fields) throws Exception {

		SecurityGroup returnVal = new SecurityGroup();

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SecurityGroup map(GenericValue val) {

SecurityGroup returnVal = new SecurityGroup();
		returnVal.setGroupId(val.getString("groupId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SecurityGroup map(HttpServletRequest request) throws Exception {

		SecurityGroup returnVal = new SecurityGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("groupId")) {
returnVal.setGroupId(request.getParameter("groupId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
