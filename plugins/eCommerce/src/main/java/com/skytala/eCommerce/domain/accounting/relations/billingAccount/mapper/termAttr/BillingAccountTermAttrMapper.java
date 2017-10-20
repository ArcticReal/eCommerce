package com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.termAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.termAttr.BillingAccountTermAttr;

public class BillingAccountTermAttrMapper  {


	public static Map<String, Object> map(BillingAccountTermAttr billingaccounttermattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(billingaccounttermattr.getBillingAccountTermId() != null ){
			returnVal.put("billingAccountTermId",billingaccounttermattr.getBillingAccountTermId());
}

		if(billingaccounttermattr.getAttrName() != null ){
			returnVal.put("attrName",billingaccounttermattr.getAttrName());
}

		if(billingaccounttermattr.getAttrValue() != null ){
			returnVal.put("attrValue",billingaccounttermattr.getAttrValue());
}

		return returnVal;
}


	public static BillingAccountTermAttr map(Map<String, Object> fields) {

		BillingAccountTermAttr returnVal = new BillingAccountTermAttr();

		if(fields.get("billingAccountTermId") != null) {
			returnVal.setBillingAccountTermId((String) fields.get("billingAccountTermId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}


		return returnVal;
 } 
	public static BillingAccountTermAttr mapstrstr(Map<String, String> fields) throws Exception {

		BillingAccountTermAttr returnVal = new BillingAccountTermAttr();

		if(fields.get("billingAccountTermId") != null) {
			returnVal.setBillingAccountTermId((String) fields.get("billingAccountTermId"));
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


		return returnVal;
 } 
	public static BillingAccountTermAttr map(GenericValue val) {

BillingAccountTermAttr returnVal = new BillingAccountTermAttr();
		returnVal.setBillingAccountTermId(val.getString("billingAccountTermId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));


return returnVal;

}

public static BillingAccountTermAttr map(HttpServletRequest request) throws Exception {

		BillingAccountTermAttr returnVal = new BillingAccountTermAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("billingAccountTermId")) {
returnVal.setBillingAccountTermId(request.getParameter("billingAccountTermId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
return returnVal;

}
}
