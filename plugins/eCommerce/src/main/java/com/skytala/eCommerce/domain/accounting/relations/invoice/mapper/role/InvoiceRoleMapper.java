package com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.role.InvoiceRole;

public class InvoiceRoleMapper  {


	public static Map<String, Object> map(InvoiceRole invoicerole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicerole.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoicerole.getInvoiceId());
}

		if(invoicerole.getPartyId() != null ){
			returnVal.put("partyId",invoicerole.getPartyId());
}

		if(invoicerole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",invoicerole.getRoleTypeId());
}

		if(invoicerole.getDatetimePerformed() != null ){
			returnVal.put("datetimePerformed",invoicerole.getDatetimePerformed());
}

		if(invoicerole.getPercentage() != null ){
			returnVal.put("percentage",invoicerole.getPercentage());
}

		return returnVal;
}


	public static InvoiceRole map(Map<String, Object> fields) {

		InvoiceRole returnVal = new InvoiceRole();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("datetimePerformed") != null) {
			returnVal.setDatetimePerformed((Timestamp) fields.get("datetimePerformed"));
}

		if(fields.get("percentage") != null) {
			returnVal.setPercentage((BigDecimal) fields.get("percentage"));
}


		return returnVal;
 } 
	public static InvoiceRole mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceRole returnVal = new InvoiceRole();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("datetimePerformed") != null) {
String buf = fields.get("datetimePerformed");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDatetimePerformed(ibuf);
}

		if(fields.get("percentage") != null) {
String buf;
buf = fields.get("percentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentage(bd);
}


		return returnVal;
 } 
	public static InvoiceRole map(GenericValue val) {

InvoiceRole returnVal = new InvoiceRole();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setDatetimePerformed(val.getTimestamp("datetimePerformed"));
		returnVal.setPercentage(val.getBigDecimal("percentage"));


return returnVal;

}

public static InvoiceRole map(HttpServletRequest request) throws Exception {

		InvoiceRole returnVal = new InvoiceRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("datetimePerformed"))  {
String buf = request.getParameter("datetimePerformed");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDatetimePerformed(ibuf);
}
		if(paramMap.containsKey("percentage"))  {
String buf = request.getParameter("percentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentage(bd);
}
return returnVal;

}
}
