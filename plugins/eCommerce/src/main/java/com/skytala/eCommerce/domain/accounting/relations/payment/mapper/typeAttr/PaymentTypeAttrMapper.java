package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.typeAttr.PaymentTypeAttr;

public class PaymentTypeAttrMapper  {


	public static Map<String, Object> map(PaymentTypeAttr paymenttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymenttypeattr.getPaymentTypeId() != null ){
			returnVal.put("paymentTypeId",paymenttypeattr.getPaymentTypeId());
}

		if(paymenttypeattr.getAttrName() != null ){
			returnVal.put("attrName",paymenttypeattr.getAttrName());
}

		if(paymenttypeattr.getDescription() != null ){
			returnVal.put("description",paymenttypeattr.getDescription());
}

		return returnVal;
}


	public static PaymentTypeAttr map(Map<String, Object> fields) {

		PaymentTypeAttr returnVal = new PaymentTypeAttr();

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PaymentTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		PaymentTypeAttr returnVal = new PaymentTypeAttr();

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PaymentTypeAttr map(GenericValue val) {

PaymentTypeAttr returnVal = new PaymentTypeAttr();
		returnVal.setPaymentTypeId(val.getString("paymentTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PaymentTypeAttr map(HttpServletRequest request) throws Exception {

		PaymentTypeAttr returnVal = new PaymentTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentTypeId")) {
returnVal.setPaymentTypeId(request.getParameter("paymentTypeId"));
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
