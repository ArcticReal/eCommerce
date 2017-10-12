package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;

public class PayrollPreferenceMapper  {


	public static Map<String, Object> map(PayrollPreference payrollpreference) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(payrollpreference.getPartyId() != null ){
			returnVal.put("partyId",payrollpreference.getPartyId());
}

		if(payrollpreference.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",payrollpreference.getRoleTypeId());
}

		if(payrollpreference.getPayrollPreferenceSeqId() != null ){
			returnVal.put("payrollPreferenceSeqId",payrollpreference.getPayrollPreferenceSeqId());
}

		if(payrollpreference.getDeductionTypeId() != null ){
			returnVal.put("deductionTypeId",payrollpreference.getDeductionTypeId());
}

		if(payrollpreference.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",payrollpreference.getPaymentMethodTypeId());
}

		if(payrollpreference.getPeriodTypeId() != null ){
			returnVal.put("periodTypeId",payrollpreference.getPeriodTypeId());
}

		if(payrollpreference.getFromDate() != null ){
			returnVal.put("fromDate",payrollpreference.getFromDate());
}

		if(payrollpreference.getThruDate() != null ){
			returnVal.put("thruDate",payrollpreference.getThruDate());
}

		if(payrollpreference.getPercentage() != null ){
			returnVal.put("percentage",payrollpreference.getPercentage());
}

		if(payrollpreference.getFlatAmount() != null ){
			returnVal.put("flatAmount",payrollpreference.getFlatAmount());
}

		if(payrollpreference.getRoutingNumber() != null ){
			returnVal.put("routingNumber",payrollpreference.getRoutingNumber());
}

		if(payrollpreference.getAccountNumber() != null ){
			returnVal.put("accountNumber",payrollpreference.getAccountNumber());
}

		if(payrollpreference.getBankName() != null ){
			returnVal.put("bankName",payrollpreference.getBankName());
}

		return returnVal;
}


	public static PayrollPreference map(Map<String, Object> fields) {

		PayrollPreference returnVal = new PayrollPreference();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("payrollPreferenceSeqId") != null) {
			returnVal.setPayrollPreferenceSeqId((String) fields.get("payrollPreferenceSeqId"));
}

		if(fields.get("deductionTypeId") != null) {
			returnVal.setDeductionTypeId((String) fields.get("deductionTypeId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("percentage") != null) {
			returnVal.setPercentage((BigDecimal) fields.get("percentage"));
}

		if(fields.get("flatAmount") != null) {
			returnVal.setFlatAmount((BigDecimal) fields.get("flatAmount"));
}

		if(fields.get("routingNumber") != null) {
			returnVal.setRoutingNumber((String) fields.get("routingNumber"));
}

		if(fields.get("accountNumber") != null) {
			returnVal.setAccountNumber((String) fields.get("accountNumber"));
}

		if(fields.get("bankName") != null) {
			returnVal.setBankName((String) fields.get("bankName"));
}


		return returnVal;
 } 
	public static PayrollPreference mapstrstr(Map<String, String> fields) throws Exception {

		PayrollPreference returnVal = new PayrollPreference();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("payrollPreferenceSeqId") != null) {
			returnVal.setPayrollPreferenceSeqId((String) fields.get("payrollPreferenceSeqId"));
}

		if(fields.get("deductionTypeId") != null) {
			returnVal.setDeductionTypeId((String) fields.get("deductionTypeId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
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

		if(fields.get("percentage") != null) {
String buf;
buf = fields.get("percentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentage(bd);
}

		if(fields.get("flatAmount") != null) {
String buf;
buf = fields.get("flatAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFlatAmount(bd);
}

		if(fields.get("routingNumber") != null) {
			returnVal.setRoutingNumber((String) fields.get("routingNumber"));
}

		if(fields.get("accountNumber") != null) {
			returnVal.setAccountNumber((String) fields.get("accountNumber"));
}

		if(fields.get("bankName") != null) {
			returnVal.setBankName((String) fields.get("bankName"));
}


		return returnVal;
 } 
	public static PayrollPreference map(GenericValue val) {

PayrollPreference returnVal = new PayrollPreference();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setPayrollPreferenceSeqId(val.getString("payrollPreferenceSeqId"));
		returnVal.setDeductionTypeId(val.getString("deductionTypeId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setPeriodTypeId(val.getString("periodTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPercentage(val.getBigDecimal("percentage"));
		returnVal.setFlatAmount(val.getBigDecimal("flatAmount"));
		returnVal.setRoutingNumber(val.getString("routingNumber"));
		returnVal.setAccountNumber(val.getString("accountNumber"));
		returnVal.setBankName(val.getString("bankName"));


return returnVal;

}

public static PayrollPreference map(HttpServletRequest request) throws Exception {

		PayrollPreference returnVal = new PayrollPreference();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("payrollPreferenceSeqId"))  {
returnVal.setPayrollPreferenceSeqId(request.getParameter("payrollPreferenceSeqId"));
}
		if(paramMap.containsKey("deductionTypeId"))  {
returnVal.setDeductionTypeId(request.getParameter("deductionTypeId"));
}
		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("periodTypeId"))  {
returnVal.setPeriodTypeId(request.getParameter("periodTypeId"));
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
		if(paramMap.containsKey("percentage"))  {
String buf = request.getParameter("percentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentage(bd);
}
		if(paramMap.containsKey("flatAmount"))  {
String buf = request.getParameter("flatAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFlatAmount(bd);
}
		if(paramMap.containsKey("routingNumber"))  {
returnVal.setRoutingNumber(request.getParameter("routingNumber"));
}
		if(paramMap.containsKey("accountNumber"))  {
returnVal.setAccountNumber(request.getParameter("accountNumber"));
}
		if(paramMap.containsKey("bankName"))  {
returnVal.setBankName(request.getParameter("bankName"));
}
return returnVal;

}
}
