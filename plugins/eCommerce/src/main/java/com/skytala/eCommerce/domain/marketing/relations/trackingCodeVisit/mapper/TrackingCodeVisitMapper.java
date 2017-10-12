package com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.model.TrackingCodeVisit;

public class TrackingCodeVisitMapper  {


	public static Map<String, Object> map(TrackingCodeVisit trackingcodevisit) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(trackingcodevisit.getTrackingCodeId() != null ){
			returnVal.put("trackingCodeId",trackingcodevisit.getTrackingCodeId());
}

		if(trackingcodevisit.getVisitId() != null ){
			returnVal.put("visitId",trackingcodevisit.getVisitId());
}

		if(trackingcodevisit.getFromDate() != null ){
			returnVal.put("fromDate",trackingcodevisit.getFromDate());
}

		if(trackingcodevisit.getSourceEnumId() != null ){
			returnVal.put("sourceEnumId",trackingcodevisit.getSourceEnumId());
}

		return returnVal;
}


	public static TrackingCodeVisit map(Map<String, Object> fields) {

		TrackingCodeVisit returnVal = new TrackingCodeVisit();

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("sourceEnumId") != null) {
			returnVal.setSourceEnumId((String) fields.get("sourceEnumId"));
}


		return returnVal;
 } 
	public static TrackingCodeVisit mapstrstr(Map<String, String> fields) throws Exception {

		TrackingCodeVisit returnVal = new TrackingCodeVisit();

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("sourceEnumId") != null) {
			returnVal.setSourceEnumId((String) fields.get("sourceEnumId"));
}


		return returnVal;
 } 
	public static TrackingCodeVisit map(GenericValue val) {

TrackingCodeVisit returnVal = new TrackingCodeVisit();
		returnVal.setTrackingCodeId(val.getString("trackingCodeId"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setSourceEnumId(val.getString("sourceEnumId"));


return returnVal;

}

public static TrackingCodeVisit map(HttpServletRequest request) throws Exception {

		TrackingCodeVisit returnVal = new TrackingCodeVisit();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("trackingCodeId")) {
returnVal.setTrackingCodeId(request.getParameter("trackingCodeId"));
}

		if(paramMap.containsKey("visitId"))  {
returnVal.setVisitId(request.getParameter("visitId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("sourceEnumId"))  {
returnVal.setSourceEnumId(request.getParameter("sourceEnumId"));
}
return returnVal;

}
}
