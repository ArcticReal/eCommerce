package com.skytala.eCommerce.domain.accounting.relations.glResourceType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;

public class GlResourceTypeMapper  {


	public static Map<String, Object> map(GlResourceType glresourcetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glresourcetype.getGlResourceTypeId() != null ){
			returnVal.put("glResourceTypeId",glresourcetype.getGlResourceTypeId());
}

		if(glresourcetype.getDescription() != null ){
			returnVal.put("description",glresourcetype.getDescription());
}

		return returnVal;
}


	public static GlResourceType map(Map<String, Object> fields) {

		GlResourceType returnVal = new GlResourceType();

		if(fields.get("glResourceTypeId") != null) {
			returnVal.setGlResourceTypeId((String) fields.get("glResourceTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlResourceType mapstrstr(Map<String, String> fields) throws Exception {

		GlResourceType returnVal = new GlResourceType();

		if(fields.get("glResourceTypeId") != null) {
			returnVal.setGlResourceTypeId((String) fields.get("glResourceTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlResourceType map(GenericValue val) {

GlResourceType returnVal = new GlResourceType();
		returnVal.setGlResourceTypeId(val.getString("glResourceTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlResourceType map(HttpServletRequest request) throws Exception {

		GlResourceType returnVal = new GlResourceType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glResourceTypeId")) {
returnVal.setGlResourceTypeId(request.getParameter("glResourceTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
