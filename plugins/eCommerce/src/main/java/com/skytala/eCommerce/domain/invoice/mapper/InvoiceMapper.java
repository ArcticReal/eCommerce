package com.skytala.eCommerce.domain.invoice.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.invoice.model.Invoice;

public class InvoiceMapper  {


	public static Map<String, Object> map(Invoice invoice) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoice.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoice.getInvoiceId());
}

		if(invoice.getInvoiceTypeId() != null ){
			returnVal.put("invoiceTypeId",invoice.getInvoiceTypeId());
}

		if(invoice.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",invoice.getPartyIdFrom());
}

		if(invoice.getPartyId() != null ){
			returnVal.put("partyId",invoice.getPartyId());
}

		if(invoice.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",invoice.getRoleTypeId());
}

		if(invoice.getStatusId() != null ){
			returnVal.put("statusId",invoice.getStatusId());
}

		if(invoice.getBillingAccountId() != null ){
			returnVal.put("billingAccountId",invoice.getBillingAccountId());
}

		if(invoice.getContactMechId() != null ){
			returnVal.put("contactMechId",invoice.getContactMechId());
}

		if(invoice.getInvoiceDate() != null ){
			returnVal.put("invoiceDate",invoice.getInvoiceDate());
}

		if(invoice.getDueDate() != null ){
			returnVal.put("dueDate",invoice.getDueDate());
}

		if(invoice.getPaidDate() != null ){
			returnVal.put("paidDate",invoice.getPaidDate());
}

		if(invoice.getInvoiceMessage() != null ){
			returnVal.put("invoiceMessage",invoice.getInvoiceMessage());
}

		if(invoice.getReferenceNumber() != null ){
			returnVal.put("referenceNumber",invoice.getReferenceNumber());
}

		if(invoice.getDescription() != null ){
			returnVal.put("description",invoice.getDescription());
}

		if(invoice.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",invoice.getCurrencyUomId());
}

		if(invoice.getRecurrenceInfoId() != null ){
			returnVal.put("recurrenceInfoId",invoice.getRecurrenceInfoId());
}

		return returnVal;
}


	public static Invoice map(Map<String, Object> fields) {

		Invoice returnVal = new Invoice();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("invoiceDate") != null) {
			returnVal.setInvoiceDate((Timestamp) fields.get("invoiceDate"));
}

		if(fields.get("dueDate") != null) {
			returnVal.setDueDate((Timestamp) fields.get("dueDate"));
}

		if(fields.get("paidDate") != null) {
			returnVal.setPaidDate((Timestamp) fields.get("paidDate"));
}

		if(fields.get("invoiceMessage") != null) {
			returnVal.setInvoiceMessage((String) fields.get("invoiceMessage"));
}

		if(fields.get("referenceNumber") != null) {
			returnVal.setReferenceNumber((String) fields.get("referenceNumber"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}


		return returnVal;
 } 
	public static Invoice mapstrstr(Map<String, String> fields) throws Exception {

		Invoice returnVal = new Invoice();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("invoiceDate") != null) {
String buf = fields.get("invoiceDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setInvoiceDate(ibuf);
}

		if(fields.get("dueDate") != null) {
String buf = fields.get("dueDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDueDate(ibuf);
}

		if(fields.get("paidDate") != null) {
String buf = fields.get("paidDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPaidDate(ibuf);
}

		if(fields.get("invoiceMessage") != null) {
			returnVal.setInvoiceMessage((String) fields.get("invoiceMessage"));
}

		if(fields.get("referenceNumber") != null) {
			returnVal.setReferenceNumber((String) fields.get("referenceNumber"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}


		return returnVal;
 } 
	public static Invoice map(GenericValue val) {

Invoice returnVal = new Invoice();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceTypeId(val.getString("invoiceTypeId"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setInvoiceDate(val.getTimestamp("invoiceDate"));
		returnVal.setDueDate(val.getTimestamp("dueDate"));
		returnVal.setPaidDate(val.getTimestamp("paidDate"));
		returnVal.setInvoiceMessage(val.getString("invoiceMessage"));
		returnVal.setReferenceNumber(val.getString("referenceNumber"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setRecurrenceInfoId(val.getString("recurrenceInfoId"));


return returnVal;

}

public static Invoice map(HttpServletRequest request) throws Exception {

		Invoice returnVal = new Invoice();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("invoiceTypeId"))  {
returnVal.setInvoiceTypeId(request.getParameter("invoiceTypeId"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("billingAccountId"))  {
returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("invoiceDate"))  {
String buf = request.getParameter("invoiceDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setInvoiceDate(ibuf);
}
		if(paramMap.containsKey("dueDate"))  {
String buf = request.getParameter("dueDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDueDate(ibuf);
}
		if(paramMap.containsKey("paidDate"))  {
String buf = request.getParameter("paidDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPaidDate(ibuf);
}
		if(paramMap.containsKey("invoiceMessage"))  {
returnVal.setInvoiceMessage(request.getParameter("invoiceMessage"));
}
		if(paramMap.containsKey("referenceNumber"))  {
returnVal.setReferenceNumber(request.getParameter("referenceNumber"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("recurrenceInfoId"))  {
returnVal.setRecurrenceInfoId(request.getParameter("recurrenceInfoId"));
}
return returnVal;

}
}
