package com.skytala.eCommerce.domain.webPreferenceType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.webPreferenceType.model.WebPreferenceType;

public class WebPreferenceTypeMapper  {


	public static Map<String, Object> map(WebPreferenceType webpreferencetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(webpreferencetype.getWebPreferenceTypeId() != null ){
			returnVal.put("webPreferenceTypeId",webpreferencetype.getWebPreferenceTypeId());
}

		if(webpreferencetype.getDescription() != null ){
			returnVal.put("description",webpreferencetype.getDescription());
}

		return returnVal;
}


	public static WebPreferenceType map(Map<String, Object> fields) {

		WebPreferenceType returnVal = new WebPreferenceType();

		if(fields.get("webPreferenceTypeId") != null) {
			returnVal.setWebPreferenceTypeId((String) fields.get("webPreferenceTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WebPreferenceType mapstrstr(Map<String, String> fields) throws Exception {

		WebPreferenceType returnVal = new WebPreferenceType();

		if(fields.get("webPreferenceTypeId") != null) {
			returnVal.setWebPreferenceTypeId((String) fields.get("webPreferenceTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WebPreferenceType map(GenericValue val) {

WebPreferenceType returnVal = new WebPreferenceType();
		returnVal.setWebPreferenceTypeId(val.getString("webPreferenceTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WebPreferenceType map(HttpServletRequest request) throws Exception {

		WebPreferenceType returnVal = new WebPreferenceType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("webPreferenceTypeId")) {
returnVal.setWebPreferenceTypeId(request.getParameter("webPreferenceTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
