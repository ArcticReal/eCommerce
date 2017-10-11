package com.skytala.eCommerce.domain.party.relations.termTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.termTypeAttr.model.TermTypeAttr;

public class TermTypeAttrMapper  {


	public static Map<String, Object> map(TermTypeAttr termtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(termtypeattr.getTermTypeId() != null ){
			returnVal.put("termTypeId",termtypeattr.getTermTypeId());
}

		if(termtypeattr.getAttrName() != null ){
			returnVal.put("attrName",termtypeattr.getAttrName());
}

		if(termtypeattr.getDescription() != null ){
			returnVal.put("description",termtypeattr.getDescription());
}

		return returnVal;
}


	public static TermTypeAttr map(Map<String, Object> fields) {

		TermTypeAttr returnVal = new TermTypeAttr();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TermTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		TermTypeAttr returnVal = new TermTypeAttr();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TermTypeAttr map(GenericValue val) {

TermTypeAttr returnVal = new TermTypeAttr();
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TermTypeAttr map(HttpServletRequest request) throws Exception {

		TermTypeAttr returnVal = new TermTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("termTypeId")) {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
