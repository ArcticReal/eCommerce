package com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.model.AgreementGeographicalApplic;

public class AgreementGeographicalApplicMapper  {


	public static Map<String, Object> map(AgreementGeographicalApplic agreementgeographicalapplic) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementgeographicalapplic.getAgreementId() != null ){
			returnVal.put("agreementId",agreementgeographicalapplic.getAgreementId());
}

		if(agreementgeographicalapplic.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementgeographicalapplic.getAgreementItemSeqId());
}

		if(agreementgeographicalapplic.getGeoId() != null ){
			returnVal.put("geoId",agreementgeographicalapplic.getGeoId());
}

		return returnVal;
}


	public static AgreementGeographicalApplic map(Map<String, Object> fields) {

		AgreementGeographicalApplic returnVal = new AgreementGeographicalApplic();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}


		return returnVal;
 } 
	public static AgreementGeographicalApplic mapstrstr(Map<String, String> fields) throws Exception {

		AgreementGeographicalApplic returnVal = new AgreementGeographicalApplic();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}


		return returnVal;
 } 
	public static AgreementGeographicalApplic map(GenericValue val) {

AgreementGeographicalApplic returnVal = new AgreementGeographicalApplic();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setGeoId(val.getString("geoId"));


return returnVal;

}

public static AgreementGeographicalApplic map(HttpServletRequest request) throws Exception {

		AgreementGeographicalApplic returnVal = new AgreementGeographicalApplic();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
}
return returnVal;

}
}
