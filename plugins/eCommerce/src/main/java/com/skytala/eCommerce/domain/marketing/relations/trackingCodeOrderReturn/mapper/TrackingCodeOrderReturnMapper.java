package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.model.TrackingCodeOrderReturn;

public class TrackingCodeOrderReturnMapper  {


	public static Map<String, Object> map(TrackingCodeOrderReturn trackingcodeorderreturn) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(trackingcodeorderreturn.getReturnId() != null ){
			returnVal.put("returnId",trackingcodeorderreturn.getReturnId());
}

		if(trackingcodeorderreturn.getOrderId() != null ){
			returnVal.put("orderId",trackingcodeorderreturn.getOrderId());
}

		if(trackingcodeorderreturn.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",trackingcodeorderreturn.getOrderItemSeqId());
}

		if(trackingcodeorderreturn.getTrackingCodeTypeId() != null ){
			returnVal.put("trackingCodeTypeId",trackingcodeorderreturn.getTrackingCodeTypeId());
}

		if(trackingcodeorderreturn.getTrackingCodeId() != null ){
			returnVal.put("trackingCodeId",trackingcodeorderreturn.getTrackingCodeId());
}

		if(trackingcodeorderreturn.getIsBillable() != null ){
			returnVal.put("isBillable",trackingcodeorderreturn.getIsBillable());
}

		if(trackingcodeorderreturn.getSiteId() != null ){
			returnVal.put("siteId",trackingcodeorderreturn.getSiteId());
}

		if(trackingcodeorderreturn.getHasExported() != null ){
			returnVal.put("hasExported",trackingcodeorderreturn.getHasExported());
}

		if(trackingcodeorderreturn.getAffiliateReferredTimeStamp() != null ){
			returnVal.put("affiliateReferredTimeStamp",trackingcodeorderreturn.getAffiliateReferredTimeStamp());
}

		return returnVal;
}


	public static TrackingCodeOrderReturn map(Map<String, Object> fields) {

		TrackingCodeOrderReturn returnVal = new TrackingCodeOrderReturn();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("trackingCodeTypeId") != null) {
			returnVal.setTrackingCodeTypeId((String) fields.get("trackingCodeTypeId"));
}

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("isBillable") != null) {
			returnVal.setIsBillable((boolean) fields.get("isBillable"));
}

		if(fields.get("siteId") != null) {
			returnVal.setSiteId((String) fields.get("siteId"));
}

		if(fields.get("hasExported") != null) {
			returnVal.setHasExported((boolean) fields.get("hasExported"));
}

		if(fields.get("affiliateReferredTimeStamp") != null) {
			returnVal.setAffiliateReferredTimeStamp((Timestamp) fields.get("affiliateReferredTimeStamp"));
}


		return returnVal;
 } 
	public static TrackingCodeOrderReturn mapstrstr(Map<String, String> fields) throws Exception {

		TrackingCodeOrderReturn returnVal = new TrackingCodeOrderReturn();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("trackingCodeTypeId") != null) {
			returnVal.setTrackingCodeTypeId((String) fields.get("trackingCodeTypeId"));
}

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("isBillable") != null) {
String buf;
buf = fields.get("isBillable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsBillable(ibuf);
}

		if(fields.get("siteId") != null) {
			returnVal.setSiteId((String) fields.get("siteId"));
}

		if(fields.get("hasExported") != null) {
String buf;
buf = fields.get("hasExported");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasExported(ibuf);
}

		if(fields.get("affiliateReferredTimeStamp") != null) {
String buf = fields.get("affiliateReferredTimeStamp");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAffiliateReferredTimeStamp(ibuf);
}


		return returnVal;
 } 
	public static TrackingCodeOrderReturn map(GenericValue val) {

TrackingCodeOrderReturn returnVal = new TrackingCodeOrderReturn();
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setTrackingCodeTypeId(val.getString("trackingCodeTypeId"));
		returnVal.setTrackingCodeId(val.getString("trackingCodeId"));
		returnVal.setIsBillable(val.getBoolean("isBillable"));
		returnVal.setSiteId(val.getString("siteId"));
		returnVal.setHasExported(val.getBoolean("hasExported"));
		returnVal.setAffiliateReferredTimeStamp(val.getTimestamp("affiliateReferredTimeStamp"));


return returnVal;

}

public static TrackingCodeOrderReturn map(HttpServletRequest request) throws Exception {

		TrackingCodeOrderReturn returnVal = new TrackingCodeOrderReturn();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnId")) {
returnVal.setReturnId(request.getParameter("returnId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("trackingCodeTypeId"))  {
returnVal.setTrackingCodeTypeId(request.getParameter("trackingCodeTypeId"));
}
		if(paramMap.containsKey("trackingCodeId"))  {
returnVal.setTrackingCodeId(request.getParameter("trackingCodeId"));
}
		if(paramMap.containsKey("isBillable"))  {
String buf = request.getParameter("isBillable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsBillable(ibuf);
}
		if(paramMap.containsKey("siteId"))  {
returnVal.setSiteId(request.getParameter("siteId"));
}
		if(paramMap.containsKey("hasExported"))  {
String buf = request.getParameter("hasExported");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasExported(ibuf);
}
		if(paramMap.containsKey("affiliateReferredTimeStamp"))  {
String buf = request.getParameter("affiliateReferredTimeStamp");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAffiliateReferredTimeStamp(ibuf);
}
return returnVal;

}
}
