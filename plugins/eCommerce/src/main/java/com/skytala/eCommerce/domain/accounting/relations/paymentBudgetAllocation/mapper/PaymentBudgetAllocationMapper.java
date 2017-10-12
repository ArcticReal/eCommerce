package com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.model.PaymentBudgetAllocation;

public class PaymentBudgetAllocationMapper  {


	public static Map<String, Object> map(PaymentBudgetAllocation paymentbudgetallocation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentbudgetallocation.getBudgetId() != null ){
			returnVal.put("budgetId",paymentbudgetallocation.getBudgetId());
}

		if(paymentbudgetallocation.getBudgetItemSeqId() != null ){
			returnVal.put("budgetItemSeqId",paymentbudgetallocation.getBudgetItemSeqId());
}

		if(paymentbudgetallocation.getPaymentId() != null ){
			returnVal.put("paymentId",paymentbudgetallocation.getPaymentId());
}

		if(paymentbudgetallocation.getAmount() != null ){
			returnVal.put("amount",paymentbudgetallocation.getAmount());
}

		return returnVal;
}


	public static PaymentBudgetAllocation map(Map<String, Object> fields) {

		PaymentBudgetAllocation returnVal = new PaymentBudgetAllocation();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}


		return returnVal;
 } 
	public static PaymentBudgetAllocation mapstrstr(Map<String, String> fields) throws Exception {

		PaymentBudgetAllocation returnVal = new PaymentBudgetAllocation();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}


		return returnVal;
 } 
	public static PaymentBudgetAllocation map(GenericValue val) {

PaymentBudgetAllocation returnVal = new PaymentBudgetAllocation();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static PaymentBudgetAllocation map(HttpServletRequest request) throws Exception {

		PaymentBudgetAllocation returnVal = new PaymentBudgetAllocation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("budgetItemSeqId"))  {
returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
}
		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
return returnVal;

}
}
