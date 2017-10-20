package com.skytala.eCommerce.domain.order.relations.returnItem.mapper.billing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.billing.ReturnItemBilling;

public class ReturnItemBillingMapper  {


	public static Map<String, Object> map(ReturnItemBilling returnitembilling) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnitembilling.getReturnId() != null ){
			returnVal.put("returnId",returnitembilling.getReturnId());
}

		if(returnitembilling.getReturnItemSeqId() != null ){
			returnVal.put("returnItemSeqId",returnitembilling.getReturnItemSeqId());
}

		if(returnitembilling.getInvoiceId() != null ){
			returnVal.put("invoiceId",returnitembilling.getInvoiceId());
}

		if(returnitembilling.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",returnitembilling.getInvoiceItemSeqId());
}

		if(returnitembilling.getShipmentReceiptId() != null ){
			returnVal.put("shipmentReceiptId",returnitembilling.getShipmentReceiptId());
}

		if(returnitembilling.getQuantity() != null ){
			returnVal.put("quantity",returnitembilling.getQuantity());
}

		if(returnitembilling.getAmount() != null ){
			returnVal.put("amount",returnitembilling.getAmount());
}

		return returnVal;
}


	public static ReturnItemBilling map(Map<String, Object> fields) {

		ReturnItemBilling returnVal = new ReturnItemBilling();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
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
	public static ReturnItemBilling mapstrstr(Map<String, String> fields) throws Exception {

		ReturnItemBilling returnVal = new ReturnItemBilling();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
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
	public static ReturnItemBilling map(GenericValue val) {

ReturnItemBilling returnVal = new ReturnItemBilling();
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnItemSeqId(val.getString("returnItemSeqId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setShipmentReceiptId(val.getString("shipmentReceiptId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static ReturnItemBilling map(HttpServletRequest request) throws Exception {

		ReturnItemBilling returnVal = new ReturnItemBilling();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnId")) {
returnVal.setReturnId(request.getParameter("returnId"));
}

		if(paramMap.containsKey("returnItemSeqId"))  {
returnVal.setReturnItemSeqId(request.getParameter("returnItemSeqId"));
}
		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
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
