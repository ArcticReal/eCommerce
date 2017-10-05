package com.skytala.eCommerce.domain.trackingCode.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.trackingCode.model.TrackingCode;

public class TrackingCodeMapper  {


	public static Map<String, Object> map(TrackingCode trackingcode) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(trackingcode.getTrackingCodeId() != null ){
			returnVal.put("trackingCodeId",trackingcode.getTrackingCodeId());
}

		if(trackingcode.getTrackingCodeTypeId() != null ){
			returnVal.put("trackingCodeTypeId",trackingcode.getTrackingCodeTypeId());
}

		if(trackingcode.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",trackingcode.getMarketingCampaignId());
}

		if(trackingcode.getRedirectUrl() != null ){
			returnVal.put("redirectUrl",trackingcode.getRedirectUrl());
}

		if(trackingcode.getOverrideLogo() != null ){
			returnVal.put("overrideLogo",trackingcode.getOverrideLogo());
}

		if(trackingcode.getOverrideCss() != null ){
			returnVal.put("overrideCss",trackingcode.getOverrideCss());
}

		if(trackingcode.getProdCatalogId() != null ){
			returnVal.put("prodCatalogId",trackingcode.getProdCatalogId());
}

		if(trackingcode.getComments() != null ){
			returnVal.put("comments",trackingcode.getComments());
}

		if(trackingcode.getDescription() != null ){
			returnVal.put("description",trackingcode.getDescription());
}

		if(trackingcode.getTrackableLifetime() != null ){
			returnVal.put("trackableLifetime",trackingcode.getTrackableLifetime());
}

		if(trackingcode.getBillableLifetime() != null ){
			returnVal.put("billableLifetime",trackingcode.getBillableLifetime());
}

		if(trackingcode.getFromDate() != null ){
			returnVal.put("fromDate",trackingcode.getFromDate());
}

		if(trackingcode.getThruDate() != null ){
			returnVal.put("thruDate",trackingcode.getThruDate());
}

		if(trackingcode.getGroupId() != null ){
			returnVal.put("groupId",trackingcode.getGroupId());
}

		if(trackingcode.getSubgroupId() != null ){
			returnVal.put("subgroupId",trackingcode.getSubgroupId());
}

		if(trackingcode.getCreatedDate() != null ){
			returnVal.put("createdDate",trackingcode.getCreatedDate());
}

		if(trackingcode.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",trackingcode.getCreatedByUserLogin());
}

		if(trackingcode.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",trackingcode.getLastModifiedDate());
}

		if(trackingcode.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",trackingcode.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static TrackingCode map(Map<String, Object> fields) {

		TrackingCode returnVal = new TrackingCode();

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("trackingCodeTypeId") != null) {
			returnVal.setTrackingCodeTypeId((String) fields.get("trackingCodeTypeId"));
}

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("overrideLogo") != null) {
			returnVal.setOverrideLogo((String) fields.get("overrideLogo"));
}

		if(fields.get("overrideCss") != null) {
			returnVal.setOverrideCss((String) fields.get("overrideCss"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("trackableLifetime") != null) {
			returnVal.setTrackableLifetime((long) fields.get("trackableLifetime"));
}

		if(fields.get("billableLifetime") != null) {
			returnVal.setBillableLifetime((long) fields.get("billableLifetime"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("subgroupId") != null) {
			returnVal.setSubgroupId((String) fields.get("subgroupId"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static TrackingCode mapstrstr(Map<String, String> fields) throws Exception {

		TrackingCode returnVal = new TrackingCode();

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("trackingCodeTypeId") != null) {
			returnVal.setTrackingCodeTypeId((String) fields.get("trackingCodeTypeId"));
}

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("overrideLogo") != null) {
			returnVal.setOverrideLogo((String) fields.get("overrideLogo"));
}

		if(fields.get("overrideCss") != null) {
			returnVal.setOverrideCss((String) fields.get("overrideCss"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("trackableLifetime") != null) {
String buf;
buf = fields.get("trackableLifetime");
long ibuf = Long.parseLong(buf);
			returnVal.setTrackableLifetime(ibuf);
}

		if(fields.get("billableLifetime") != null) {
String buf;
buf = fields.get("billableLifetime");
long ibuf = Long.parseLong(buf);
			returnVal.setBillableLifetime(ibuf);
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

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("subgroupId") != null) {
			returnVal.setSubgroupId((String) fields.get("subgroupId"));
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static TrackingCode map(GenericValue val) {

TrackingCode returnVal = new TrackingCode();
		returnVal.setTrackingCodeId(val.getString("trackingCodeId"));
		returnVal.setTrackingCodeTypeId(val.getString("trackingCodeTypeId"));
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setRedirectUrl(val.getString("redirectUrl"));
		returnVal.setOverrideLogo(val.getString("overrideLogo"));
		returnVal.setOverrideCss(val.getString("overrideCss"));
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setTrackableLifetime(val.getLong("trackableLifetime"));
		returnVal.setBillableLifetime(val.getLong("billableLifetime"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setGroupId(val.getString("groupId"));
		returnVal.setSubgroupId(val.getString("subgroupId"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static TrackingCode map(HttpServletRequest request) throws Exception {

		TrackingCode returnVal = new TrackingCode();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("trackingCodeId")) {
returnVal.setTrackingCodeId(request.getParameter("trackingCodeId"));
}

		if(paramMap.containsKey("trackingCodeTypeId"))  {
returnVal.setTrackingCodeTypeId(request.getParameter("trackingCodeTypeId"));
}
		if(paramMap.containsKey("marketingCampaignId"))  {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}
		if(paramMap.containsKey("redirectUrl"))  {
returnVal.setRedirectUrl(request.getParameter("redirectUrl"));
}
		if(paramMap.containsKey("overrideLogo"))  {
returnVal.setOverrideLogo(request.getParameter("overrideLogo"));
}
		if(paramMap.containsKey("overrideCss"))  {
returnVal.setOverrideCss(request.getParameter("overrideCss"));
}
		if(paramMap.containsKey("prodCatalogId"))  {
returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("trackableLifetime"))  {
String buf = request.getParameter("trackableLifetime");
Long ibuf = Long.parseLong(buf);
returnVal.setTrackableLifetime(ibuf);
}
		if(paramMap.containsKey("billableLifetime"))  {
String buf = request.getParameter("billableLifetime");
Long ibuf = Long.parseLong(buf);
returnVal.setBillableLifetime(ibuf);
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
		if(paramMap.containsKey("groupId"))  {
returnVal.setGroupId(request.getParameter("groupId"));
}
		if(paramMap.containsKey("subgroupId"))  {
returnVal.setSubgroupId(request.getParameter("subgroupId"));
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
