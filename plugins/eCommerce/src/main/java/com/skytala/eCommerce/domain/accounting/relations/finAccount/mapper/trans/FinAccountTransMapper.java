package com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.trans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.trans.FinAccountTrans;

public class FinAccountTransMapper  {


	public static Map<String, Object> map(FinAccountTrans finaccounttrans) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccounttrans.getFinAccountTransId() != null ){
			returnVal.put("finAccountTransId",finaccounttrans.getFinAccountTransId());
}

		if(finaccounttrans.getFinAccountTransTypeId() != null ){
			returnVal.put("finAccountTransTypeId",finaccounttrans.getFinAccountTransTypeId());
}

		if(finaccounttrans.getFinAccountId() != null ){
			returnVal.put("finAccountId",finaccounttrans.getFinAccountId());
}

		if(finaccounttrans.getPartyId() != null ){
			returnVal.put("partyId",finaccounttrans.getPartyId());
}

		if(finaccounttrans.getGlReconciliationId() != null ){
			returnVal.put("glReconciliationId",finaccounttrans.getGlReconciliationId());
}

		if(finaccounttrans.getTransactionDate() != null ){
			returnVal.put("transactionDate",finaccounttrans.getTransactionDate());
}

		if(finaccounttrans.getEntryDate() != null ){
			returnVal.put("entryDate",finaccounttrans.getEntryDate());
}

		if(finaccounttrans.getAmount() != null ){
			returnVal.put("amount",finaccounttrans.getAmount());
}

		if(finaccounttrans.getPaymentId() != null ){
			returnVal.put("paymentId",finaccounttrans.getPaymentId());
}

		if(finaccounttrans.getOrderId() != null ){
			returnVal.put("orderId",finaccounttrans.getOrderId());
}

		if(finaccounttrans.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",finaccounttrans.getOrderItemSeqId());
}

		if(finaccounttrans.getPerformedByPartyId() != null ){
			returnVal.put("performedByPartyId",finaccounttrans.getPerformedByPartyId());
}

		if(finaccounttrans.getReasonEnumId() != null ){
			returnVal.put("reasonEnumId",finaccounttrans.getReasonEnumId());
}

		if(finaccounttrans.getComments() != null ){
			returnVal.put("comments",finaccounttrans.getComments());
}

		if(finaccounttrans.getStatusId() != null ){
			returnVal.put("statusId",finaccounttrans.getStatusId());
}

		return returnVal;
}


	public static FinAccountTrans map(Map<String, Object> fields) {

		FinAccountTrans returnVal = new FinAccountTrans();

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("finAccountTransTypeId") != null) {
			returnVal.setFinAccountTransTypeId((String) fields.get("finAccountTransTypeId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("glReconciliationId") != null) {
			returnVal.setGlReconciliationId((String) fields.get("glReconciliationId"));
}

		if(fields.get("transactionDate") != null) {
			returnVal.setTransactionDate((Timestamp) fields.get("transactionDate"));
}

		if(fields.get("entryDate") != null) {
			returnVal.setEntryDate((Timestamp) fields.get("entryDate"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("performedByPartyId") != null) {
			returnVal.setPerformedByPartyId((String) fields.get("performedByPartyId"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static FinAccountTrans mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountTrans returnVal = new FinAccountTrans();

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("finAccountTransTypeId") != null) {
			returnVal.setFinAccountTransTypeId((String) fields.get("finAccountTransTypeId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("glReconciliationId") != null) {
			returnVal.setGlReconciliationId((String) fields.get("glReconciliationId"));
}

		if(fields.get("transactionDate") != null) {
String buf = fields.get("transactionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setTransactionDate(ibuf);
}

		if(fields.get("entryDate") != null) {
String buf = fields.get("entryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEntryDate(ibuf);
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("performedByPartyId") != null) {
			returnVal.setPerformedByPartyId((String) fields.get("performedByPartyId"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static FinAccountTrans map(GenericValue val) {

FinAccountTrans returnVal = new FinAccountTrans();
		returnVal.setFinAccountTransId(val.getString("finAccountTransId"));
		returnVal.setFinAccountTransTypeId(val.getString("finAccountTransTypeId"));
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setGlReconciliationId(val.getString("glReconciliationId"));
		returnVal.setTransactionDate(val.getTimestamp("transactionDate"));
		returnVal.setEntryDate(val.getTimestamp("entryDate"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setPerformedByPartyId(val.getString("performedByPartyId"));
		returnVal.setReasonEnumId(val.getString("reasonEnumId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setStatusId(val.getString("statusId"));


return returnVal;

}

public static FinAccountTrans map(HttpServletRequest request) throws Exception {

		FinAccountTrans returnVal = new FinAccountTrans();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountTransId")) {
returnVal.setFinAccountTransId(request.getParameter("finAccountTransId"));
}

		if(paramMap.containsKey("finAccountTransTypeId"))  {
returnVal.setFinAccountTransTypeId(request.getParameter("finAccountTransTypeId"));
}
		if(paramMap.containsKey("finAccountId"))  {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("glReconciliationId"))  {
returnVal.setGlReconciliationId(request.getParameter("glReconciliationId"));
}
		if(paramMap.containsKey("transactionDate"))  {
String buf = request.getParameter("transactionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setTransactionDate(ibuf);
}
		if(paramMap.containsKey("entryDate"))  {
String buf = request.getParameter("entryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEntryDate(ibuf);
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("performedByPartyId"))  {
returnVal.setPerformedByPartyId(request.getParameter("performedByPartyId"));
}
		if(paramMap.containsKey("reasonEnumId"))  {
returnVal.setReasonEnumId(request.getParameter("reasonEnumId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
return returnVal;

}
}
