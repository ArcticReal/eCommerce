package com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.note;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.note.InvoiceNote;

public class InvoiceNoteMapper  {


	public static Map<String, Object> map(InvoiceNote invoicenote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicenote.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoicenote.getInvoiceId());
}

		if(invoicenote.getNoteId() != null ){
			returnVal.put("noteId",invoicenote.getNoteId());
}

		return returnVal;
}


	public static InvoiceNote map(Map<String, Object> fields) {

		InvoiceNote returnVal = new InvoiceNote();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static InvoiceNote mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceNote returnVal = new InvoiceNote();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static InvoiceNote map(GenericValue val) {

InvoiceNote returnVal = new InvoiceNote();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setNoteId(val.getString("noteId"));


return returnVal;

}

public static InvoiceNote map(HttpServletRequest request) throws Exception {

		InvoiceNote returnVal = new InvoiceNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
return returnVal;

}
}
