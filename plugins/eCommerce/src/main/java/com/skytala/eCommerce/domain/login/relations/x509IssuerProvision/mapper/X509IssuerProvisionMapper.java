package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;

public class X509IssuerProvisionMapper  {


	public static Map<String, Object> map(X509IssuerProvision x509issuerprovision) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(x509issuerprovision.getCertProvisionId() != null ){
			returnVal.put("certProvisionId",x509issuerprovision.getCertProvisionId());
}

		if(x509issuerprovision.getCommonName() != null ){
			returnVal.put("commonName",x509issuerprovision.getCommonName());
}

		if(x509issuerprovision.getOrganizationalUnit() != null ){
			returnVal.put("organizationalUnit",x509issuerprovision.getOrganizationalUnit());
}

		if(x509issuerprovision.getOrganizationName() != null ){
			returnVal.put("organizationName",x509issuerprovision.getOrganizationName());
}

		if(x509issuerprovision.getCityLocality() != null ){
			returnVal.put("cityLocality",x509issuerprovision.getCityLocality());
}

		if(x509issuerprovision.getStateProvince() != null ){
			returnVal.put("stateProvince",x509issuerprovision.getStateProvince());
}

		if(x509issuerprovision.getCountry() != null ){
			returnVal.put("country",x509issuerprovision.getCountry());
}

		if(x509issuerprovision.getSerialNumber() != null ){
			returnVal.put("serialNumber",x509issuerprovision.getSerialNumber());
}

		return returnVal;
}


	public static X509IssuerProvision map(Map<String, Object> fields) {

		X509IssuerProvision returnVal = new X509IssuerProvision();

		if(fields.get("certProvisionId") != null) {
			returnVal.setCertProvisionId((String) fields.get("certProvisionId"));
}

		if(fields.get("commonName") != null) {
			returnVal.setCommonName((String) fields.get("commonName"));
}

		if(fields.get("organizationalUnit") != null) {
			returnVal.setOrganizationalUnit((String) fields.get("organizationalUnit"));
}

		if(fields.get("organizationName") != null) {
			returnVal.setOrganizationName((String) fields.get("organizationName"));
}

		if(fields.get("cityLocality") != null) {
			returnVal.setCityLocality((String) fields.get("cityLocality"));
}

		if(fields.get("stateProvince") != null) {
			returnVal.setStateProvince((String) fields.get("stateProvince"));
}

		if(fields.get("country") != null) {
			returnVal.setCountry((String) fields.get("country"));
}

		if(fields.get("serialNumber") != null) {
			returnVal.setSerialNumber((String) fields.get("serialNumber"));
}


		return returnVal;
 } 
	public static X509IssuerProvision mapstrstr(Map<String, String> fields) throws Exception {

		X509IssuerProvision returnVal = new X509IssuerProvision();

		if(fields.get("certProvisionId") != null) {
			returnVal.setCertProvisionId((String) fields.get("certProvisionId"));
}

		if(fields.get("commonName") != null) {
			returnVal.setCommonName((String) fields.get("commonName"));
}

		if(fields.get("organizationalUnit") != null) {
			returnVal.setOrganizationalUnit((String) fields.get("organizationalUnit"));
}

		if(fields.get("organizationName") != null) {
			returnVal.setOrganizationName((String) fields.get("organizationName"));
}

		if(fields.get("cityLocality") != null) {
			returnVal.setCityLocality((String) fields.get("cityLocality"));
}

		if(fields.get("stateProvince") != null) {
			returnVal.setStateProvince((String) fields.get("stateProvince"));
}

		if(fields.get("country") != null) {
			returnVal.setCountry((String) fields.get("country"));
}

		if(fields.get("serialNumber") != null) {
			returnVal.setSerialNumber((String) fields.get("serialNumber"));
}


		return returnVal;
 } 
	public static X509IssuerProvision map(GenericValue val) {

X509IssuerProvision returnVal = new X509IssuerProvision();
		returnVal.setCertProvisionId(val.getString("certProvisionId"));
		returnVal.setCommonName(val.getString("commonName"));
		returnVal.setOrganizationalUnit(val.getString("organizationalUnit"));
		returnVal.setOrganizationName(val.getString("organizationName"));
		returnVal.setCityLocality(val.getString("cityLocality"));
		returnVal.setStateProvince(val.getString("stateProvince"));
		returnVal.setCountry(val.getString("country"));
		returnVal.setSerialNumber(val.getString("serialNumber"));


return returnVal;

}

public static X509IssuerProvision map(HttpServletRequest request) throws Exception {

		X509IssuerProvision returnVal = new X509IssuerProvision();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("certProvisionId")) {
returnVal.setCertProvisionId(request.getParameter("certProvisionId"));
}

		if(paramMap.containsKey("commonName"))  {
returnVal.setCommonName(request.getParameter("commonName"));
}
		if(paramMap.containsKey("organizationalUnit"))  {
returnVal.setOrganizationalUnit(request.getParameter("organizationalUnit"));
}
		if(paramMap.containsKey("organizationName"))  {
returnVal.setOrganizationName(request.getParameter("organizationName"));
}
		if(paramMap.containsKey("cityLocality"))  {
returnVal.setCityLocality(request.getParameter("cityLocality"));
}
		if(paramMap.containsKey("stateProvince"))  {
returnVal.setStateProvince(request.getParameter("stateProvince"));
}
		if(paramMap.containsKey("country"))  {
returnVal.setCountry(request.getParameter("country"));
}
		if(paramMap.containsKey("serialNumber"))  {
returnVal.setSerialNumber(request.getParameter("serialNumber"));
}
return returnVal;

}
}
