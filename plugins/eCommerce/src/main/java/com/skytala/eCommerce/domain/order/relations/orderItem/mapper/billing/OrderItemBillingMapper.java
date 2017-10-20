package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.billing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.billing.OrderItemBilling;

public class OrderItemBillingMapper  {


	public static Map<String, Object> map(OrderItemBilling orderitembilling) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitembilling.getOrderId() != null ){
			returnVal.put("orderId",orderitembilling.getOrderId());
}

		if(orderitembilling.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitembilling.getOrderItemSeqId());
}

		if(orderitembilling.getInvoiceId() != null ){
			returnVal.put("invoiceId",orderitembilling.getInvoiceId());
}

		if(orderitembilling.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",orderitembilling.getInvoiceItemSeqId());
}

		if(orderitembilling.getItemIssuanceId() != null ){
			returnVal.put("itemIssuanceId",orderitembilling.getItemIssuanceId());
}

		if(orderitembilling.getShipmentReceiptId() != null ){
			returnVal.put("shipmentReceiptId",orderitembilling.getShipmentReceiptId());
}

		if(orderitembilling.getQuantity() != null ){
			returnVal.put("quantity",orderitembilling.getQuantity());
}

		if(orderitembilling.getAmount() != null ){
			returnVal.put("amount",orderitembilling.getAmount());
}

		return returnVal;
}


	public static OrderItemBilling map(Map<String, Object> fields) {

		OrderItemBilling returnVal = new OrderItemBilling();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("shipmentReceiptId") != null) {
			returnVal.setShipmentReceiptId((String) fields.get("shipmentReceiptId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}


		return returnVal;
 } 
	public static OrderItemBilling mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemBilling returnVal = new OrderItemBilling();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("shipmentReceiptId") != null) {
			returnVal.setShipmentReceiptId((String) fields.get("shipmentReceiptId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
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
	public static OrderItemBilling map(GenericValue val) {

OrderItemBilling returnVal = new OrderItemBilling();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setItemIssuanceId(val.getString("itemIssuanceId"));
		returnVal.setShipmentReceiptId(val.getString("shipmentReceiptId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static OrderItemBilling map(HttpServletRequest request) throws Exception {

		OrderItemBilling returnVal = new OrderItemBilling();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
		if(paramMap.containsKey("itemIssuanceId"))  {
returnVal.setItemIssuanceId(request.getParameter("itemIssuanceId"));
}
		if(paramMap.containsKey("shipmentReceiptId"))  {
returnVal.setShipmentReceiptId(request.getParameter("shipmentReceiptId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
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
