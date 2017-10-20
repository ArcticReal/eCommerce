package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.geo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.geo.SegmentGroupGeo;

public class SegmentGroupGeoMapper  {


	public static Map<String, Object> map(SegmentGroupGeo segmentgroupgeo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(segmentgroupgeo.getSegmentGroupId() != null ){
			returnVal.put("segmentGroupId",segmentgroupgeo.getSegmentGroupId());
}

		if(segmentgroupgeo.getGeoId() != null ){
			returnVal.put("geoId",segmentgroupgeo.getGeoId());
}

		return returnVal;
}


	public static SegmentGroupGeo map(Map<String, Object> fields) {

		SegmentGroupGeo returnVal = new SegmentGroupGeo();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}


		return returnVal;
 } 
	public static SegmentGroupGeo mapstrstr(Map<String, String> fields) throws Exception {

		SegmentGroupGeo returnVal = new SegmentGroupGeo();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}


		return returnVal;
 } 
	public static SegmentGroupGeo map(GenericValue val) {

SegmentGroupGeo returnVal = new SegmentGroupGeo();
		returnVal.setSegmentGroupId(val.getString("segmentGroupId"));
		returnVal.setGeoId(val.getString("geoId"));


return returnVal;

}

public static SegmentGroupGeo map(HttpServletRequest request) throws Exception {

		SegmentGroupGeo returnVal = new SegmentGroupGeo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("segmentGroupId")) {
returnVal.setSegmentGroupId(request.getParameter("segmentGroupId"));
}

		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
}
return returnVal;

}
}
