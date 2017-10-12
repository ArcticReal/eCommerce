package com.skytala.eCommerce.domain.accounting.relations.invoiceType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.model.InvoiceType;

public class InvoiceTypeMapper  {


	public static Map<String, Object> map(InvoiceType invoicetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicetype.getInvoiceTypeId() != null ){
			returnVal.put("invoiceTypeId",invoicetype.getInvoiceTypeId());
}

		if(invoicetype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",invoicetype.getParentTypeId());
}

		if(invoicetype.getHasTable() != null ){
			returnVal.put("hasTable",invoicetype.getHasTable());
}

		if(invoicetype.getDescription() != null ){
			returnVal.put("description",invoicetype.getDescription());
}

		return returnVal;
}


	public static InvoiceType map(Map<String, Object> fields) {

		InvoiceType returnVal = new InvoiceType();

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
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
	public static InvoiceType mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceType returnVal = new InvoiceType();

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
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
	public static InvoiceType map(GenericValue val) {

InvoiceType returnVal = new InvoiceType();
		returnVal.setInvoiceTypeId(val.getString("invoiceTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InvoiceType map(HttpServletRequest request) throws Exception {

		InvoiceType returnVal = new InvoiceType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceTypeId")) {
returnVal.setInvoiceTypeId(request.getParameter("invoiceTypeId"));
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
