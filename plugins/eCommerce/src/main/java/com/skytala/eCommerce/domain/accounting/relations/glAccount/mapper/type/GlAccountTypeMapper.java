package com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;

public class GlAccountTypeMapper  {


	public static Map<String, Object> map(GlAccountType glaccounttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccounttype.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",glaccounttype.getGlAccountTypeId());
}

		if(glaccounttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",glaccounttype.getParentTypeId());
}

		if(glaccounttype.getHasTable() != null ){
			returnVal.put("hasTable",glaccounttype.getHasTable());
}

		if(glaccounttype.getDescription() != null ){
			returnVal.put("description",glaccounttype.getDescription());
}

		return returnVal;
}


	public static GlAccountType map(Map<String, Object> fields) {

		GlAccountType returnVal = new GlAccountType();

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountType mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountType returnVal = new GlAccountType();

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static GlAccountType map(GenericValue val) {

GlAccountType returnVal = new GlAccountType();
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GlAccountType map(HttpServletRequest request) throws Exception {

		GlAccountType returnVal = new GlAccountType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountTypeId")) {
returnVal.setGlAccountTypeId(request.getParameter("glAccountTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
