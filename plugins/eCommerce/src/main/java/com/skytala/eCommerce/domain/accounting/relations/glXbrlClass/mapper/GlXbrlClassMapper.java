package com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.model.GlXbrlClass;

public class GlXbrlClassMapper  {


	public static Map<String, Object> map(GlXbrlClass glxbrlclass) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glxbrlclass.getGlXbrlClassId() != null ){
			returnVal.put("glXbrlClassId",glxbrlclass.getGlXbrlClassId());
}

		if(glxbrlclass.getDescription() != null ){
			returnVal.put("description",glxbrlclass.getDescription());
}

		return returnVal;
}


	public static GlXbrlClass map(Map<String, Object> fields) {

		GlXbrlClass returnVal = new GlXbrlClass();

		if(fields.get("glXbrlClassId") != null) {
			returnVal.setGlXbrlClassId((String) fields.get("glXbrlClassId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlXbrlClass mapstrstr(Map<String, String> fields) throws Exception {

		GlXbrlClass returnVal = new GlXbrlClass();

		if(fields.get("glXbrlClassId") != null) {
			returnVal.setGlXbrlClassId((String) fields.get("glXbrlClassId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlXbrlClass map(GenericValue val) {

GlXbrlClass returnVal = new GlXbrlClass();
		returnVal.setGlXbrlClassId(val.getString("glXbrlClassId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlXbrlClass map(HttpServletRequest request) throws Exception {

		GlXbrlClass returnVal = new GlXbrlClass();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glXbrlClassId")) {
returnVal.setGlXbrlClassId(request.getParameter("glXbrlClassId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
