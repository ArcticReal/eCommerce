package com.skytala.eCommerce.domain.agreementTerm.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.agreementTerm.model.AgreementTerm;

public class AgreementTermMapper  {


	public static Map<String, Object> map(AgreementTerm agreementterm) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementterm.getAgreementTermId() != null ){
			returnVal.put("agreementTermId",agreementterm.getAgreementTermId());
}

		if(agreementterm.getTermTypeId() != null ){
			returnVal.put("termTypeId",agreementterm.getTermTypeId());
}

		if(agreementterm.getAgreementId() != null ){
			returnVal.put("agreementId",agreementterm.getAgreementId());
}

		if(agreementterm.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementterm.getAgreementItemSeqId());
}

		if(agreementterm.getInvoiceItemTypeId() != null ){
			returnVal.put("invoiceItemTypeId",agreementterm.getInvoiceItemTypeId());
}

		if(agreementterm.getFromDate() != null ){
			returnVal.put("fromDate",agreementterm.getFromDate());
}

		if(agreementterm.getThruDate() != null ){
			returnVal.put("thruDate",agreementterm.getThruDate());
}

		if(agreementterm.getTermValue() != null ){
			returnVal.put("termValue",agreementterm.getTermValue());
}

		if(agreementterm.getTermDays() != null ){
			returnVal.put("termDays",agreementterm.getTermDays());
}

		if(agreementterm.getTextValue() != null ){
			returnVal.put("textValue",agreementterm.getTextValue());
}

		if(agreementterm.getMinQuantity() != null ){
			returnVal.put("minQuantity",agreementterm.getMinQuantity());
}

		if(agreementterm.getMaxQuantity() != null ){
			returnVal.put("maxQuantity",agreementterm.getMaxQuantity());
}

		if(agreementterm.getDescription() != null ){
			returnVal.put("description",agreementterm.getDescription());
}

		return returnVal;
}


	public static AgreementTerm map(Map<String, Object> fields) {

		AgreementTerm returnVal = new AgreementTerm();

		if(fields.get("agreementTermId") != null) {
			returnVal.setAgreementTermId((String) fields.get("agreementTermId"));
}

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
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

		if(fields.get("minQuantity") != null) {
			returnVal.setMinQuantity((BigDecimal) fields.get("minQuantity"));
}

		if(fields.get("maxQuantity") != null) {
			returnVal.setMaxQuantity((BigDecimal) fields.get("maxQuantity"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AgreementTerm mapstrstr(Map<String, String> fields) throws Exception {

		AgreementTerm returnVal = new AgreementTerm();

		if(fields.get("agreementTermId") != null) {
			returnVal.setAgreementTermId((String) fields.get("agreementTermId"));
}

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
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

		if(fields.get("minQuantity") != null) {
String buf;
buf = fields.get("minQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinQuantity(bd);
}

		if(fields.get("maxQuantity") != null) {
String buf;
buf = fields.get("maxQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxQuantity(bd);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AgreementTerm map(GenericValue val) {

AgreementTerm returnVal = new AgreementTerm();
		returnVal.setAgreementTermId(val.getString("agreementTermId"));
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setInvoiceItemTypeId(val.getString("invoiceItemTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setTermValue(val.getBigDecimal("termValue"));
		returnVal.setTermDays(val.getLong("termDays"));
		returnVal.setTextValue(val.getString("textValue"));
		returnVal.setMinQuantity(val.getBigDecimal("minQuantity"));
		returnVal.setMaxQuantity(val.getBigDecimal("maxQuantity"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AgreementTerm map(HttpServletRequest request) throws Exception {

		AgreementTerm returnVal = new AgreementTerm();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementTermId")) {
returnVal.setAgreementTermId(request.getParameter("agreementTermId"));
}

		if(paramMap.containsKey("termTypeId"))  {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
}
		if(paramMap.containsKey("agreementId"))  {
returnVal.setAgreementId(request.getParameter("agreementId"));
}
		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("invoiceItemTypeId"))  {
returnVal.setInvoiceItemTypeId(request.getParameter("invoiceItemTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
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
		if(paramMap.containsKey("minQuantity"))  {
String buf = request.getParameter("minQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinQuantity(bd);
}
		if(paramMap.containsKey("maxQuantity"))  {
String buf = request.getParameter("maxQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxQuantity(bd);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
