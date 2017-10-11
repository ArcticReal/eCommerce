package com.skytala.eCommerce.domain.party.relations.postalAddress.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;

public class PostalAddressMapper  {


	public static Map<String, Object> map(PostalAddress postaladdress) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(postaladdress.getContactMechId() != null ){
			returnVal.put("contactMechId",postaladdress.getContactMechId());
}

		if(postaladdress.getToName() != null ){
			returnVal.put("toName",postaladdress.getToName());
}

		if(postaladdress.getAttnName() != null ){
			returnVal.put("attnName",postaladdress.getAttnName());
}

		if(postaladdress.getAddress1() != null ){
			returnVal.put("address1",postaladdress.getAddress1());
}

		if(postaladdress.getAddress2() != null ){
			returnVal.put("address2",postaladdress.getAddress2());
}

		if(postaladdress.getHouseNumber() != null ){
			returnVal.put("houseNumber",postaladdress.getHouseNumber());
}

		if(postaladdress.getHouseNumberExt() != null ){
			returnVal.put("houseNumberExt",postaladdress.getHouseNumberExt());
}

		if(postaladdress.getDirections() != null ){
			returnVal.put("directions",postaladdress.getDirections());
}

		if(postaladdress.getCity() != null ){
			returnVal.put("city",postaladdress.getCity());
}

		if(postaladdress.getCityGeoId() != null ){
			returnVal.put("cityGeoId",postaladdress.getCityGeoId());
}

		if(postaladdress.getPostalCode() != null ){
			returnVal.put("postalCode",postaladdress.getPostalCode());
}

		if(postaladdress.getPostalCodeExt() != null ){
			returnVal.put("postalCodeExt",postaladdress.getPostalCodeExt());
}

		if(postaladdress.getCountryGeoId() != null ){
			returnVal.put("countryGeoId",postaladdress.getCountryGeoId());
}

		if(postaladdress.getStateProvinceGeoId() != null ){
			returnVal.put("stateProvinceGeoId",postaladdress.getStateProvinceGeoId());
}

		if(postaladdress.getCountyGeoId() != null ){
			returnVal.put("countyGeoId",postaladdress.getCountyGeoId());
}

		if(postaladdress.getMunicipalityGeoId() != null ){
			returnVal.put("municipalityGeoId",postaladdress.getMunicipalityGeoId());
}

		if(postaladdress.getPostalCodeGeoId() != null ){
			returnVal.put("postalCodeGeoId",postaladdress.getPostalCodeGeoId());
}

		if(postaladdress.getGeoPointId() != null ){
			returnVal.put("geoPointId",postaladdress.getGeoPointId());
}

		return returnVal;
}


	public static PostalAddress map(Map<String, Object> fields) {

		PostalAddress returnVal = new PostalAddress();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("toName") != null) {
			returnVal.setToName((String) fields.get("toName"));
}

		if(fields.get("attnName") != null) {
			returnVal.setAttnName((String) fields.get("attnName"));
}

		if(fields.get("address1") != null) {
			returnVal.setAddress1((String) fields.get("address1"));
}

		if(fields.get("address2") != null) {
			returnVal.setAddress2((String) fields.get("address2"));
}

		if(fields.get("houseNumber") != null) {
			returnVal.setHouseNumber((long) fields.get("houseNumber"));
}

		if(fields.get("houseNumberExt") != null) {
			returnVal.setHouseNumberExt((String) fields.get("houseNumberExt"));
}

		if(fields.get("directions") != null) {
			returnVal.setDirections((String) fields.get("directions"));
}

		if(fields.get("city") != null) {
			returnVal.setCity((String) fields.get("city"));
}

		if(fields.get("cityGeoId") != null) {
			returnVal.setCityGeoId((String) fields.get("cityGeoId"));
}

		if(fields.get("postalCode") != null) {
			returnVal.setPostalCode((String) fields.get("postalCode"));
}

		if(fields.get("postalCodeExt") != null) {
			returnVal.setPostalCodeExt((String) fields.get("postalCodeExt"));
}

		if(fields.get("countryGeoId") != null) {
			returnVal.setCountryGeoId((String) fields.get("countryGeoId"));
}

		if(fields.get("stateProvinceGeoId") != null) {
			returnVal.setStateProvinceGeoId((String) fields.get("stateProvinceGeoId"));
}

		if(fields.get("countyGeoId") != null) {
			returnVal.setCountyGeoId((String) fields.get("countyGeoId"));
}

		if(fields.get("municipalityGeoId") != null) {
			returnVal.setMunicipalityGeoId((String) fields.get("municipalityGeoId"));
}

		if(fields.get("postalCodeGeoId") != null) {
			returnVal.setPostalCodeGeoId((String) fields.get("postalCodeGeoId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}


		return returnVal;
 } 
	public static PostalAddress mapstrstr(Map<String, String> fields) throws Exception {

		PostalAddress returnVal = new PostalAddress();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("toName") != null) {
			returnVal.setToName((String) fields.get("toName"));
}

		if(fields.get("attnName") != null) {
			returnVal.setAttnName((String) fields.get("attnName"));
}

		if(fields.get("address1") != null) {
			returnVal.setAddress1((String) fields.get("address1"));
}

		if(fields.get("address2") != null) {
			returnVal.setAddress2((String) fields.get("address2"));
}

		if(fields.get("houseNumber") != null) {
String buf;
buf = fields.get("houseNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setHouseNumber(ibuf);
}

		if(fields.get("houseNumberExt") != null) {
			returnVal.setHouseNumberExt((String) fields.get("houseNumberExt"));
}

		if(fields.get("directions") != null) {
			returnVal.setDirections((String) fields.get("directions"));
}

		if(fields.get("city") != null) {
			returnVal.setCity((String) fields.get("city"));
}

		if(fields.get("cityGeoId") != null) {
			returnVal.setCityGeoId((String) fields.get("cityGeoId"));
}

		if(fields.get("postalCode") != null) {
			returnVal.setPostalCode((String) fields.get("postalCode"));
}

		if(fields.get("postalCodeExt") != null) {
			returnVal.setPostalCodeExt((String) fields.get("postalCodeExt"));
}

		if(fields.get("countryGeoId") != null) {
			returnVal.setCountryGeoId((String) fields.get("countryGeoId"));
}

		if(fields.get("stateProvinceGeoId") != null) {
			returnVal.setStateProvinceGeoId((String) fields.get("stateProvinceGeoId"));
}

		if(fields.get("countyGeoId") != null) {
			returnVal.setCountyGeoId((String) fields.get("countyGeoId"));
}

		if(fields.get("municipalityGeoId") != null) {
			returnVal.setMunicipalityGeoId((String) fields.get("municipalityGeoId"));
}

		if(fields.get("postalCodeGeoId") != null) {
			returnVal.setPostalCodeGeoId((String) fields.get("postalCodeGeoId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}


		return returnVal;
 } 
	public static PostalAddress map(GenericValue val) {

PostalAddress returnVal = new PostalAddress();
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setToName(val.getString("toName"));
		returnVal.setAttnName(val.getString("attnName"));
		returnVal.setAddress1(val.getString("address1"));
		returnVal.setAddress2(val.getString("address2"));
		returnVal.setHouseNumber(val.getLong("houseNumber"));
		returnVal.setHouseNumberExt(val.getString("houseNumberExt"));
		returnVal.setDirections(val.getString("directions"));
		returnVal.setCity(val.getString("city"));
		returnVal.setCityGeoId(val.getString("cityGeoId"));
		returnVal.setPostalCode(val.getString("postalCode"));
		returnVal.setPostalCodeExt(val.getString("postalCodeExt"));
		returnVal.setCountryGeoId(val.getString("countryGeoId"));
		returnVal.setStateProvinceGeoId(val.getString("stateProvinceGeoId"));
		returnVal.setCountyGeoId(val.getString("countyGeoId"));
		returnVal.setMunicipalityGeoId(val.getString("municipalityGeoId"));
		returnVal.setPostalCodeGeoId(val.getString("postalCodeGeoId"));
		returnVal.setGeoPointId(val.getString("geoPointId"));


return returnVal;

}

public static PostalAddress map(HttpServletRequest request) throws Exception {

		PostalAddress returnVal = new PostalAddress();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechId")) {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}

		if(paramMap.containsKey("toName"))  {
returnVal.setToName(request.getParameter("toName"));
}
		if(paramMap.containsKey("attnName"))  {
returnVal.setAttnName(request.getParameter("attnName"));
}
		if(paramMap.containsKey("address1"))  {
returnVal.setAddress1(request.getParameter("address1"));
}
		if(paramMap.containsKey("address2"))  {
returnVal.setAddress2(request.getParameter("address2"));
}
		if(paramMap.containsKey("houseNumber"))  {
String buf = request.getParameter("houseNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setHouseNumber(ibuf);
}
		if(paramMap.containsKey("houseNumberExt"))  {
returnVal.setHouseNumberExt(request.getParameter("houseNumberExt"));
}
		if(paramMap.containsKey("directions"))  {
returnVal.setDirections(request.getParameter("directions"));
}
		if(paramMap.containsKey("city"))  {
returnVal.setCity(request.getParameter("city"));
}
		if(paramMap.containsKey("cityGeoId"))  {
returnVal.setCityGeoId(request.getParameter("cityGeoId"));
}
		if(paramMap.containsKey("postalCode"))  {
returnVal.setPostalCode(request.getParameter("postalCode"));
}
		if(paramMap.containsKey("postalCodeExt"))  {
returnVal.setPostalCodeExt(request.getParameter("postalCodeExt"));
}
		if(paramMap.containsKey("countryGeoId"))  {
returnVal.setCountryGeoId(request.getParameter("countryGeoId"));
}
		if(paramMap.containsKey("stateProvinceGeoId"))  {
returnVal.setStateProvinceGeoId(request.getParameter("stateProvinceGeoId"));
}
		if(paramMap.containsKey("countyGeoId"))  {
returnVal.setCountyGeoId(request.getParameter("countyGeoId"));
}
		if(paramMap.containsKey("municipalityGeoId"))  {
returnVal.setMunicipalityGeoId(request.getParameter("municipalityGeoId"));
}
		if(paramMap.containsKey("postalCodeGeoId"))  {
returnVal.setPostalCodeGeoId(request.getParameter("postalCodeGeoId"));
}
		if(paramMap.containsKey("geoPointId"))  {
returnVal.setGeoPointId(request.getParameter("geoPointId"));
}
return returnVal;

}
}
