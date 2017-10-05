package com.skytala.eCommerce.domain.paymentMethod.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.paymentMethod.model.PaymentMethod;

public class PaymentMethodMapper  {


	public static Map<String, Object> map(PaymentMethod paymentmethod) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentmethod.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",paymentmethod.getPaymentMethodId());
}

		if(paymentmethod.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",paymentmethod.getPaymentMethodTypeId());
}

		if(paymentmethod.getPartyId() != null ){
			returnVal.put("partyId",paymentmethod.getPartyId());
}

		if(paymentmethod.getGlAccountId() != null ){
			returnVal.put("glAccountId",paymentmethod.getGlAccountId());
}

		if(paymentmethod.getFinAccountId() != null ){
			returnVal.put("finAccountId",paymentmethod.getFinAccountId());
}

		if(paymentmethod.getDescription() != null ){
			returnVal.put("description",paymentmethod.getDescription());
}

		if(paymentmethod.getFromDate() != null ){
			returnVal.put("fromDate",paymentmethod.getFromDate());
}

		if(paymentmethod.getThruDate() != null ){
			returnVal.put("thruDate",paymentmethod.getThruDate());
}

		return returnVal;
}


	public static PaymentMethod map(Map<String, Object> fields) {

		PaymentMethod returnVal = new PaymentMethod();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static PaymentMethod mapstrstr(Map<String, String> fields) throws Exception {

		PaymentMethod returnVal = new PaymentMethod();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static PaymentMethod map(GenericValue val) {

PaymentMethod returnVal = new PaymentMethod();
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PaymentMethod map(HttpServletRequest request) throws Exception {

		PaymentMethod returnVal = new PaymentMethod();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentMethodId")) {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}

		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
		if(paramMap.containsKey("finAccountId"))  {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
