package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayRespMsg;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayRespMsg.PaymentGatewayRespMsg;

public class PaymentGatewayRespMsgMapper  {


	public static Map<String, Object> map(PaymentGatewayRespMsg paymentgatewayrespmsg) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayrespmsg.getPaymentGatewayRespMsgId() != null ){
			returnVal.put("paymentGatewayRespMsgId",paymentgatewayrespmsg.getPaymentGatewayRespMsgId());
}

		if(paymentgatewayrespmsg.getPaymentGatewayResponseId() != null ){
			returnVal.put("paymentGatewayResponseId",paymentgatewayrespmsg.getPaymentGatewayResponseId());
}

		if(paymentgatewayrespmsg.getPgrMessage() != null ){
			returnVal.put("pgrMessage",paymentgatewayrespmsg.getPgrMessage());
}

		return returnVal;
}


	public static PaymentGatewayRespMsg map(Map<String, Object> fields) {

		PaymentGatewayRespMsg returnVal = new PaymentGatewayRespMsg();

		if(fields.get("paymentGatewayRespMsgId") != null) {
			returnVal.setPaymentGatewayRespMsgId((String) fields.get("paymentGatewayRespMsgId"));
}

		if(fields.get("paymentGatewayResponseId") != null) {
			returnVal.setPaymentGatewayResponseId((String) fields.get("paymentGatewayResponseId"));
}

		if(fields.get("pgrMessage") != null) {
			returnVal.setPgrMessage((String) fields.get("pgrMessage"));
}


		return returnVal;
 } 
	public static PaymentGatewayRespMsg mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayRespMsg returnVal = new PaymentGatewayRespMsg();

		if(fields.get("paymentGatewayRespMsgId") != null) {
			returnVal.setPaymentGatewayRespMsgId((String) fields.get("paymentGatewayRespMsgId"));
}

		if(fields.get("paymentGatewayResponseId") != null) {
			returnVal.setPaymentGatewayResponseId((String) fields.get("paymentGatewayResponseId"));
}

		if(fields.get("pgrMessage") != null) {
			returnVal.setPgrMessage((String) fields.get("pgrMessage"));
}


		return returnVal;
 } 
	public static PaymentGatewayRespMsg map(GenericValue val) {

PaymentGatewayRespMsg returnVal = new PaymentGatewayRespMsg();
		returnVal.setPaymentGatewayRespMsgId(val.getString("paymentGatewayRespMsgId"));
		returnVal.setPaymentGatewayResponseId(val.getString("paymentGatewayResponseId"));
		returnVal.setPgrMessage(val.getString("pgrMessage"));


return returnVal;

}

public static PaymentGatewayRespMsg map(HttpServletRequest request) throws Exception {

		PaymentGatewayRespMsg returnVal = new PaymentGatewayRespMsg();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayRespMsgId")) {
returnVal.setPaymentGatewayRespMsgId(request.getParameter("paymentGatewayRespMsgId"));
}

		if(paramMap.containsKey("paymentGatewayResponseId"))  {
returnVal.setPaymentGatewayResponseId(request.getParameter("paymentGatewayResponseId"));
}
		if(paramMap.containsKey("pgrMessage"))  {
returnVal.setPgrMessage(request.getParameter("pgrMessage"));
}
return returnVal;

}
}
