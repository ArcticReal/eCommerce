package com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transTypeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;

public class FinAccountTransTypeAttrMapper  {


	public static Map<String, Object> map(FinAccountTransTypeAttr finaccounttranstypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccounttranstypeattr.getFinAccountTransTypeId() != null ){
			returnVal.put("finAccountTransTypeId",finaccounttranstypeattr.getFinAccountTransTypeId());
}

		if(finaccounttranstypeattr.getAttrName() != null ){
			returnVal.put("attrName",finaccounttranstypeattr.getAttrName());
}

		if(finaccounttranstypeattr.getDescription() != null ){
			returnVal.put("description",finaccounttranstypeattr.getDescription());
}

		return returnVal;
}


	public static FinAccountTransTypeAttr map(Map<String, Object> fields) {

		FinAccountTransTypeAttr returnVal = new FinAccountTransTypeAttr();

		if(fields.get("finAccountTransTypeId") != null) {
			returnVal.setFinAccountTransTypeId((String) fields.get("finAccountTransTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FinAccountTransTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountTransTypeAttr returnVal = new FinAccountTransTypeAttr();

		if(fields.get("finAccountTransTypeId") != null) {
			returnVal.setFinAccountTransTypeId((String) fields.get("finAccountTransTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FinAccountTransTypeAttr map(GenericValue val) {

FinAccountTransTypeAttr returnVal = new FinAccountTransTypeAttr();
		returnVal.setFinAccountTransTypeId(val.getString("finAccountTransTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FinAccountTransTypeAttr map(HttpServletRequest request) throws Exception {

		FinAccountTransTypeAttr returnVal = new FinAccountTransTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountTransTypeId")) {
returnVal.setFinAccountTransTypeId(request.getParameter("finAccountTransTypeId"));
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
