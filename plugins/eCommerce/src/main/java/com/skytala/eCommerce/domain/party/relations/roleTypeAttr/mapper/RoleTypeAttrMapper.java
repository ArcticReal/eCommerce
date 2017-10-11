package com.skytala.eCommerce.domain.party.relations.roleTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.model.RoleTypeAttr;

public class RoleTypeAttrMapper  {


	public static Map<String, Object> map(RoleTypeAttr roletypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(roletypeattr.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",roletypeattr.getRoleTypeId());
}

		if(roletypeattr.getAttrName() != null ){
			returnVal.put("attrName",roletypeattr.getAttrName());
}

		if(roletypeattr.getDescription() != null ){
			returnVal.put("description",roletypeattr.getDescription());
}

		return returnVal;
}


	public static RoleTypeAttr map(Map<String, Object> fields) {

		RoleTypeAttr returnVal = new RoleTypeAttr();

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RoleTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		RoleTypeAttr returnVal = new RoleTypeAttr();

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RoleTypeAttr map(GenericValue val) {

RoleTypeAttr returnVal = new RoleTypeAttr();
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static RoleTypeAttr map(HttpServletRequest request) throws Exception {

		RoleTypeAttr returnVal = new RoleTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("roleTypeId")) {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
