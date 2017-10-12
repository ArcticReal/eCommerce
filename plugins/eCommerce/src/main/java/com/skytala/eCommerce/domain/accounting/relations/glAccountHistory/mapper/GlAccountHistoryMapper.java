package com.skytala.eCommerce.domain.accounting.relations.glAccountHistory.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountHistory.model.GlAccountHistory;

public class GlAccountHistoryMapper  {


	public static Map<String, Object> map(GlAccountHistory glaccounthistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccounthistory.getGlAccountId() != null ){
			returnVal.put("glAccountId",glaccounthistory.getGlAccountId());
}

		if(glaccounthistory.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",glaccounthistory.getOrganizationPartyId());
}

		if(glaccounthistory.getCustomTimePeriodId() != null ){
			returnVal.put("customTimePeriodId",glaccounthistory.getCustomTimePeriodId());
}

		if(glaccounthistory.getOpeningBalance() != null ){
			returnVal.put("openingBalance",glaccounthistory.getOpeningBalance());
}

		if(glaccounthistory.getPostedDebits() != null ){
			returnVal.put("postedDebits",glaccounthistory.getPostedDebits());
}

		if(glaccounthistory.getPostedCredits() != null ){
			returnVal.put("postedCredits",glaccounthistory.getPostedCredits());
}

		if(glaccounthistory.getEndingBalance() != null ){
			returnVal.put("endingBalance",glaccounthistory.getEndingBalance());
}

		return returnVal;
}


	public static GlAccountHistory map(Map<String, Object> fields) {

		GlAccountHistory returnVal = new GlAccountHistory();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("customTimePeriodId") != null) {
			returnVal.setCustomTimePeriodId((String) fields.get("customTimePeriodId"));
}

		if(fields.get("openingBalance") != null) {
			returnVal.setOpeningBalance((BigDecimal) fields.get("openingBalance"));
}

		if(fields.get("postedDebits") != null) {
			returnVal.setPostedDebits((BigDecimal) fields.get("postedDebits"));
}

		if(fields.get("postedCredits") != null) {
			returnVal.setPostedCredits((BigDecimal) fields.get("postedCredits"));
}

		if(fields.get("endingBalance") != null) {
			returnVal.setEndingBalance((BigDecimal) fields.get("endingBalance"));
}


		return returnVal;
 } 
	public static GlAccountHistory mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountHistory returnVal = new GlAccountHistory();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("customTimePeriodId") != null) {
			returnVal.setCustomTimePeriodId((String) fields.get("customTimePeriodId"));
}

		if(fields.get("openingBalance") != null) {
String buf;
buf = fields.get("openingBalance");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOpeningBalance(bd);
}

		if(fields.get("postedDebits") != null) {
String buf;
buf = fields.get("postedDebits");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPostedDebits(bd);
}

		if(fields.get("postedCredits") != null) {
String buf;
buf = fields.get("postedCredits");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPostedCredits(bd);
}

		if(fields.get("endingBalance") != null) {
String buf;
buf = fields.get("endingBalance");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEndingBalance(bd);
}


		return returnVal;
 } 
	public static GlAccountHistory map(GenericValue val) {

GlAccountHistory returnVal = new GlAccountHistory();
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setCustomTimePeriodId(val.getString("customTimePeriodId"));
		returnVal.setOpeningBalance(val.getBigDecimal("openingBalance"));
		returnVal.setPostedDebits(val.getBigDecimal("postedDebits"));
		returnVal.setPostedCredits(val.getBigDecimal("postedCredits"));
		returnVal.setEndingBalance(val.getBigDecimal("endingBalance"));


return returnVal;

}

public static GlAccountHistory map(HttpServletRequest request) throws Exception {

		GlAccountHistory returnVal = new GlAccountHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountId")) {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("customTimePeriodId"))  {
returnVal.setCustomTimePeriodId(request.getParameter("customTimePeriodId"));
}
		if(paramMap.containsKey("openingBalance"))  {
String buf = request.getParameter("openingBalance");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOpeningBalance(bd);
}
		if(paramMap.containsKey("postedDebits"))  {
String buf = request.getParameter("postedDebits");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPostedDebits(bd);
}
		if(paramMap.containsKey("postedCredits"))  {
String buf = request.getParameter("postedCredits");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPostedCredits(bd);
}
		if(paramMap.containsKey("endingBalance"))  {
String buf = request.getParameter("endingBalance");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEndingBalance(bd);
}
return returnVal;

}
}
