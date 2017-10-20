package com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.groupMember;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupMember.GlAccountGroupMember;

public class GlAccountGroupMemberMapper  {


	public static Map<String, Object> map(GlAccountGroupMember glaccountgroupmember) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountgroupmember.getGlAccountId() != null ){
			returnVal.put("glAccountId",glaccountgroupmember.getGlAccountId());
}

		if(glaccountgroupmember.getGlAccountGroupTypeId() != null ){
			returnVal.put("glAccountGroupTypeId",glaccountgroupmember.getGlAccountGroupTypeId());
}

		if(glaccountgroupmember.getGlAccountGroupId() != null ){
			returnVal.put("glAccountGroupId",glaccountgroupmember.getGlAccountGroupId());
}

		return returnVal;
}


	public static GlAccountGroupMember map(Map<String, Object> fields) {

		GlAccountGroupMember returnVal = new GlAccountGroupMember();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("glAccountGroupTypeId") != null) {
			returnVal.setGlAccountGroupTypeId((String) fields.get("glAccountGroupTypeId"));
}

		if(fields.get("glAccountGroupId") != null) {
			returnVal.setGlAccountGroupId((String) fields.get("glAccountGroupId"));
}


		return returnVal;
 } 
	public static GlAccountGroupMember mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountGroupMember returnVal = new GlAccountGroupMember();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("glAccountGroupTypeId") != null) {
			returnVal.setGlAccountGroupTypeId((String) fields.get("glAccountGroupTypeId"));
}

		if(fields.get("glAccountGroupId") != null) {
			returnVal.setGlAccountGroupId((String) fields.get("glAccountGroupId"));
}


		return returnVal;
 } 
	public static GlAccountGroupMember map(GenericValue val) {

GlAccountGroupMember returnVal = new GlAccountGroupMember();
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setGlAccountGroupTypeId(val.getString("glAccountGroupTypeId"));
		returnVal.setGlAccountGroupId(val.getString("glAccountGroupId"));


return returnVal;

}

public static GlAccountGroupMember map(HttpServletRequest request) throws Exception {

		GlAccountGroupMember returnVal = new GlAccountGroupMember();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountId")) {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}

		if(paramMap.containsKey("glAccountGroupTypeId"))  {
returnVal.setGlAccountGroupTypeId(request.getParameter("glAccountGroupTypeId"));
}
		if(paramMap.containsKey("glAccountGroupId"))  {
returnVal.setGlAccountGroupId(request.getParameter("glAccountGroupId"));
}
return returnVal;

}
}
