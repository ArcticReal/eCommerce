package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayConfigType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfigType.PaymentGatewayConfigType;

public class PaymentGatewayConfigTypeMapper  {


	public static Map<String, Object> map(PaymentGatewayConfigType paymentgatewayconfigtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayconfigtype.getPaymentGatewayConfigTypeId() != null ){
			returnVal.put("paymentGatewayConfigTypeId",paymentgatewayconfigtype.getPaymentGatewayConfigTypeId());
}

		if(paymentgatewayconfigtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",paymentgatewayconfigtype.getParentTypeId());
}

		if(paymentgatewayconfigtype.getHasTable() != null ){
			returnVal.put("hasTable",paymentgatewayconfigtype.getHasTable());
}

		if(paymentgatewayconfigtype.getDescription() != null ){
			returnVal.put("description",paymentgatewayconfigtype.getDescription());
}

		return returnVal;
}


	public static PaymentGatewayConfigType map(Map<String, Object> fields) {

		PaymentGatewayConfigType returnVal = new PaymentGatewayConfigType();

		if(fields.get("paymentGatewayConfigTypeId") != null) {
			returnVal.setPaymentGatewayConfigTypeId((String) fields.get("paymentGatewayConfigTypeId"));
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
	public static PaymentGatewayConfigType mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayConfigType returnVal = new PaymentGatewayConfigType();

		if(fields.get("paymentGatewayConfigTypeId") != null) {
			returnVal.setPaymentGatewayConfigTypeId((String) fields.get("paymentGatewayConfigTypeId"));
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
	public static PaymentGatewayConfigType map(GenericValue val) {

PaymentGatewayConfigType returnVal = new PaymentGatewayConfigType();
		returnVal.setPaymentGatewayConfigTypeId(val.getString("paymentGatewayConfigTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PaymentGatewayConfigType map(HttpServletRequest request) throws Exception {

		PaymentGatewayConfigType returnVal = new PaymentGatewayConfigType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigTypeId")) {
returnVal.setPaymentGatewayConfigTypeId(request.getParameter("paymentGatewayConfigTypeId"));
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
