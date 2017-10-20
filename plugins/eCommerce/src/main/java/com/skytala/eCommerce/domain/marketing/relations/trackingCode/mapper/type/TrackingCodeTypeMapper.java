package com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.type.TrackingCodeType;

public class TrackingCodeTypeMapper  {


	public static Map<String, Object> map(TrackingCodeType trackingcodetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(trackingcodetype.getTrackingCodeTypeId() != null ){
			returnVal.put("trackingCodeTypeId",trackingcodetype.getTrackingCodeTypeId());
}

		if(trackingcodetype.getDescription() != null ){
			returnVal.put("description",trackingcodetype.getDescription());
}

		return returnVal;
}


	public static TrackingCodeType map(Map<String, Object> fields) {

		TrackingCodeType returnVal = new TrackingCodeType();

		if(fields.get("trackingCodeTypeId") != null) {
			returnVal.setTrackingCodeTypeId((String) fields.get("trackingCodeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TrackingCodeType mapstrstr(Map<String, String> fields) throws Exception {

		TrackingCodeType returnVal = new TrackingCodeType();

		if(fields.get("trackingCodeTypeId") != null) {
			returnVal.setTrackingCodeTypeId((String) fields.get("trackingCodeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TrackingCodeType map(GenericValue val) {

TrackingCodeType returnVal = new TrackingCodeType();
		returnVal.setTrackingCodeTypeId(val.getString("trackingCodeTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TrackingCodeType map(HttpServletRequest request) throws Exception {

		TrackingCodeType returnVal = new TrackingCodeType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("trackingCodeTypeId")) {
returnVal.setTrackingCodeTypeId(request.getParameter("trackingCodeTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
