package com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.model.InvoiceAttribute;

public class InvoiceAttributeMapper  {


	public static Map<String, Object> map(InvoiceAttribute invoiceattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceattribute.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoiceattribute.getInvoiceId());
}

		if(invoiceattribute.getAttrName() != null ){
			returnVal.put("attrName",invoiceattribute.getAttrName());
}

		if(invoiceattribute.getAttrValue() != null ){
			returnVal.put("attrValue",invoiceattribute.getAttrValue());
}

		if(invoiceattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",invoiceattribute.getAttrDescription());
}

		return returnVal;
}


	public static InvoiceAttribute map(Map<String, Object> fields) {

		InvoiceAttribute returnVal = new InvoiceAttribute();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static InvoiceAttribute mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceAttribute returnVal = new InvoiceAttribute();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static InvoiceAttribute map(GenericValue val) {

InvoiceAttribute returnVal = new InvoiceAttribute();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static InvoiceAttribute map(HttpServletRequest request) throws Exception {

		InvoiceAttribute returnVal = new InvoiceAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
