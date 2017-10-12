package com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.model.InvoiceTypeAttr;

public class InvoiceTypeAttrMapper  {


	public static Map<String, Object> map(InvoiceTypeAttr invoicetypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicetypeattr.getInvoiceTypeId() != null ){
			returnVal.put("invoiceTypeId",invoicetypeattr.getInvoiceTypeId());
}

		if(invoicetypeattr.getAttrName() != null ){
			returnVal.put("attrName",invoicetypeattr.getAttrName());
}

		if(invoicetypeattr.getDescription() != null ){
			returnVal.put("description",invoicetypeattr.getDescription());
}

		return returnVal;
}


	public static InvoiceTypeAttr map(Map<String, Object> fields) {

		InvoiceTypeAttr returnVal = new InvoiceTypeAttr();

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InvoiceTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceTypeAttr returnVal = new InvoiceTypeAttr();

		if(fields.get("invoiceTypeId") != null) {
			returnVal.setInvoiceTypeId((String) fields.get("invoiceTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InvoiceTypeAttr map(GenericValue val) {

InvoiceTypeAttr returnVal = new InvoiceTypeAttr();
		returnVal.setInvoiceTypeId(val.getString("invoiceTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InvoiceTypeAttr map(HttpServletRequest request) throws Exception {

		InvoiceTypeAttr returnVal = new InvoiceTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceTypeId")) {
returnVal.setInvoiceTypeId(request.getParameter("invoiceTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
