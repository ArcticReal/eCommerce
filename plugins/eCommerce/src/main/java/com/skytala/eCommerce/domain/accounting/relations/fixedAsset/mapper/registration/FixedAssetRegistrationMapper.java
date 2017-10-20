package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.registration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.registration.FixedAssetRegistration;

public class FixedAssetRegistrationMapper  {


	public static Map<String, Object> map(FixedAssetRegistration fixedassetregistration) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetregistration.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetregistration.getFixedAssetId());
}

		if(fixedassetregistration.getFromDate() != null ){
			returnVal.put("fromDate",fixedassetregistration.getFromDate());
}

		if(fixedassetregistration.getThruDate() != null ){
			returnVal.put("thruDate",fixedassetregistration.getThruDate());
}

		if(fixedassetregistration.getRegistrationDate() != null ){
			returnVal.put("registrationDate",fixedassetregistration.getRegistrationDate());
}

		if(fixedassetregistration.getGovAgencyPartyId() != null ){
			returnVal.put("govAgencyPartyId",fixedassetregistration.getGovAgencyPartyId());
}

		if(fixedassetregistration.getRegistrationNumber() != null ){
			returnVal.put("registrationNumber",fixedassetregistration.getRegistrationNumber());
}

		if(fixedassetregistration.getLicenseNumber() != null ){
			returnVal.put("licenseNumber",fixedassetregistration.getLicenseNumber());
}

		return returnVal;
}


	public static FixedAssetRegistration map(Map<String, Object> fields) {

		FixedAssetRegistration returnVal = new FixedAssetRegistration();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("registrationDate") != null) {
			returnVal.setRegistrationDate((Timestamp) fields.get("registrationDate"));
}

		if(fields.get("govAgencyPartyId") != null) {
			returnVal.setGovAgencyPartyId((String) fields.get("govAgencyPartyId"));
}

		if(fields.get("registrationNumber") != null) {
			returnVal.setRegistrationNumber((String) fields.get("registrationNumber"));
}

		if(fields.get("licenseNumber") != null) {
			returnVal.setLicenseNumber((String) fields.get("licenseNumber"));
}


		return returnVal;
 } 
	public static FixedAssetRegistration mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetRegistration returnVal = new FixedAssetRegistration();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
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

		if(fields.get("registrationDate") != null) {
String buf = fields.get("registrationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setRegistrationDate(ibuf);
}

		if(fields.get("govAgencyPartyId") != null) {
			returnVal.setGovAgencyPartyId((String) fields.get("govAgencyPartyId"));
}

		if(fields.get("registrationNumber") != null) {
			returnVal.setRegistrationNumber((String) fields.get("registrationNumber"));
}

		if(fields.get("licenseNumber") != null) {
			returnVal.setLicenseNumber((String) fields.get("licenseNumber"));
}


		return returnVal;
 } 
	public static FixedAssetRegistration map(GenericValue val) {

FixedAssetRegistration returnVal = new FixedAssetRegistration();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setRegistrationDate(val.getTimestamp("registrationDate"));
		returnVal.setGovAgencyPartyId(val.getString("govAgencyPartyId"));
		returnVal.setRegistrationNumber(val.getString("registrationNumber"));
		returnVal.setLicenseNumber(val.getString("licenseNumber"));


return returnVal;

}

public static FixedAssetRegistration map(HttpServletRequest request) throws Exception {

		FixedAssetRegistration returnVal = new FixedAssetRegistration();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
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
		if(paramMap.containsKey("registrationDate"))  {
String buf = request.getParameter("registrationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setRegistrationDate(ibuf);
}
		if(paramMap.containsKey("govAgencyPartyId"))  {
returnVal.setGovAgencyPartyId(request.getParameter("govAgencyPartyId"));
}
		if(paramMap.containsKey("registrationNumber"))  {
returnVal.setRegistrationNumber(request.getParameter("registrationNumber"));
}
		if(paramMap.containsKey("licenseNumber"))  {
returnVal.setLicenseNumber(request.getParameter("licenseNumber"));
}
return returnVal;

}
}
