package com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.billing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.billing.OrderAdjustmentBilling;

public class OrderAdjustmentBillingMapper  {


	public static Map<String, Object> map(OrderAdjustmentBilling orderadjustmentbilling) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderadjustmentbilling.getOrderAdjustmentId() != null ){
			returnVal.put("orderAdjustmentId",orderadjustmentbilling.getOrderAdjustmentId());
}

		if(orderadjustmentbilling.getInvoiceId() != null ){
			returnVal.put("invoiceId",orderadjustmentbilling.getInvoiceId());
}

		if(orderadjustmentbilling.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",orderadjustmentbilling.getInvoiceItemSeqId());
}

		if(orderadjustmentbilling.getAmount() != null ){
			returnVal.put("amount",orderadjustmentbilling.getAmount());
}

		return returnVal;
}


	public static OrderAdjustmentBilling map(Map<String, Object> fields) {

		OrderAdjustmentBilling returnVal = new OrderAdjustmentBilling();

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}


		return returnVal;
 } 
	public static OrderAdjustmentBilling mapstrstr(Map<String, String> fields) throws Exception {

		OrderAdjustmentBilling returnVal = new OrderAdjustmentBilling();

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
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
	public static OrderAdjustmentBilling map(GenericValue val) {

OrderAdjustmentBilling returnVal = new OrderAdjustmentBilling();
		returnVal.setOrderAdjustmentId(val.getString("orderAdjustmentId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static OrderAdjustmentBilling map(HttpServletRequest request) throws Exception {

		OrderAdjustmentBilling returnVal = new OrderAdjustmentBilling();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderAdjustmentId")) {
returnVal.setOrderAdjustmentId(request.getParameter("orderAdjustmentId"));
}

		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
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
