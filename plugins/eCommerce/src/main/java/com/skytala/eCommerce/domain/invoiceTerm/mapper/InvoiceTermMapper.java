package com.skytala.eCommerce.domain.invoiceTerm.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.invoiceTerm.model.InvoiceTerm;

public class InvoiceTermMapper  {


	public static Map<String, Object> map(InvoiceTerm invoiceterm) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceterm.getInvoiceTermId() != null ){
			returnVal.put("invoiceTermId",invoiceterm.getInvoiceTermId());
}

		if(invoiceterm.getTermTypeId() != null ){
			returnVal.put("termTypeId",invoiceterm.getTermTypeId());
}

		if(invoiceterm.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoiceterm.getInvoiceId());
}

		if(invoiceterm.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",invoiceterm.getInvoiceItemSeqId());
}

		if(invoiceterm.getTermValue() != null ){
			returnVal.put("termValue",invoiceterm.getTermValue());
}

		if(invoiceterm.getTermDays() != null ){
			returnVal.put("termDays",invoiceterm.getTermDays());
}

		if(invoiceterm.getTextValue() != null ){
			returnVal.put("textValue",invoiceterm.getTextValue());
}

		if(invoiceterm.getDescription() != null ){
			returnVal.put("description",invoiceterm.getDescription());
}

		if(invoiceterm.getUomId() != null ){
			returnVal.put("uomId",invoiceterm.getUomId());
}

		return returnVal;
}


	public static InvoiceTerm map(Map<String, Object> fields) {

		InvoiceTerm returnVal = new InvoiceTerm();

		if(fields.get("invoiceTermId") != null) {
			returnVal.setInvoiceTermId((String) fields.get("invoiceTermId"));
}

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("termValue") != null) {
			returnVal.setTermValue((BigDecimal) fields.get("termValue"));
}

		if(fields.get("termDays") != null) {
			returnVal.setTermDays((long) fields.get("termDays"));
}

		if(fields.get("textValue") != null) {
			returnVal.setTextValue((String) fields.get("textValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static InvoiceTerm mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceTerm returnVal = new InvoiceTerm();

		if(fields.get("invoiceTermId") != null) {
			returnVal.setInvoiceTermId((String) fields.get("invoiceTermId"));
}

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("termValue") != null) {
String buf;
buf = fields.get("termValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTermValue(bd);
}

		if(fields.get("termDays") != null) {
String buf;
buf = fields.get("termDays");
long ibuf = Long.parseLong(buf);
			returnVal.setTermDays(ibuf);
}

		if(fields.get("textValue") != null) {
			returnVal.setTextValue((String) fields.get("textValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static InvoiceTerm map(GenericValue val) {

InvoiceTerm returnVal = new InvoiceTerm();
		returnVal.setInvoiceTermId(val.getString("invoiceTermId"));
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setTermValue(val.getBigDecimal("termValue"));
		returnVal.setTermDays(val.getLong("termDays"));
		returnVal.setTextValue(val.getString("textValue"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setUomId(val.getString("uomId"));


return returnVal;

}

public static InvoiceTerm map(HttpServletRequest request) throws Exception {

		InvoiceTerm returnVal = new InvoiceTerm();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceTermId")) {
returnVal.setInvoiceTermId(request.getParameter("invoiceTermId"));
}

		if(paramMap.containsKey("termTypeId"))  {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
}
		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
		if(paramMap.containsKey("termValue"))  {
String buf = request.getParameter("termValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTermValue(bd);
}
		if(paramMap.containsKey("termDays"))  {
String buf = request.getParameter("termDays");
Long ibuf = Long.parseLong(buf);
returnVal.setTermDays(ibuf);
}
		if(paramMap.containsKey("textValue"))  {
returnVal.setTextValue(request.getParameter("textValue"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
return returnVal;

}
}
