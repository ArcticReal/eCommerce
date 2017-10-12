package com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.model.InvoiceItemType;

public class InvoiceItemTypeMapper  {


	public static Map<String, Object> map(InvoiceItemType invoiceitemtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitemtype.getInvoiceItemTypeId() != null ){
			returnVal.put("invoiceItemTypeId",invoiceitemtype.getInvoiceItemTypeId());
}

		if(invoiceitemtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",invoiceitemtype.getParentTypeId());
}

		if(invoiceitemtype.getHasTable() != null ){
			returnVal.put("hasTable",invoiceitemtype.getHasTable());
}

		if(invoiceitemtype.getDescription() != null ){
			returnVal.put("description",invoiceitemtype.getDescription());
}

		if(invoiceitemtype.getDefaultGlAccountId() != null ){
			returnVal.put("defaultGlAccountId",invoiceitemtype.getDefaultGlAccountId());
}

		return returnVal;
}


	public static InvoiceItemType map(Map<String, Object> fields) {

		InvoiceItemType returnVal = new InvoiceItemType();

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
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

		if(fields.get("defaultGlAccountId") != null) {
			returnVal.setDefaultGlAccountId((String) fields.get("defaultGlAccountId"));
}


		return returnVal;
 } 
	public static InvoiceItemType mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItemType returnVal = new InvoiceItemType();

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
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

		if(fields.get("defaultGlAccountId") != null) {
			returnVal.setDefaultGlAccountId((String) fields.get("defaultGlAccountId"));
}


		return returnVal;
 } 
	public static InvoiceItemType map(GenericValue val) {

InvoiceItemType returnVal = new InvoiceItemType();
		returnVal.setInvoiceItemTypeId(val.getString("invoiceItemTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setDefaultGlAccountId(val.getString("defaultGlAccountId"));


return returnVal;

}

public static InvoiceItemType map(HttpServletRequest request) throws Exception {

		InvoiceItemType returnVal = new InvoiceItemType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceItemTypeId")) {
returnVal.setInvoiceItemTypeId(request.getParameter("invoiceItemTypeId"));
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
		if(paramMap.containsKey("defaultGlAccountId"))  {
returnVal.setDefaultGlAccountId(request.getParameter("defaultGlAccountId"));
}
return returnVal;

}
}
