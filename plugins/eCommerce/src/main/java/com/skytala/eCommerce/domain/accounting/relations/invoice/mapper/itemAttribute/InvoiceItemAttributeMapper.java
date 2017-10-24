package com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAttribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;

public class InvoiceItemAttributeMapper  {


	public static Map<String, Object> map(InvoiceItemAttribute invoiceitemattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitemattribute.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoiceitemattribute.getInvoiceId());
}

		if(invoiceitemattribute.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",invoiceitemattribute.getInvoiceItemSeqId());
}

		if(invoiceitemattribute.getAttrName() != null ){
			returnVal.put("attrName",invoiceitemattribute.getAttrName());
}

		if(invoiceitemattribute.getAttrValue() != null ){
			returnVal.put("attrValue",invoiceitemattribute.getAttrValue());
}

		if(invoiceitemattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",invoiceitemattribute.getAttrDescription());
}

		return returnVal;
}


	public static InvoiceItemAttribute map(Map<String, Object> fields) {

		InvoiceItemAttribute returnVal = new InvoiceItemAttribute();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static InvoiceItemAttribute mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItemAttribute returnVal = new InvoiceItemAttribute();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static InvoiceItemAttribute map(GenericValue val) {

InvoiceItemAttribute returnVal = new InvoiceItemAttribute();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static InvoiceItemAttribute map(HttpServletRequest request) throws Exception {

		InvoiceItemAttribute returnVal = new InvoiceItemAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
returnVal.setAttrValue(request.getParameter("attrValue"));
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
