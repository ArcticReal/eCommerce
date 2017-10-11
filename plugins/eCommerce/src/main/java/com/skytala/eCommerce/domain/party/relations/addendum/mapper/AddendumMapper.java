package com.skytala.eCommerce.domain.party.relations.addendum.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.addendum.model.Addendum;

public class AddendumMapper  {


	public static Map<String, Object> map(Addendum addendum) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(addendum.getAddendumId() != null ){
			returnVal.put("addendumId",addendum.getAddendumId());
}

		if(addendum.getAgreementId() != null ){
			returnVal.put("agreementId",addendum.getAgreementId());
}

		if(addendum.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",addendum.getAgreementItemSeqId());
}

		if(addendum.getAddendumCreationDate() != null ){
			returnVal.put("addendumCreationDate",addendum.getAddendumCreationDate());
}

		if(addendum.getAddendumEffectiveDate() != null ){
			returnVal.put("addendumEffectiveDate",addendum.getAddendumEffectiveDate());
}

		if(addendum.getAddendumText() != null ){
			returnVal.put("addendumText",addendum.getAddendumText());
}

		return returnVal;
}


	public static Addendum map(Map<String, Object> fields) {

		Addendum returnVal = new Addendum();

		if(fields.get("addendumId") != null) {
			returnVal.setAddendumId((String) fields.get("addendumId"));
}

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("addendumCreationDate") != null) {
			returnVal.setAddendumCreationDate((Timestamp) fields.get("addendumCreationDate"));
}

		if(fields.get("addendumEffectiveDate") != null) {
			returnVal.setAddendumEffectiveDate((Timestamp) fields.get("addendumEffectiveDate"));
}

		if(fields.get("addendumText") != null) {
			returnVal.setAddendumText((String) fields.get("addendumText"));
}


		return returnVal;
 } 
	public static Addendum mapstrstr(Map<String, String> fields) throws Exception {

		Addendum returnVal = new Addendum();

		if(fields.get("addendumId") != null) {
			returnVal.setAddendumId((String) fields.get("addendumId"));
}

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("addendumCreationDate") != null) {
String buf = fields.get("addendumCreationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAddendumCreationDate(ibuf);
}

		if(fields.get("addendumEffectiveDate") != null) {
String buf = fields.get("addendumEffectiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAddendumEffectiveDate(ibuf);
}

		if(fields.get("addendumText") != null) {
			returnVal.setAddendumText((String) fields.get("addendumText"));
}


		return returnVal;
 } 
	public static Addendum map(GenericValue val) {

Addendum returnVal = new Addendum();
		returnVal.setAddendumId(val.getString("addendumId"));
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setAddendumCreationDate(val.getTimestamp("addendumCreationDate"));
		returnVal.setAddendumEffectiveDate(val.getTimestamp("addendumEffectiveDate"));
		returnVal.setAddendumText(val.getString("addendumText"));


return returnVal;

}

public static Addendum map(HttpServletRequest request) throws Exception {

		Addendum returnVal = new Addendum();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("addendumId")) {
returnVal.setAddendumId(request.getParameter("addendumId"));
}

		if(paramMap.containsKey("agreementId"))  {
returnVal.setAgreementId(request.getParameter("agreementId"));
}
		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("addendumCreationDate"))  {
String buf = request.getParameter("addendumCreationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAddendumCreationDate(ibuf);
}
		if(paramMap.containsKey("addendumEffectiveDate"))  {
String buf = request.getParameter("addendumEffectiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAddendumEffectiveDate(ibuf);
}
		if(paramMap.containsKey("addendumText"))  {
returnVal.setAddendumText(request.getParameter("addendumText"));
}
return returnVal;

}
}
