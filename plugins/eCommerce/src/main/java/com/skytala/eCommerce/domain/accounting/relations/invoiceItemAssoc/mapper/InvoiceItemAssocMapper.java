package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.model.InvoiceItemAssoc;

public class InvoiceItemAssocMapper  {


	public static Map<String, Object> map(InvoiceItemAssoc invoiceitemassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitemassoc.getInvoiceIdFrom() != null ){
			returnVal.put("invoiceIdFrom",invoiceitemassoc.getInvoiceIdFrom());
}

		if(invoiceitemassoc.getInvoiceItemSeqIdFrom() != null ){
			returnVal.put("invoiceItemSeqIdFrom",invoiceitemassoc.getInvoiceItemSeqIdFrom());
}

		if(invoiceitemassoc.getInvoiceIdTo() != null ){
			returnVal.put("invoiceIdTo",invoiceitemassoc.getInvoiceIdTo());
}

		if(invoiceitemassoc.getInvoiceItemSeqIdTo() != null ){
			returnVal.put("invoiceItemSeqIdTo",invoiceitemassoc.getInvoiceItemSeqIdTo());
}

		if(invoiceitemassoc.getInvoiceItemAssocTypeId() != null ){
			returnVal.put("invoiceItemAssocTypeId",invoiceitemassoc.getInvoiceItemAssocTypeId());
}

		if(invoiceitemassoc.getFromDate() != null ){
			returnVal.put("fromDate",invoiceitemassoc.getFromDate());
}

		if(invoiceitemassoc.getThruDate() != null ){
			returnVal.put("thruDate",invoiceitemassoc.getThruDate());
}

		if(invoiceitemassoc.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",invoiceitemassoc.getPartyIdFrom());
}

		if(invoiceitemassoc.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",invoiceitemassoc.getPartyIdTo());
}

		if(invoiceitemassoc.getQuantity() != null ){
			returnVal.put("quantity",invoiceitemassoc.getQuantity());
}

		if(invoiceitemassoc.getAmount() != null ){
			returnVal.put("amount",invoiceitemassoc.getAmount());
}

		return returnVal;
}


	public static InvoiceItemAssoc map(Map<String, Object> fields) {

		InvoiceItemAssoc returnVal = new InvoiceItemAssoc();

		if(fields.get("invoiceIdFrom") != null) {
			returnVal.setInvoiceIdFrom((String) fields.get("invoiceIdFrom"));
}

		if(fields.get("invoiceItemSeqIdFrom") != null) {
			returnVal.setInvoiceItemSeqIdFrom((String) fields.get("invoiceItemSeqIdFrom"));
}

		if(fields.get("invoiceIdTo") != null) {
			returnVal.setInvoiceIdTo((String) fields.get("invoiceIdTo"));
}

		if(fields.get("invoiceItemSeqIdTo") != null) {
			returnVal.setInvoiceItemSeqIdTo((String) fields.get("invoiceItemSeqIdTo"));
}

		if(fields.get("invoiceItemAssocTypeId") != null) {
			returnVal.setInvoiceItemAssocTypeId((String) fields.get("invoiceItemAssocTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}


		return returnVal;
 } 
	public static InvoiceItemAssoc mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItemAssoc returnVal = new InvoiceItemAssoc();

		if(fields.get("invoiceIdFrom") != null) {
			returnVal.setInvoiceIdFrom((String) fields.get("invoiceIdFrom"));
}

		if(fields.get("invoiceItemSeqIdFrom") != null) {
			returnVal.setInvoiceItemSeqIdFrom((String) fields.get("invoiceItemSeqIdFrom"));
}

		if(fields.get("invoiceIdTo") != null) {
			returnVal.setInvoiceIdTo((String) fields.get("invoiceIdTo"));
}

		if(fields.get("invoiceItemSeqIdTo") != null) {
			returnVal.setInvoiceItemSeqIdTo((String) fields.get("invoiceItemSeqIdTo"));
}

		if(fields.get("invoiceItemAssocTypeId") != null) {
			returnVal.setInvoiceItemAssocTypeId((String) fields.get("invoiceItemAssocTypeId"));
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

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
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
	public static InvoiceItemAssoc map(GenericValue val) {

InvoiceItemAssoc returnVal = new InvoiceItemAssoc();
		returnVal.setInvoiceIdFrom(val.getString("invoiceIdFrom"));
		returnVal.setInvoiceItemSeqIdFrom(val.getString("invoiceItemSeqIdFrom"));
		returnVal.setInvoiceIdTo(val.getString("invoiceIdTo"));
		returnVal.setInvoiceItemSeqIdTo(val.getString("invoiceItemSeqIdTo"));
		returnVal.setInvoiceItemAssocTypeId(val.getString("invoiceItemAssocTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static InvoiceItemAssoc map(HttpServletRequest request) throws Exception {

		InvoiceItemAssoc returnVal = new InvoiceItemAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceIdFrom")) {
returnVal.setInvoiceIdFrom(request.getParameter("invoiceIdFrom"));
}

		if(paramMap.containsKey("invoiceItemSeqIdFrom"))  {
returnVal.setInvoiceItemSeqIdFrom(request.getParameter("invoiceItemSeqIdFrom"));
}
		if(paramMap.containsKey("invoiceIdTo"))  {
returnVal.setInvoiceIdTo(request.getParameter("invoiceIdTo"));
}
		if(paramMap.containsKey("invoiceItemSeqIdTo"))  {
returnVal.setInvoiceItemSeqIdTo(request.getParameter("invoiceItemSeqIdTo"));
}
		if(paramMap.containsKey("invoiceItemAssocTypeId"))  {
returnVal.setInvoiceItemAssocTypeId(request.getParameter("invoiceItemAssocTypeId"));
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
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
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
