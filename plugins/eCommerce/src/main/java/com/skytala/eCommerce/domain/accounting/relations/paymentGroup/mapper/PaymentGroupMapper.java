package com.skytala.eCommerce.domain.accounting.relations.paymentGroup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGroup.model.PaymentGroup;

public class PaymentGroupMapper  {


	public static Map<String, Object> map(PaymentGroup paymentgroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgroup.getPaymentGroupId() != null ){
			returnVal.put("paymentGroupId",paymentgroup.getPaymentGroupId());
}

		if(paymentgroup.getPaymentGroupTypeId() != null ){
			returnVal.put("paymentGroupTypeId",paymentgroup.getPaymentGroupTypeId());
}

		if(paymentgroup.getPaymentGroupName() != null ){
			returnVal.put("paymentGroupName",paymentgroup.getPaymentGroupName());
}

		return returnVal;
}


	public static PaymentGroup map(Map<String, Object> fields) {

		PaymentGroup returnVal = new PaymentGroup();

		if(fields.get("paymentGroupId") != null) {
			returnVal.setPaymentGroupId((String) fields.get("paymentGroupId"));
}

		if(fields.get("paymentGroupTypeId") != null) {
			returnVal.setPaymentGroupTypeId((String) fields.get("paymentGroupTypeId"));
}

		if(fields.get("paymentGroupName") != null) {
			returnVal.setPaymentGroupName((String) fields.get("paymentGroupName"));
}


		return returnVal;
 } 
	public static PaymentGroup mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGroup returnVal = new PaymentGroup();

		if(fields.get("paymentGroupId") != null) {
			returnVal.setPaymentGroupId((String) fields.get("paymentGroupId"));
}

		if(fields.get("paymentGroupTypeId") != null) {
			returnVal.setPaymentGroupTypeId((String) fields.get("paymentGroupTypeId"));
}

		if(fields.get("paymentGroupName") != null) {
			returnVal.setPaymentGroupName((String) fields.get("paymentGroupName"));
}


		return returnVal;
 } 
	public static PaymentGroup map(GenericValue val) {

PaymentGroup returnVal = new PaymentGroup();
		returnVal.setPaymentGroupId(val.getString("paymentGroupId"));
		returnVal.setPaymentGroupTypeId(val.getString("paymentGroupTypeId"));
		returnVal.setPaymentGroupName(val.getString("paymentGroupName"));


return returnVal;

}

public static PaymentGroup map(HttpServletRequest request) throws Exception {

		PaymentGroup returnVal = new PaymentGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGroupId")) {
returnVal.setPaymentGroupId(request.getParameter("paymentGroupId"));
}

		if(paramMap.containsKey("paymentGroupTypeId"))  {
returnVal.setPaymentGroupTypeId(request.getParameter("paymentGroupTypeId"));
}
		if(paramMap.containsKey("paymentGroupName"))  {
returnVal.setPaymentGroupName(request.getParameter("paymentGroupName"));
}
return returnVal;

}
}
