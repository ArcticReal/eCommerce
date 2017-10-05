package com.skytala.eCommerce.domain.paymentApplication.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.paymentApplication.model.PaymentApplication;

public class PaymentApplicationMapper  {


	public static Map<String, Object> map(PaymentApplication paymentapplication) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentapplication.getPaymentApplicationId() != null ){
			returnVal.put("paymentApplicationId",paymentapplication.getPaymentApplicationId());
}

		if(paymentapplication.getPaymentId() != null ){
			returnVal.put("paymentId",paymentapplication.getPaymentId());
}

		if(paymentapplication.getInvoiceId() != null ){
			returnVal.put("invoiceId",paymentapplication.getInvoiceId());
}

		if(paymentapplication.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",paymentapplication.getInvoiceItemSeqId());
}

		if(paymentapplication.getBillingAccountId() != null ){
			returnVal.put("billingAccountId",paymentapplication.getBillingAccountId());
}

		if(paymentapplication.getOverrideGlAccountId() != null ){
			returnVal.put("overrideGlAccountId",paymentapplication.getOverrideGlAccountId());
}

		if(paymentapplication.getToPaymentId() != null ){
			returnVal.put("toPaymentId",paymentapplication.getToPaymentId());
}

		if(paymentapplication.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",paymentapplication.getTaxAuthGeoId());
}

		if(paymentapplication.getAmountApplied() != null ){
			returnVal.put("amountApplied",paymentapplication.getAmountApplied());
}

		return returnVal;
}


	public static PaymentApplication map(Map<String, Object> fields) {

		PaymentApplication returnVal = new PaymentApplication();

		if(fields.get("paymentApplicationId") != null) {
			returnVal.setPaymentApplicationId((String) fields.get("paymentApplicationId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("toPaymentId") != null) {
			returnVal.setToPaymentId((String) fields.get("toPaymentId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("amountApplied") != null) {
			returnVal.setAmountApplied((BigDecimal) fields.get("amountApplied"));
}


		return returnVal;
 } 
	public static PaymentApplication mapstrstr(Map<String, String> fields) throws Exception {

		PaymentApplication returnVal = new PaymentApplication();

		if(fields.get("paymentApplicationId") != null) {
			returnVal.setPaymentApplicationId((String) fields.get("paymentApplicationId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("toPaymentId") != null) {
			returnVal.setToPaymentId((String) fields.get("toPaymentId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("amountApplied") != null) {
String buf;
buf = fields.get("amountApplied");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountApplied(bd);
}


		return returnVal;
 } 
	public static PaymentApplication map(GenericValue val) {

PaymentApplication returnVal = new PaymentApplication();
		returnVal.setPaymentApplicationId(val.getString("paymentApplicationId"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setOverrideGlAccountId(val.getString("overrideGlAccountId"));
		returnVal.setToPaymentId(val.getString("toPaymentId"));
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setAmountApplied(val.getBigDecimal("amountApplied"));


return returnVal;

}

public static PaymentApplication map(HttpServletRequest request) throws Exception {

		PaymentApplication returnVal = new PaymentApplication();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentApplicationId")) {
returnVal.setPaymentApplicationId(request.getParameter("paymentApplicationId"));
}

		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
}
		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
		if(paramMap.containsKey("billingAccountId"))  {
returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
}
		if(paramMap.containsKey("overrideGlAccountId"))  {
returnVal.setOverrideGlAccountId(request.getParameter("overrideGlAccountId"));
}
		if(paramMap.containsKey("toPaymentId"))  {
returnVal.setToPaymentId(request.getParameter("toPaymentId"));
}
		if(paramMap.containsKey("taxAuthGeoId"))  {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}
		if(paramMap.containsKey("amountApplied"))  {
String buf = request.getParameter("amountApplied");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountApplied(bd);
}
return returnVal;

}
}
