package com.skytala.eCommerce.domain.humanres.relations.payHistory.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;

public class PayHistoryMapper  {


	public static Map<String, Object> map(PayHistory payhistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(payhistory.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",payhistory.getRoleTypeIdFrom());
}

		if(payhistory.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",payhistory.getRoleTypeIdTo());
}

		if(payhistory.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",payhistory.getPartyIdFrom());
}

		if(payhistory.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",payhistory.getPartyIdTo());
}

		if(payhistory.getFromDate() != null ){
			returnVal.put("fromDate",payhistory.getFromDate());
}

		if(payhistory.getThruDate() != null ){
			returnVal.put("thruDate",payhistory.getThruDate());
}

		if(payhistory.getSalaryStepSeqId() != null ){
			returnVal.put("salaryStepSeqId",payhistory.getSalaryStepSeqId());
}

		if(payhistory.getPayGradeId() != null ){
			returnVal.put("payGradeId",payhistory.getPayGradeId());
}

		if(payhistory.getPeriodTypeId() != null ){
			returnVal.put("periodTypeId",payhistory.getPeriodTypeId());
}

		if(payhistory.getAmount() != null ){
			returnVal.put("amount",payhistory.getAmount());
}

		if(payhistory.getComments() != null ){
			returnVal.put("comments",payhistory.getComments());
}

		return returnVal;
}


	public static PayHistory map(Map<String, Object> fields) {

		PayHistory returnVal = new PayHistory();

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("salaryStepSeqId") != null) {
			returnVal.setSalaryStepSeqId((String) fields.get("salaryStepSeqId"));
}

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PayHistory mapstrstr(Map<String, String> fields) throws Exception {

		PayHistory returnVal = new PayHistory();

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
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

		if(fields.get("salaryStepSeqId") != null) {
			returnVal.setSalaryStepSeqId((String) fields.get("salaryStepSeqId"));
}

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PayHistory map(GenericValue val) {

PayHistory returnVal = new PayHistory();
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSalaryStepSeqId(val.getString("salaryStepSeqId"));
		returnVal.setPayGradeId(val.getString("payGradeId"));
		returnVal.setPeriodTypeId(val.getString("periodTypeId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static PayHistory map(HttpServletRequest request) throws Exception {

		PayHistory returnVal = new PayHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("roleTypeIdFrom")) {
returnVal.setRoleTypeIdFrom(request.getParameter("roleTypeIdFrom"));
}

		if(paramMap.containsKey("roleTypeIdTo"))  {
returnVal.setRoleTypeIdTo(request.getParameter("roleTypeIdTo"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
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
		if(paramMap.containsKey("salaryStepSeqId"))  {
returnVal.setSalaryStepSeqId(request.getParameter("salaryStepSeqId"));
}
		if(paramMap.containsKey("payGradeId"))  {
returnVal.setPayGradeId(request.getParameter("payGradeId"));
}
		if(paramMap.containsKey("periodTypeId"))  {
returnVal.setPeriodTypeId(request.getParameter("periodTypeId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
