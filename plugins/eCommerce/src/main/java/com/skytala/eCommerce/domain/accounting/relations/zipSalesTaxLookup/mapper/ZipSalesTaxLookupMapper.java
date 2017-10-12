package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model.ZipSalesTaxLookup;

public class ZipSalesTaxLookupMapper  {


	public static Map<String, Object> map(ZipSalesTaxLookup zipsalestaxlookup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(zipsalestaxlookup.getZipCode() != null ){
			returnVal.put("zipCode",zipsalestaxlookup.getZipCode());
}

		if(zipsalestaxlookup.getStateCode() != null ){
			returnVal.put("stateCode",zipsalestaxlookup.getStateCode());
}

		if(zipsalestaxlookup.getCity() != null ){
			returnVal.put("city",zipsalestaxlookup.getCity());
}

		if(zipsalestaxlookup.getCounty() != null ){
			returnVal.put("county",zipsalestaxlookup.getCounty());
}

		if(zipsalestaxlookup.getFromDate() != null ){
			returnVal.put("fromDate",zipsalestaxlookup.getFromDate());
}

		if(zipsalestaxlookup.getCountyFips() != null ){
			returnVal.put("countyFips",zipsalestaxlookup.getCountyFips());
}

		if(zipsalestaxlookup.getCountyDefault() != null ){
			returnVal.put("countyDefault",zipsalestaxlookup.getCountyDefault());
}

		if(zipsalestaxlookup.getGeneralDefault() != null ){
			returnVal.put("generalDefault",zipsalestaxlookup.getGeneralDefault());
}

		if(zipsalestaxlookup.getInsideCity() != null ){
			returnVal.put("insideCity",zipsalestaxlookup.getInsideCity());
}

		if(zipsalestaxlookup.getGeoCode() != null ){
			returnVal.put("geoCode",zipsalestaxlookup.getGeoCode());
}

		if(zipsalestaxlookup.getStateSalesTax() != null ){
			returnVal.put("stateSalesTax",zipsalestaxlookup.getStateSalesTax());
}

		if(zipsalestaxlookup.getCitySalesTax() != null ){
			returnVal.put("citySalesTax",zipsalestaxlookup.getCitySalesTax());
}

		if(zipsalestaxlookup.getCityLocalSalesTax() != null ){
			returnVal.put("cityLocalSalesTax",zipsalestaxlookup.getCityLocalSalesTax());
}

		if(zipsalestaxlookup.getCountySalesTax() != null ){
			returnVal.put("countySalesTax",zipsalestaxlookup.getCountySalesTax());
}

		if(zipsalestaxlookup.getCountyLocalSalesTax() != null ){
			returnVal.put("countyLocalSalesTax",zipsalestaxlookup.getCountyLocalSalesTax());
}

		if(zipsalestaxlookup.getComboSalesTax() != null ){
			returnVal.put("comboSalesTax",zipsalestaxlookup.getComboSalesTax());
}

		if(zipsalestaxlookup.getStateUseTax() != null ){
			returnVal.put("stateUseTax",zipsalestaxlookup.getStateUseTax());
}

		if(zipsalestaxlookup.getCityUseTax() != null ){
			returnVal.put("cityUseTax",zipsalestaxlookup.getCityUseTax());
}

		if(zipsalestaxlookup.getCityLocalUseTax() != null ){
			returnVal.put("cityLocalUseTax",zipsalestaxlookup.getCityLocalUseTax());
}

		if(zipsalestaxlookup.getCountyUseTax() != null ){
			returnVal.put("countyUseTax",zipsalestaxlookup.getCountyUseTax());
}

		if(zipsalestaxlookup.getCountyLocalUseTax() != null ){
			returnVal.put("countyLocalUseTax",zipsalestaxlookup.getCountyLocalUseTax());
}

		if(zipsalestaxlookup.getComboUseTax() != null ){
			returnVal.put("comboUseTax",zipsalestaxlookup.getComboUseTax());
}

		return returnVal;
}


	public static ZipSalesTaxLookup map(Map<String, Object> fields) {

		ZipSalesTaxLookup returnVal = new ZipSalesTaxLookup();

		if(fields.get("zipCode") != null) {
			returnVal.setZipCode((String) fields.get("zipCode"));
}

		if(fields.get("stateCode") != null) {
			returnVal.setStateCode((String) fields.get("stateCode"));
}

		if(fields.get("city") != null) {
			returnVal.setCity((String) fields.get("city"));
}

		if(fields.get("county") != null) {
			returnVal.setCounty((String) fields.get("county"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("countyFips") != null) {
			returnVal.setCountyFips((String) fields.get("countyFips"));
}

		if(fields.get("countyDefault") != null) {
			returnVal.setCountyDefault((boolean) fields.get("countyDefault"));
}

		if(fields.get("generalDefault") != null) {
			returnVal.setGeneralDefault((boolean) fields.get("generalDefault"));
}

		if(fields.get("insideCity") != null) {
			returnVal.setInsideCity((boolean) fields.get("insideCity"));
}

		if(fields.get("geoCode") != null) {
			returnVal.setGeoCode((String) fields.get("geoCode"));
}

		if(fields.get("stateSalesTax") != null) {
			returnVal.setStateSalesTax((BigDecimal) fields.get("stateSalesTax"));
}

		if(fields.get("citySalesTax") != null) {
			returnVal.setCitySalesTax((BigDecimal) fields.get("citySalesTax"));
}

		if(fields.get("cityLocalSalesTax") != null) {
			returnVal.setCityLocalSalesTax((BigDecimal) fields.get("cityLocalSalesTax"));
}

		if(fields.get("countySalesTax") != null) {
			returnVal.setCountySalesTax((BigDecimal) fields.get("countySalesTax"));
}

		if(fields.get("countyLocalSalesTax") != null) {
			returnVal.setCountyLocalSalesTax((BigDecimal) fields.get("countyLocalSalesTax"));
}

		if(fields.get("comboSalesTax") != null) {
			returnVal.setComboSalesTax((BigDecimal) fields.get("comboSalesTax"));
}

		if(fields.get("stateUseTax") != null) {
			returnVal.setStateUseTax((BigDecimal) fields.get("stateUseTax"));
}

		if(fields.get("cityUseTax") != null) {
			returnVal.setCityUseTax((BigDecimal) fields.get("cityUseTax"));
}

		if(fields.get("cityLocalUseTax") != null) {
			returnVal.setCityLocalUseTax((BigDecimal) fields.get("cityLocalUseTax"));
}

		if(fields.get("countyUseTax") != null) {
			returnVal.setCountyUseTax((BigDecimal) fields.get("countyUseTax"));
}

		if(fields.get("countyLocalUseTax") != null) {
			returnVal.setCountyLocalUseTax((BigDecimal) fields.get("countyLocalUseTax"));
}

		if(fields.get("comboUseTax") != null) {
			returnVal.setComboUseTax((BigDecimal) fields.get("comboUseTax"));
}


		return returnVal;
 } 
	public static ZipSalesTaxLookup mapstrstr(Map<String, String> fields) throws Exception {

		ZipSalesTaxLookup returnVal = new ZipSalesTaxLookup();

		if(fields.get("zipCode") != null) {
			returnVal.setZipCode((String) fields.get("zipCode"));
}

		if(fields.get("stateCode") != null) {
			returnVal.setStateCode((String) fields.get("stateCode"));
}

		if(fields.get("city") != null) {
			returnVal.setCity((String) fields.get("city"));
}

		if(fields.get("county") != null) {
			returnVal.setCounty((String) fields.get("county"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("countyFips") != null) {
			returnVal.setCountyFips((String) fields.get("countyFips"));
}

		if(fields.get("countyDefault") != null) {
String buf;
buf = fields.get("countyDefault");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setCountyDefault(ibuf);
}

		if(fields.get("generalDefault") != null) {
String buf;
buf = fields.get("generalDefault");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setGeneralDefault(ibuf);
}

		if(fields.get("insideCity") != null) {
String buf;
buf = fields.get("insideCity");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setInsideCity(ibuf);
}

		if(fields.get("geoCode") != null) {
			returnVal.setGeoCode((String) fields.get("geoCode"));
}

		if(fields.get("stateSalesTax") != null) {
String buf;
buf = fields.get("stateSalesTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStateSalesTax(bd);
}

		if(fields.get("citySalesTax") != null) {
String buf;
buf = fields.get("citySalesTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCitySalesTax(bd);
}

		if(fields.get("cityLocalSalesTax") != null) {
String buf;
buf = fields.get("cityLocalSalesTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCityLocalSalesTax(bd);
}

		if(fields.get("countySalesTax") != null) {
String buf;
buf = fields.get("countySalesTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountySalesTax(bd);
}

		if(fields.get("countyLocalSalesTax") != null) {
String buf;
buf = fields.get("countyLocalSalesTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountyLocalSalesTax(bd);
}

		if(fields.get("comboSalesTax") != null) {
String buf;
buf = fields.get("comboSalesTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setComboSalesTax(bd);
}

		if(fields.get("stateUseTax") != null) {
String buf;
buf = fields.get("stateUseTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStateUseTax(bd);
}

		if(fields.get("cityUseTax") != null) {
String buf;
buf = fields.get("cityUseTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCityUseTax(bd);
}

		if(fields.get("cityLocalUseTax") != null) {
String buf;
buf = fields.get("cityLocalUseTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCityLocalUseTax(bd);
}

		if(fields.get("countyUseTax") != null) {
String buf;
buf = fields.get("countyUseTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountyUseTax(bd);
}

		if(fields.get("countyLocalUseTax") != null) {
String buf;
buf = fields.get("countyLocalUseTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountyLocalUseTax(bd);
}

		if(fields.get("comboUseTax") != null) {
String buf;
buf = fields.get("comboUseTax");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setComboUseTax(bd);
}


		return returnVal;
 } 
	public static ZipSalesTaxLookup map(GenericValue val) {

ZipSalesTaxLookup returnVal = new ZipSalesTaxLookup();
		returnVal.setZipCode(val.getString("zipCode"));
		returnVal.setStateCode(val.getString("stateCode"));
		returnVal.setCity(val.getString("city"));
		returnVal.setCounty(val.getString("county"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setCountyFips(val.getString("countyFips"));
		returnVal.setCountyDefault(val.getBoolean("countyDefault"));
		returnVal.setGeneralDefault(val.getBoolean("generalDefault"));
		returnVal.setInsideCity(val.getBoolean("insideCity"));
		returnVal.setGeoCode(val.getString("geoCode"));
		returnVal.setStateSalesTax(val.getBigDecimal("stateSalesTax"));
		returnVal.setCitySalesTax(val.getBigDecimal("citySalesTax"));
		returnVal.setCityLocalSalesTax(val.getBigDecimal("cityLocalSalesTax"));
		returnVal.setCountySalesTax(val.getBigDecimal("countySalesTax"));
		returnVal.setCountyLocalSalesTax(val.getBigDecimal("countyLocalSalesTax"));
		returnVal.setComboSalesTax(val.getBigDecimal("comboSalesTax"));
		returnVal.setStateUseTax(val.getBigDecimal("stateUseTax"));
		returnVal.setCityUseTax(val.getBigDecimal("cityUseTax"));
		returnVal.setCityLocalUseTax(val.getBigDecimal("cityLocalUseTax"));
		returnVal.setCountyUseTax(val.getBigDecimal("countyUseTax"));
		returnVal.setCountyLocalUseTax(val.getBigDecimal("countyLocalUseTax"));
		returnVal.setComboUseTax(val.getBigDecimal("comboUseTax"));


return returnVal;

}

public static ZipSalesTaxLookup map(HttpServletRequest request) throws Exception {

		ZipSalesTaxLookup returnVal = new ZipSalesTaxLookup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("zipCode")) {
returnVal.setZipCode(request.getParameter("zipCode"));
}

		if(paramMap.containsKey("stateCode"))  {
returnVal.setStateCode(request.getParameter("stateCode"));
}
		if(paramMap.containsKey("city"))  {
returnVal.setCity(request.getParameter("city"));
}
		if(paramMap.containsKey("county"))  {
returnVal.setCounty(request.getParameter("county"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("countyFips"))  {
returnVal.setCountyFips(request.getParameter("countyFips"));
}
		if(paramMap.containsKey("countyDefault"))  {
String buf = request.getParameter("countyDefault");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setCountyDefault(ibuf);
}
		if(paramMap.containsKey("generalDefault"))  {
String buf = request.getParameter("generalDefault");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setGeneralDefault(ibuf);
}
		if(paramMap.containsKey("insideCity"))  {
String buf = request.getParameter("insideCity");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setInsideCity(ibuf);
}
		if(paramMap.containsKey("geoCode"))  {
returnVal.setGeoCode(request.getParameter("geoCode"));
}
		if(paramMap.containsKey("stateSalesTax"))  {
String buf = request.getParameter("stateSalesTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStateSalesTax(bd);
}
		if(paramMap.containsKey("citySalesTax"))  {
String buf = request.getParameter("citySalesTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCitySalesTax(bd);
}
		if(paramMap.containsKey("cityLocalSalesTax"))  {
String buf = request.getParameter("cityLocalSalesTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCityLocalSalesTax(bd);
}
		if(paramMap.containsKey("countySalesTax"))  {
String buf = request.getParameter("countySalesTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountySalesTax(bd);
}
		if(paramMap.containsKey("countyLocalSalesTax"))  {
String buf = request.getParameter("countyLocalSalesTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountyLocalSalesTax(bd);
}
		if(paramMap.containsKey("comboSalesTax"))  {
String buf = request.getParameter("comboSalesTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setComboSalesTax(bd);
}
		if(paramMap.containsKey("stateUseTax"))  {
String buf = request.getParameter("stateUseTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStateUseTax(bd);
}
		if(paramMap.containsKey("cityUseTax"))  {
String buf = request.getParameter("cityUseTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCityUseTax(bd);
}
		if(paramMap.containsKey("cityLocalUseTax"))  {
String buf = request.getParameter("cityLocalUseTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCityLocalUseTax(bd);
}
		if(paramMap.containsKey("countyUseTax"))  {
String buf = request.getParameter("countyUseTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountyUseTax(bd);
}
		if(paramMap.containsKey("countyLocalUseTax"))  {
String buf = request.getParameter("countyLocalUseTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCountyLocalUseTax(bd);
}
		if(paramMap.containsKey("comboUseTax"))  {
String buf = request.getParameter("comboUseTax");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setComboUseTax(bd);
}
return returnVal;

}
}
