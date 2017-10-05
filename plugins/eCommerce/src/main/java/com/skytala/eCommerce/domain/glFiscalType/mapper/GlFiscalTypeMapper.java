package com.skytala.eCommerce.domain.glFiscalType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.glFiscalType.model.GlFiscalType;

public class GlFiscalTypeMapper  {


	public static Map<String, Object> map(GlFiscalType glfiscaltype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glfiscaltype.getGlFiscalTypeId() != null ){
			returnVal.put("glFiscalTypeId",glfiscaltype.getGlFiscalTypeId());
}

		if(glfiscaltype.getDescription() != null ){
			returnVal.put("description",glfiscaltype.getDescription());
}

		return returnVal;
}


	public static GlFiscalType map(Map<String, Object> fields) {

		GlFiscalType returnVal = new GlFiscalType();

		if(fields.get("glFiscalTypeId") != null) {
			returnVal.setGlFiscalTypeId((String) fields.get("glFiscalTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlFiscalType mapstrstr(Map<String, String> fields) throws Exception {

		GlFiscalType returnVal = new GlFiscalType();

		if(fields.get("glFiscalTypeId") != null) {
			returnVal.setGlFiscalTypeId((String) fields.get("glFiscalTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlFiscalType map(GenericValue val) {

GlFiscalType returnVal = new GlFiscalType();
		returnVal.setGlFiscalTypeId(val.getString("glFiscalTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlFiscalType map(HttpServletRequest request) throws Exception {

		GlFiscalType returnVal = new GlFiscalType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glFiscalTypeId")) {
returnVal.setGlFiscalTypeId(request.getParameter("glFiscalTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
