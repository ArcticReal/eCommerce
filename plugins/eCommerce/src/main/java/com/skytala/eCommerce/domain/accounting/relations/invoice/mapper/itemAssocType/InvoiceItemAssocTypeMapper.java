package com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAssocType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssocType.InvoiceItemAssocType;

public class InvoiceItemAssocTypeMapper  {


	public static Map<String, Object> map(InvoiceItemAssocType invoiceitemassoctype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitemassoctype.getInvoiceItemAssocTypeId() != null ){
			returnVal.put("invoiceItemAssocTypeId",invoiceitemassoctype.getInvoiceItemAssocTypeId());
}

		if(invoiceitemassoctype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",invoiceitemassoctype.getParentTypeId());
}

		if(invoiceitemassoctype.getHasTable() != null ){
			returnVal.put("hasTable",invoiceitemassoctype.getHasTable());
}

		if(invoiceitemassoctype.getDescription() != null ){
			returnVal.put("description",invoiceitemassoctype.getDescription());
}

		return returnVal;
}


	public static InvoiceItemAssocType map(Map<String, Object> fields) {

		InvoiceItemAssocType returnVal = new InvoiceItemAssocType();

		if(fields.get("invoiceItemAssocTypeId") != null) {
			returnVal.setInvoiceItemAssocTypeId((String) fields.get("invoiceItemAssocTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InvoiceItemAssocType mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItemAssocType returnVal = new InvoiceItemAssocType();

		if(fields.get("invoiceItemAssocTypeId") != null) {
			returnVal.setInvoiceItemAssocTypeId((String) fields.get("invoiceItemAssocTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InvoiceItemAssocType map(GenericValue val) {

InvoiceItemAssocType returnVal = new InvoiceItemAssocType();
		returnVal.setInvoiceItemAssocTypeId(val.getString("invoiceItemAssocTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InvoiceItemAssocType map(HttpServletRequest request) throws Exception {

		InvoiceItemAssocType returnVal = new InvoiceItemAssocType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceItemAssocTypeId")) {
returnVal.setInvoiceItemAssocTypeId(request.getParameter("invoiceItemAssocTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
