package com.skytala.eCommerce.domain.party.relations.affiliate.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;

public class AffiliateMapper  {


	public static Map<String, Object> map(Affiliate affiliate) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(affiliate.getPartyId() != null ){
			returnVal.put("partyId",affiliate.getPartyId());
}

		if(affiliate.getAffiliateName() != null ){
			returnVal.put("affiliateName",affiliate.getAffiliateName());
}

		if(affiliate.getAffiliateDescription() != null ){
			returnVal.put("affiliateDescription",affiliate.getAffiliateDescription());
}

		if(affiliate.getYearEstablished() != null ){
			returnVal.put("yearEstablished",affiliate.getYearEstablished());
}

		if(affiliate.getSiteType() != null ){
			returnVal.put("siteType",affiliate.getSiteType());
}

		if(affiliate.getSitePageViews() != null ){
			returnVal.put("sitePageViews",affiliate.getSitePageViews());
}

		if(affiliate.getSiteVisitors() != null ){
			returnVal.put("siteVisitors",affiliate.getSiteVisitors());
}

		if(affiliate.getDateTimeCreated() != null ){
			returnVal.put("dateTimeCreated",affiliate.getDateTimeCreated());
}

		if(affiliate.getDateTimeApproved() != null ){
			returnVal.put("dateTimeApproved",affiliate.getDateTimeApproved());
}

		return returnVal;
}


	public static Affiliate map(Map<String, Object> fields) {

		Affiliate returnVal = new Affiliate();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("affiliateName") != null) {
			returnVal.setAffiliateName((String) fields.get("affiliateName"));
}

		if(fields.get("affiliateDescription") != null) {
			returnVal.setAffiliateDescription((String) fields.get("affiliateDescription"));
}

		if(fields.get("yearEstablished") != null) {
			returnVal.setYearEstablished((String) fields.get("yearEstablished"));
}

		if(fields.get("siteType") != null) {
			returnVal.setSiteType((String) fields.get("siteType"));
}

		if(fields.get("sitePageViews") != null) {
			returnVal.setSitePageViews((String) fields.get("sitePageViews"));
}

		if(fields.get("siteVisitors") != null) {
			returnVal.setSiteVisitors((String) fields.get("siteVisitors"));
}

		if(fields.get("dateTimeCreated") != null) {
			returnVal.setDateTimeCreated((Timestamp) fields.get("dateTimeCreated"));
}

		if(fields.get("dateTimeApproved") != null) {
			returnVal.setDateTimeApproved((Timestamp) fields.get("dateTimeApproved"));
}


		return returnVal;
 } 
	public static Affiliate mapstrstr(Map<String, String> fields) throws Exception {

		Affiliate returnVal = new Affiliate();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("affiliateName") != null) {
			returnVal.setAffiliateName((String) fields.get("affiliateName"));
}

		if(fields.get("affiliateDescription") != null) {
			returnVal.setAffiliateDescription((String) fields.get("affiliateDescription"));
}

		if(fields.get("yearEstablished") != null) {
			returnVal.setYearEstablished((String) fields.get("yearEstablished"));
}

		if(fields.get("siteType") != null) {
			returnVal.setSiteType((String) fields.get("siteType"));
}

		if(fields.get("sitePageViews") != null) {
			returnVal.setSitePageViews((String) fields.get("sitePageViews"));
}

		if(fields.get("siteVisitors") != null) {
			returnVal.setSiteVisitors((String) fields.get("siteVisitors"));
}

		if(fields.get("dateTimeCreated") != null) {
String buf = fields.get("dateTimeCreated");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateTimeCreated(ibuf);
}

		if(fields.get("dateTimeApproved") != null) {
String buf = fields.get("dateTimeApproved");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateTimeApproved(ibuf);
}


		return returnVal;
 } 
	public static Affiliate map(GenericValue val) {

Affiliate returnVal = new Affiliate();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setAffiliateName(val.getString("affiliateName"));
		returnVal.setAffiliateDescription(val.getString("affiliateDescription"));
		returnVal.setYearEstablished(val.getString("yearEstablished"));
		returnVal.setSiteType(val.getString("siteType"));
		returnVal.setSitePageViews(val.getString("sitePageViews"));
		returnVal.setSiteVisitors(val.getString("siteVisitors"));
		returnVal.setDateTimeCreated(val.getTimestamp("dateTimeCreated"));
		returnVal.setDateTimeApproved(val.getTimestamp("dateTimeApproved"));


return returnVal;

}

public static Affiliate map(HttpServletRequest request) throws Exception {

		Affiliate returnVal = new Affiliate();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("affiliateName"))  {
returnVal.setAffiliateName(request.getParameter("affiliateName"));
}
		if(paramMap.containsKey("affiliateDescription"))  {
returnVal.setAffiliateDescription(request.getParameter("affiliateDescription"));
}
		if(paramMap.containsKey("yearEstablished"))  {
returnVal.setYearEstablished(request.getParameter("yearEstablished"));
}
		if(paramMap.containsKey("siteType"))  {
returnVal.setSiteType(request.getParameter("siteType"));
}
		if(paramMap.containsKey("sitePageViews"))  {
returnVal.setSitePageViews(request.getParameter("sitePageViews"));
}
		if(paramMap.containsKey("siteVisitors"))  {
returnVal.setSiteVisitors(request.getParameter("siteVisitors"));
}
		if(paramMap.containsKey("dateTimeCreated"))  {
String buf = request.getParameter("dateTimeCreated");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateTimeCreated(ibuf);
}
		if(paramMap.containsKey("dateTimeApproved"))  {
String buf = request.getParameter("dateTimeApproved");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateTimeApproved(ibuf);
}
return returnVal;

}
}
