package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute.PaymentAttribute;

public class PaymentAttributeMapper  {


	public static Map<String, Object> map(PaymentAttribute paymentattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentattribute.getPaymentId() != null ){
			returnVal.put("paymentId",paymentattribute.getPaymentId());
}

		if(paymentattribute.getAttrName() != null ){
			returnVal.put("attrName",paymentattribute.getAttrName());
}

		if(paymentattribute.getAttrValue() != null ){
			returnVal.put("attrValue",paymentattribute.getAttrValue());
}

		if(paymentattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",paymentattribute.getAttrDescription());
}

		return returnVal;
}


	public static PaymentAttribute map(Map<String, Object> fields) {

		PaymentAttribute returnVal = new PaymentAttribute();

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
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
	public static PaymentAttribute mapstrstr(Map<String, String> fields) throws Exception {

		PaymentAttribute returnVal = new PaymentAttribute();

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
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
	public static PaymentAttribute map(GenericValue val) {

PaymentAttribute returnVal = new PaymentAttribute();
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static PaymentAttribute map(HttpServletRequest request) throws Exception {

		PaymentAttribute returnVal = new PaymentAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentId")) {
returnVal.setPaymentId(request.getParameter("paymentId"));
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
