package com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeMap;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;

public class InvoiceItemTypeMapMapper  {


	public static Map<String, Object> map(InvoiceItemTypeMap invoiceitemtypemap) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitemtypemap.getInvoiceItemMapKey() != null ){
			returnVal.put("invoiceItemMapKey",invoiceitemtypemap.getInvoiceItemMapKey());
}

		if(invoiceitemtypemap.getInvoiceTypeId() != null ){
			returnVal.put("invoiceTypeId",invoiceitemtypemap.getInvoiceTypeId());
}

		if(invoiceitemtypemap.getInvoiceItemTypeId() != null ){
			returnVal.put("invoiceItemTypeId",invoiceitemtypemap.getInvoiceItemTypeId());
}

		return returnVal;
}


	public static InvoiceItemTypeMap map(Map<String, Object> fields) {

		InvoiceItemTypeMap returnVal = new InvoiceItemTypeMap();

		if(fields.get("invoiceItemMapKey") != null) {
			returnVal.setInvoiceItemMapKey((String) fields.get("invoiceItemMapKey"));
}

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
}

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}


		return returnVal;
 } 
	public static InvoiceItemTypeMap mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItemTypeMap returnVal = new InvoiceItemTypeMap();

		if(fields.get("invoiceItemMapKey") != null) {
			returnVal.setInvoiceItemMapKey((String) fields.get("invoiceItemMapKey"));
}

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
}

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}


		return returnVal;
 } 
	public static InvoiceItemTypeMap map(GenericValue val) {

InvoiceItemTypeMap returnVal = new InvoiceItemTypeMap();
		returnVal.setInvoiceItemMapKey(val.getString("invoiceItemMapKey"));
		returnVal.setInvoiceTypeId(val.getString("invoiceTypeId"));
		returnVal.setInvoiceItemTypeId(val.getString("invoiceItemTypeId"));


return returnVal;

}

public static InvoiceItemTypeMap map(HttpServletRequest request) throws Exception {

		InvoiceItemTypeMap returnVal = new InvoiceItemTypeMap();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceItemMapKey")) {
returnVal.setInvoiceItemMapKey(request.getParameter("invoiceItemMapKey"));
}

		if(paramMap.containsKey("invoiceTypeId"))  {
returnVal.setInvoiceTypeId(request.getParameter("invoiceTypeId"));
}
		if(paramMap.containsKey("invoiceItemTypeId"))  {
returnVal.setInvoiceItemTypeId(request.getParameter("invoiceItemTypeId"));
}
return returnVal;

}
}
