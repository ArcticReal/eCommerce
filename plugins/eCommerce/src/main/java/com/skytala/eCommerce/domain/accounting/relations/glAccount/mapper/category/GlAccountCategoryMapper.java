package com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.category;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.category.GlAccountCategory;

public class GlAccountCategoryMapper  {


	public static Map<String, Object> map(GlAccountCategory glaccountcategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountcategory.getGlAccountCategoryId() != null ){
			returnVal.put("glAccountCategoryId",glaccountcategory.getGlAccountCategoryId());
}

		if(glaccountcategory.getGlAccountCategoryTypeId() != null ){
			returnVal.put("glAccountCategoryTypeId",glaccountcategory.getGlAccountCategoryTypeId());
}

		if(glaccountcategory.getDescription() != null ){
			returnVal.put("description",glaccountcategory.getDescription());
}

		return returnVal;
}


	public static GlAccountCategory map(Map<String, Object> fields) {

		GlAccountCategory returnVal = new GlAccountCategory();

		if(fields.get("glAccountCategoryId") != null) {
			returnVal.setGlAccountCategoryId((String) fields.get("glAccountCategoryId"));
}

		if(fields.get("glAccountCategoryTypeId") != null) {
			returnVal.setGlAccountCategoryTypeId((String) fields.get("glAccountCategoryTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountCategory mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountCategory returnVal = new GlAccountCategory();

		if(fields.get("glAccountCategoryId") != null) {
			returnVal.setGlAccountCategoryId((String) fields.get("glAccountCategoryId"));
}

		if(fields.get("glAccountCategoryTypeId") != null) {
			returnVal.setGlAccountCategoryTypeId((String) fields.get("glAccountCategoryTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountCategory map(GenericValue val) {

GlAccountCategory returnVal = new GlAccountCategory();
		returnVal.setGlAccountCategoryId(val.getString("glAccountCategoryId"));
		returnVal.setGlAccountCategoryTypeId(val.getString("glAccountCategoryTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlAccountCategory map(HttpServletRequest request) throws Exception {

		GlAccountCategory returnVal = new GlAccountCategory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountCategoryId")) {
returnVal.setGlAccountCategoryId(request.getParameter("glAccountCategoryId"));
}

		if(paramMap.containsKey("glAccountCategoryTypeId"))  {
returnVal.setGlAccountCategoryTypeId(request.getParameter("glAccountCategoryTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
