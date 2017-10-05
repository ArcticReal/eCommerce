package com.skytala.eCommerce.domain.glAccountGroupType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.glAccountGroupType.model.GlAccountGroupType;

public class GlAccountGroupTypeMapper  {


	public static Map<String, Object> map(GlAccountGroupType glaccountgrouptype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountgrouptype.getGlAccountGroupTypeId() != null ){
			returnVal.put("glAccountGroupTypeId",glaccountgrouptype.getGlAccountGroupTypeId());
}

		if(glaccountgrouptype.getDescription() != null ){
			returnVal.put("description",glaccountgrouptype.getDescription());
}

		return returnVal;
}


	public static GlAccountGroupType map(Map<String, Object> fields) {

		GlAccountGroupType returnVal = new GlAccountGroupType();

		if(fields.get("glAccountGroupTypeId") != null) {
			returnVal.setGlAccountGroupTypeId((String) fields.get("glAccountGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountGroupType mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountGroupType returnVal = new GlAccountGroupType();

		if(fields.get("glAccountGroupTypeId") != null) {
			returnVal.setGlAccountGroupTypeId((String) fields.get("glAccountGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountGroupType map(GenericValue val) {

GlAccountGroupType returnVal = new GlAccountGroupType();
		returnVal.setGlAccountGroupTypeId(val.getString("glAccountGroupTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlAccountGroupType map(HttpServletRequest request) throws Exception {

		GlAccountGroupType returnVal = new GlAccountGroupType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountGroupTypeId")) {
returnVal.setGlAccountGroupTypeId(request.getParameter("glAccountGroupTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
