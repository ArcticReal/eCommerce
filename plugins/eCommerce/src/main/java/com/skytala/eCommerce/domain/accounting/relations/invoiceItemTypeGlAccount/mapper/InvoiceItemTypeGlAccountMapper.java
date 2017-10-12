package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.model.InvoiceItemTypeGlAccount;

public class InvoiceItemTypeGlAccountMapper  {


	public static Map<String, Object> map(InvoiceItemTypeGlAccount invoiceitemtypeglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitemtypeglaccount.getInvoiceItemTypeId() != null ){
			returnVal.put("invoiceItemTypeId",invoiceitemtypeglaccount.getInvoiceItemTypeId());
}

		if(invoiceitemtypeglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",invoiceitemtypeglaccount.getOrganizationPartyId());
}

		if(invoiceitemtypeglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",invoiceitemtypeglaccount.getGlAccountId());
}

		return returnVal;
}


	public static InvoiceItemTypeGlAccount map(Map<String, Object> fields) {

		InvoiceItemTypeGlAccount returnVal = new InvoiceItemTypeGlAccount();

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static InvoiceItemTypeGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItemTypeGlAccount returnVal = new InvoiceItemTypeGlAccount();

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static InvoiceItemTypeGlAccount map(GenericValue val) {

InvoiceItemTypeGlAccount returnVal = new InvoiceItemTypeGlAccount();
		returnVal.setInvoiceItemTypeId(val.getString("invoiceItemTypeId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static InvoiceItemTypeGlAccount map(HttpServletRequest request) throws Exception {

		InvoiceItemTypeGlAccount returnVal = new InvoiceItemTypeGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceItemTypeId")) {
returnVal.setInvoiceItemTypeId(request.getParameter("invoiceItemTypeId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
return returnVal;

}
}
