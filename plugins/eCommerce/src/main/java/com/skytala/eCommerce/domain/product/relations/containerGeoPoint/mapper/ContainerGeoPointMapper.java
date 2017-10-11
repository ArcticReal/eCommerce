package com.skytala.eCommerce.domain.product.relations.containerGeoPoint.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.model.ContainerGeoPoint;

public class ContainerGeoPointMapper  {


	public static Map<String, Object> map(ContainerGeoPoint containergeopoint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(containergeopoint.getContainerId() != null ){
			returnVal.put("containerId",containergeopoint.getContainerId());
}

		if(containergeopoint.getGeoPointId() != null ){
			returnVal.put("geoPointId",containergeopoint.getGeoPointId());
}

		if(containergeopoint.getFromDate() != null ){
			returnVal.put("fromDate",containergeopoint.getFromDate());
}

		if(containergeopoint.getThruDate() != null ){
			returnVal.put("thruDate",containergeopoint.getThruDate());
}

		return returnVal;
}


	public static ContainerGeoPoint map(Map<String, Object> fields) {

		ContainerGeoPoint returnVal = new ContainerGeoPoint();

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ContainerGeoPoint mapstrstr(Map<String, String> fields) throws Exception {

		ContainerGeoPoint returnVal = new ContainerGeoPoint();

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
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


		return returnVal;
 } 
	public static ContainerGeoPoint map(GenericValue val) {

ContainerGeoPoint returnVal = new ContainerGeoPoint();
		returnVal.setContainerId(val.getString("containerId"));
		returnVal.setGeoPointId(val.getString("geoPointId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ContainerGeoPoint map(HttpServletRequest request) throws Exception {

		ContainerGeoPoint returnVal = new ContainerGeoPoint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("containerId")) {
returnVal.setContainerId(request.getParameter("containerId"));
}

		if(paramMap.containsKey("geoPointId"))  {
returnVal.setGeoPointId(request.getParameter("geoPointId"));
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
return returnVal;

}
}
