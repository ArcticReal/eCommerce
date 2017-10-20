package com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.categoryType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryType.GlAccountCategoryType;

public class GlAccountCategoryTypeMapper  {


	public static Map<String, Object> map(GlAccountCategoryType glaccountcategorytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountcategorytype.getGlAccountCategoryTypeId() != null ){
			returnVal.put("glAccountCategoryTypeId",glaccountcategorytype.getGlAccountCategoryTypeId());
}

		if(glaccountcategorytype.getDescription() != null ){
			returnVal.put("description",glaccountcategorytype.getDescription());
}

		return returnVal;
}


	public static GlAccountCategoryType map(Map<String, Object> fields) {

		GlAccountCategoryType returnVal = new GlAccountCategoryType();

		if(fields.get("glAccountCategoryTypeId") != null) {
			returnVal.setGlAccountCategoryTypeId((String) fields.get("glAccountCategoryTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountCategoryType mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountCategoryType returnVal = new GlAccountCategoryType();

		if(fields.get("glAccountCategoryTypeId") != null) {
			returnVal.setGlAccountCategoryTypeId((String) fields.get("glAccountCategoryTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountCategoryType map(GenericValue val) {

GlAccountCategoryType returnVal = new GlAccountCategoryType();
		returnVal.setGlAccountCategoryTypeId(val.getString("glAccountCategoryTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlAccountCategoryType map(HttpServletRequest request) throws Exception {

		GlAccountCategoryType returnVal = new GlAccountCategoryType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountCategoryTypeId")) {
returnVal.setGlAccountCategoryTypeId(request.getParameter("glAccountCategoryTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
