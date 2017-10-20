package com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.status.InvoiceStatus;

public class InvoiceStatusMapper  {


	public static Map<String, Object> map(InvoiceStatus invoicestatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicestatus.getStatusId() != null ){
			returnVal.put("statusId",invoicestatus.getStatusId());
}

		if(invoicestatus.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoicestatus.getInvoiceId());
}

		if(invoicestatus.getStatusDate() != null ){
			returnVal.put("statusDate",invoicestatus.getStatusDate());
}

		if(invoicestatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",invoicestatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static InvoiceStatus map(Map<String, Object> fields) {

		InvoiceStatus returnVal = new InvoiceStatus();

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static InvoiceStatus mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceStatus returnVal = new InvoiceStatus();

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static InvoiceStatus map(GenericValue val) {

InvoiceStatus returnVal = new InvoiceStatus();
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static InvoiceStatus map(HttpServletRequest request) throws Exception {

		InvoiceStatus returnVal = new InvoiceStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("statusId")) {
returnVal.setStatusId(request.getParameter("statusId"));
}

		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}
