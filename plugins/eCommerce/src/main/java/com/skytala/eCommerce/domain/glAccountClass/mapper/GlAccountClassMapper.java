package com.skytala.eCommerce.domain.glAccountClass.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.glAccountClass.model.GlAccountClass;

public class GlAccountClassMapper  {


	public static Map<String, Object> map(GlAccountClass glaccountclass) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountclass.getGlAccountClassId() != null ){
			returnVal.put("glAccountClassId",glaccountclass.getGlAccountClassId());
}

		if(glaccountclass.getParentClassId() != null ){
			returnVal.put("parentClassId",glaccountclass.getParentClassId());
}

		if(glaccountclass.getDescription() != null ){
			returnVal.put("description",glaccountclass.getDescription());
}

		if(glaccountclass.getIsAssetClass() != null ){
			returnVal.put("isAssetClass",glaccountclass.getIsAssetClass());
}

		return returnVal;
}


	public static GlAccountClass map(Map<String, Object> fields) {

		GlAccountClass returnVal = new GlAccountClass();

		if(fields.get("glAccountClassId") != null) {
			returnVal.setGlAccountClassId((String) fields.get("glAccountClassId"));
}

		if(fields.get("parentClassId") != null) {
			returnVal.setParentClassId((String) fields.get("parentClassId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("isAssetClass") != null) {
			returnVal.setIsAssetClass((boolean) fields.get("isAssetClass"));
}


		return returnVal;
 } 
	public static GlAccountClass mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountClass returnVal = new GlAccountClass();

		if(fields.get("glAccountClassId") != null) {
			returnVal.setGlAccountClassId((String) fields.get("glAccountClassId"));
}

		if(fields.get("parentClassId") != null) {
			returnVal.setParentClassId((String) fields.get("parentClassId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("isAssetClass") != null) {
String buf;
buf = fields.get("isAssetClass");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsAssetClass(ibuf);
}


		return returnVal;
 } 
	public static GlAccountClass map(GenericValue val) {

GlAccountClass returnVal = new GlAccountClass();
		returnVal.setGlAccountClassId(val.getString("glAccountClassId"));
		returnVal.setParentClassId(val.getString("parentClassId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setIsAssetClass(val.getBoolean("isAssetClass"));


return returnVal;

}

public static GlAccountClass map(HttpServletRequest request) throws Exception {

		GlAccountClass returnVal = new GlAccountClass();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountClassId")) {
returnVal.setGlAccountClassId(request.getParameter("glAccountClassId"));
}

		if(paramMap.containsKey("parentClassId"))  {
returnVal.setParentClassId(request.getParameter("parentClassId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("isAssetClass"))  {
String buf = request.getParameter("isAssetClass");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsAssetClass(ibuf);
}
return returnVal;

}
}
