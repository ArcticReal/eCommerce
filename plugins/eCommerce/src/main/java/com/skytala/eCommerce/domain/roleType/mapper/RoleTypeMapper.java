package com.skytala.eCommerce.domain.roleType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.roleType.model.RoleType;

public class RoleTypeMapper  {


	public static Map<String, Object> map(RoleType roletype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(roletype.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",roletype.getRoleTypeId());
}

		if(roletype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",roletype.getParentTypeId());
}

		if(roletype.getHasTable() != null ){
			returnVal.put("hasTable",roletype.getHasTable());
}

		if(roletype.getDescription() != null ){
			returnVal.put("description",roletype.getDescription());
}

		return returnVal;
}


	public static RoleType map(Map<String, Object> fields) {

		RoleType returnVal = new RoleType();

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RoleType mapstrstr(Map<String, String> fields) throws Exception {

		RoleType returnVal = new RoleType();

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RoleType map(GenericValue val) {

RoleType returnVal = new RoleType();
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static RoleType map(HttpServletRequest request) throws Exception {

		RoleType returnVal = new RoleType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("roleTypeId")) {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
