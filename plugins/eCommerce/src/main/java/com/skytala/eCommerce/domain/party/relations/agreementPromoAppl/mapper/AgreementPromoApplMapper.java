package com.skytala.eCommerce.domain.party.relations.agreementPromoAppl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementPromoAppl.model.AgreementPromoAppl;

public class AgreementPromoApplMapper  {


	public static Map<String, Object> map(AgreementPromoAppl agreementpromoappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementpromoappl.getAgreementId() != null ){
			returnVal.put("agreementId",agreementpromoappl.getAgreementId());
}

		if(agreementpromoappl.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementpromoappl.getAgreementItemSeqId());
}

		if(agreementpromoappl.getProductPromoId() != null ){
			returnVal.put("productPromoId",agreementpromoappl.getProductPromoId());
}

		if(agreementpromoappl.getFromDate() != null ){
			returnVal.put("fromDate",agreementpromoappl.getFromDate());
}

		if(agreementpromoappl.getThruDate() != null ){
			returnVal.put("thruDate",agreementpromoappl.getThruDate());
}

		if(agreementpromoappl.getSequenceNum() != null ){
			returnVal.put("sequenceNum",agreementpromoappl.getSequenceNum());
}

		return returnVal;
}


	public static AgreementPromoAppl map(Map<String, Object> fields) {

		AgreementPromoAppl returnVal = new AgreementPromoAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static AgreementPromoAppl mapstrstr(Map<String, String> fields) throws Exception {

		AgreementPromoAppl returnVal = new AgreementPromoAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static AgreementPromoAppl map(GenericValue val) {

AgreementPromoAppl returnVal = new AgreementPromoAppl();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static AgreementPromoAppl map(HttpServletRequest request) throws Exception {

		AgreementPromoAppl returnVal = new AgreementPromoAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("productPromoId"))  {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
