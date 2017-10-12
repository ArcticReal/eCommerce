package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.model.InvoiceItemTypeAttr;

public class InvoiceItemTypeAttrMapper  {


	public static Map<String, Object> map(InvoiceItemTypeAttr invoiceitemtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitemtypeattr.getInvoiceItemTypeId() != null ){
			returnVal.put("invoiceItemTypeId",invoiceitemtypeattr.getInvoiceItemTypeId());
}

		if(invoiceitemtypeattr.getAttrName() != null ){
			returnVal.put("attrName",invoiceitemtypeattr.getAttrName());
}

		if(invoiceitemtypeattr.getDescription() != null ){
			returnVal.put("description",invoiceitemtypeattr.getDescription());
}

		return returnVal;
}


	public static InvoiceItemTypeAttr map(Map<String, Object> fields) {

		InvoiceItemTypeAttr returnVal = new InvoiceItemTypeAttr();

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InvoiceItemTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItemTypeAttr returnVal = new InvoiceItemTypeAttr();

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InvoiceItemTypeAttr map(GenericValue val) {

InvoiceItemTypeAttr returnVal = new InvoiceItemTypeAttr();
		returnVal.setInvoiceItemTypeId(val.getString("invoiceItemTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InvoiceItemTypeAttr map(HttpServletRequest request) throws Exception {

		InvoiceItemTypeAttr returnVal = new InvoiceItemTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceItemTypeId")) {
returnVal.setInvoiceItemTypeId(request.getParameter("invoiceItemTypeId"));
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
