package com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.model.InvoiceTermAttribute;

public class InvoiceTermAttributeMapper  {


	public static Map<String, Object> map(InvoiceTermAttribute invoicetermattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicetermattribute.getInvoiceTermId() != null ){
			returnVal.put("invoiceTermId",invoicetermattribute.getInvoiceTermId());
}

		if(invoicetermattribute.getAttrName() != null ){
			returnVal.put("attrName",invoicetermattribute.getAttrName());
}

		if(invoicetermattribute.getAttrValue() != null ){
			returnVal.put("attrValue",invoicetermattribute.getAttrValue());
}

		if(invoicetermattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",invoicetermattribute.getAttrDescription());
}

		return returnVal;
}


	public static InvoiceTermAttribute map(Map<String, Object> fields) {

		InvoiceTermAttribute returnVal = new InvoiceTermAttribute();

		if(fields.get("invoiceTermId") != null) {
			returnVal.setInvoiceTermId((String) fields.get("invoiceTermId"));
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
	public static InvoiceTermAttribute mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceTermAttribute returnVal = new InvoiceTermAttribute();

		if(fields.get("invoiceTermId") != null) {
			returnVal.setInvoiceTermId((String) fields.get("invoiceTermId"));
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
	public static InvoiceTermAttribute map(GenericValue val) {

InvoiceTermAttribute returnVal = new InvoiceTermAttribute();
		returnVal.setInvoiceTermId(val.getString("invoiceTermId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static InvoiceTermAttribute map(HttpServletRequest request) throws Exception {

		InvoiceTermAttribute returnVal = new InvoiceTermAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceTermId")) {
returnVal.setInvoiceTermId(request.getParameter("invoiceTermId"));
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
