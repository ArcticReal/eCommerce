package com.skytala.eCommerce.domain.accounting.relations.invoiceContent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContent.model.InvoiceContent;

public class InvoiceContentMapper  {


	public static Map<String, Object> map(InvoiceContent invoicecontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicecontent.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoicecontent.getInvoiceId());
}

		if(invoicecontent.getInvoiceContentTypeId() != null ){
			returnVal.put("invoiceContentTypeId",invoicecontent.getInvoiceContentTypeId());
}

		if(invoicecontent.getContentId() != null ){
			returnVal.put("contentId",invoicecontent.getContentId());
}

		if(invoicecontent.getFromDate() != null ){
			returnVal.put("fromDate",invoicecontent.getFromDate());
}

		if(invoicecontent.getThruDate() != null ){
			returnVal.put("thruDate",invoicecontent.getThruDate());
}

		return returnVal;
}


	public static InvoiceContent map(Map<String, Object> fields) {

		InvoiceContent returnVal = new InvoiceContent();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceContentTypeId") != null) {
			returnVal.setInvoiceContentTypeId((String) fields.get("invoiceContentTypeId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static InvoiceContent mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceContent returnVal = new InvoiceContent();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceContentTypeId") != null) {
			returnVal.setInvoiceContentTypeId((String) fields.get("invoiceContentTypeId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
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


		return returnVal;
 } 
	public static InvoiceContent map(GenericValue val) {

InvoiceContent returnVal = new InvoiceContent();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceContentTypeId(val.getString("invoiceContentTypeId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static InvoiceContent map(HttpServletRequest request) throws Exception {

		InvoiceContent returnVal = new InvoiceContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("invoiceContentTypeId"))  {
returnVal.setInvoiceContentTypeId(request.getParameter("invoiceContentTypeId"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
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
return returnVal;

}
}
