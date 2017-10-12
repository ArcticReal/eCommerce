package com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.model.ContentPurposeOperation;

public class ContentPurposeOperationMapper  {


	public static Map<String, Object> map(ContentPurposeOperation contentpurposeoperation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentpurposeoperation.getContentPurposeTypeId() != null ){
			returnVal.put("contentPurposeTypeId",contentpurposeoperation.getContentPurposeTypeId());
}

		if(contentpurposeoperation.getContentOperationId() != null ){
			returnVal.put("contentOperationId",contentpurposeoperation.getContentOperationId());
}

		if(contentpurposeoperation.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",contentpurposeoperation.getRoleTypeId());
}

		if(contentpurposeoperation.getStatusId() != null ){
			returnVal.put("statusId",contentpurposeoperation.getStatusId());
}

		if(contentpurposeoperation.getPrivilegeEnumId() != null ){
			returnVal.put("privilegeEnumId",contentpurposeoperation.getPrivilegeEnumId());
}

		return returnVal;
}


	public static ContentPurposeOperation map(Map<String, Object> fields) {

		ContentPurposeOperation returnVal = new ContentPurposeOperation();

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}

		if(fields.get("contentOperationId") != null) {
			returnVal.setContentOperationId((String) fields.get("contentOperationId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("privilegeEnumId") != null) {
			returnVal.setPrivilegeEnumId((String) fields.get("privilegeEnumId"));
}


		return returnVal;
 } 
	public static ContentPurposeOperation mapstrstr(Map<String, String> fields) throws Exception {

		ContentPurposeOperation returnVal = new ContentPurposeOperation();

		if(fields.get("contentPurposeTypeId") != null) {
			returnVal.setContentPurposeTypeId((String) fields.get("contentPurposeTypeId"));
}

		if(fields.get("contentOperationId") != null) {
			returnVal.setContentOperationId((String) fields.get("contentOperationId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("privilegeEnumId") != null) {
			returnVal.setPrivilegeEnumId((String) fields.get("privilegeEnumId"));
}


		return returnVal;
 } 
	public static ContentPurposeOperation map(GenericValue val) {

ContentPurposeOperation returnVal = new ContentPurposeOperation();
		returnVal.setContentPurposeTypeId(val.getString("contentPurposeTypeId"));
		returnVal.setContentOperationId(val.getString("contentOperationId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPrivilegeEnumId(val.getString("privilegeEnumId"));


return returnVal;

}

public static ContentPurposeOperation map(HttpServletRequest request) throws Exception {

		ContentPurposeOperation returnVal = new ContentPurposeOperation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentPurposeTypeId")) {
returnVal.setContentPurposeTypeId(request.getParameter("contentPurposeTypeId"));
}

		if(paramMap.containsKey("contentOperationId"))  {
returnVal.setContentOperationId(request.getParameter("contentOperationId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("privilegeEnumId"))  {
returnVal.setPrivilegeEnumId(request.getParameter("privilegeEnumId"));
}
return returnVal;

}
}
