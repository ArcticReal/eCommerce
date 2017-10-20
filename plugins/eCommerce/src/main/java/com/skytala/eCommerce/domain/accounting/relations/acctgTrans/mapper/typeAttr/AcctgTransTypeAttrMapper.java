package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.typeAttr.AcctgTransTypeAttr;

public class AcctgTransTypeAttrMapper  {


	public static Map<String, Object> map(AcctgTransTypeAttr acctgtranstypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(acctgtranstypeattr.getAcctgTransTypeId() != null ){
			returnVal.put("acctgTransTypeId",acctgtranstypeattr.getAcctgTransTypeId());
}

		if(acctgtranstypeattr.getAttrName() != null ){
			returnVal.put("attrName",acctgtranstypeattr.getAttrName());
}

		if(acctgtranstypeattr.getDescription() != null ){
			returnVal.put("description",acctgtranstypeattr.getDescription());
}

		return returnVal;
}


	public static AcctgTransTypeAttr map(Map<String, Object> fields) {

		AcctgTransTypeAttr returnVal = new AcctgTransTypeAttr();

		if(fields.get("acctgTransTypeId") != null) {
			returnVal.setAcctgTransTypeId((String) fields.get("acctgTransTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AcctgTransTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		AcctgTransTypeAttr returnVal = new AcctgTransTypeAttr();

		if(fields.get("acctgTransTypeId") != null) {
			returnVal.setAcctgTransTypeId((String) fields.get("acctgTransTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AcctgTransTypeAttr map(GenericValue val) {

AcctgTransTypeAttr returnVal = new AcctgTransTypeAttr();
		returnVal.setAcctgTransTypeId(val.getString("acctgTransTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AcctgTransTypeAttr map(HttpServletRequest request) throws Exception {

		AcctgTransTypeAttr returnVal = new AcctgTransTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("acctgTransTypeId")) {
returnVal.setAcctgTransTypeId(request.getParameter("acctgTransTypeId"));
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
