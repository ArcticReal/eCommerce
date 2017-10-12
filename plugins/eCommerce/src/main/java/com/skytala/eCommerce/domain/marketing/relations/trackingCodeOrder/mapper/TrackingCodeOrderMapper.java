package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;

public class TrackingCodeOrderMapper  {


	public static Map<String, Object> map(TrackingCodeOrder trackingcodeorder) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(trackingcodeorder.getOrderId() != null ){
			returnVal.put("orderId",trackingcodeorder.getOrderId());
}

		if(trackingcodeorder.getTrackingCodeTypeId() != null ){
			returnVal.put("trackingCodeTypeId",trackingcodeorder.getTrackingCodeTypeId());
}

		if(trackingcodeorder.getTrackingCodeId() != null ){
			returnVal.put("trackingCodeId",trackingcodeorder.getTrackingCodeId());
}

		if(trackingcodeorder.getIsBillable() != null ){
			returnVal.put("isBillable",trackingcodeorder.getIsBillable());
}

		if(trackingcodeorder.getSiteId() != null ){
			returnVal.put("siteId",trackingcodeorder.getSiteId());
}

		if(trackingcodeorder.getHasExported() != null ){
			returnVal.put("hasExported",trackingcodeorder.getHasExported());
}

		if(trackingcodeorder.getAffiliateReferredTimeStamp() != null ){
			returnVal.put("affiliateReferredTimeStamp",trackingcodeorder.getAffiliateReferredTimeStamp());
}

		return returnVal;
}


	public static TrackingCodeOrder map(Map<String, Object> fields) {

		TrackingCodeOrder returnVal = new TrackingCodeOrder();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
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
	public static TrackingCodeOrder mapstrstr(Map<String, String> fields) throws Exception {

		TrackingCodeOrder returnVal = new TrackingCodeOrder();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
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
	public static TrackingCodeOrder map(GenericValue val) {

TrackingCodeOrder returnVal = new TrackingCodeOrder();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setTrackingCodeTypeId(val.getString("trackingCodeTypeId"));
		returnVal.setTrackingCodeId(val.getString("trackingCodeId"));
		returnVal.setIsBillable(val.getBoolean("isBillable"));
		returnVal.setSiteId(val.getString("siteId"));
		returnVal.setHasExported(val.getBoolean("hasExported"));
		returnVal.setAffiliateReferredTimeStamp(val.getTimestamp("affiliateReferredTimeStamp"));


return returnVal;

}

public static TrackingCodeOrder map(HttpServletRequest request) throws Exception {

		TrackingCodeOrder returnVal = new TrackingCodeOrder();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
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
