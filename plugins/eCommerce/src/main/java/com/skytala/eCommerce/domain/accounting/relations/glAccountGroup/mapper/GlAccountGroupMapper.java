package com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.model.GlAccountGroup;

public class GlAccountGroupMapper  {


	public static Map<String, Object> map(GlAccountGroup glaccountgroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountgroup.getGlAccountGroupId() != null ){
			returnVal.put("glAccountGroupId",glaccountgroup.getGlAccountGroupId());
}

		if(glaccountgroup.getGlAccountGroupTypeId() != null ){
			returnVal.put("glAccountGroupTypeId",glaccountgroup.getGlAccountGroupTypeId());
}

		if(glaccountgroup.getDescription() != null ){
			returnVal.put("description",glaccountgroup.getDescription());
}

		return returnVal;
}


	public static GlAccountGroup map(Map<String, Object> fields) {

		GlAccountGroup returnVal = new GlAccountGroup();

		if(fields.get("glAccountGroupId") != null) {
			returnVal.setGlAccountGroupId((String) fields.get("glAccountGroupId"));
}

		if(fields.get("glAccountGroupTypeId") != null) {
			returnVal.setGlAccountGroupTypeId((String) fields.get("glAccountGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountGroup mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountGroup returnVal = new GlAccountGroup();

		if(fields.get("glAccountGroupId") != null) {
			returnVal.setGlAccountGroupId((String) fields.get("glAccountGroupId"));
}

		if(fields.get("glAccountGroupTypeId") != null) {
			returnVal.setGlAccountGroupTypeId((String) fields.get("glAccountGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountGroup map(GenericValue val) {

GlAccountGroup returnVal = new GlAccountGroup();
		returnVal.setGlAccountGroupId(val.getString("glAccountGroupId"));
		returnVal.setGlAccountGroupTypeId(val.getString("glAccountGroupTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlAccountGroup map(HttpServletRequest request) throws Exception {

		GlAccountGroup returnVal = new GlAccountGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountGroupId")) {
returnVal.setGlAccountGroupId(request.getParameter("glAccountGroupId"));
}

		if(paramMap.containsKey("glAccountGroupTypeId"))  {
returnVal.setGlAccountGroupTypeId(request.getParameter("glAccountGroupTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
