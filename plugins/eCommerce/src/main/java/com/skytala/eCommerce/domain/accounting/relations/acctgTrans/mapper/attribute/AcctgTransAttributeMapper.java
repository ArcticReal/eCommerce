package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.attribute.AcctgTransAttribute;

public class AcctgTransAttributeMapper  {


	public static Map<String, Object> map(AcctgTransAttribute acctgtransattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(acctgtransattribute.getAcctgTransId() != null ){
			returnVal.put("acctgTransId",acctgtransattribute.getAcctgTransId());
}

		if(acctgtransattribute.getAttrName() != null ){
			returnVal.put("attrName",acctgtransattribute.getAttrName());
}

		if(acctgtransattribute.getAttrValue() != null ){
			returnVal.put("attrValue",acctgtransattribute.getAttrValue());
}

		if(acctgtransattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",acctgtransattribute.getAttrDescription());
}

		return returnVal;
}


	public static AcctgTransAttribute map(Map<String, Object> fields) {

		AcctgTransAttribute returnVal = new AcctgTransAttribute();

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static AcctgTransAttribute mapstrstr(Map<String, String> fields) throws Exception {

		AcctgTransAttribute returnVal = new AcctgTransAttribute();

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static AcctgTransAttribute map(GenericValue val) {

AcctgTransAttribute returnVal = new AcctgTransAttribute();
		returnVal.setAcctgTransId(val.getString("acctgTransId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static AcctgTransAttribute map(HttpServletRequest request) throws Exception {

		AcctgTransAttribute returnVal = new AcctgTransAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("acctgTransId")) {
returnVal.setAcctgTransId(request.getParameter("acctgTransId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
