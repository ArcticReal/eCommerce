package com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.model.InvoiceContactMech;

public class InvoiceContactMechMapper  {


	public static Map<String, Object> map(InvoiceContactMech invoicecontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicecontactmech.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoicecontactmech.getInvoiceId());
}

		if(invoicecontactmech.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",invoicecontactmech.getContactMechPurposeTypeId());
}

		if(invoicecontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",invoicecontactmech.getContactMechId());
}

		return returnVal;
}


	public static InvoiceContactMech map(Map<String, Object> fields) {

		InvoiceContactMech returnVal = new InvoiceContactMech();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static InvoiceContactMech mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceContactMech returnVal = new InvoiceContactMech();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static InvoiceContactMech map(GenericValue val) {

InvoiceContactMech returnVal = new InvoiceContactMech();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));
		returnVal.setContactMechId(val.getString("contactMechId"));


return returnVal;

}

public static InvoiceContactMech map(HttpServletRequest request) throws Exception {

		InvoiceContactMech returnVal = new InvoiceContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("contactMechPurposeTypeId"))  {
returnVal.setContactMechPurposeTypeId(request.getParameter("contactMechPurposeTypeId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
return returnVal;

}
}
