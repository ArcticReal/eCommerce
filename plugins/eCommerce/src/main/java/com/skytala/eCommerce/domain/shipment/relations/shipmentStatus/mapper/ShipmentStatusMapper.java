package com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.model.ShipmentStatus;

public class ShipmentStatusMapper  {


	public static Map<String, Object> map(ShipmentStatus shipmentstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentstatus.getStatusId() != null ){
			returnVal.put("statusId",shipmentstatus.getStatusId());
}

		if(shipmentstatus.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentstatus.getShipmentId());
}

		if(shipmentstatus.getStatusDate() != null ){
			returnVal.put("statusDate",shipmentstatus.getStatusDate());
}

		if(shipmentstatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",shipmentstatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static ShipmentStatus map(Map<String, Object> fields) {

		ShipmentStatus returnVal = new ShipmentStatus();

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static ShipmentStatus mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentStatus returnVal = new ShipmentStatus();

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static ShipmentStatus map(GenericValue val) {

ShipmentStatus returnVal = new ShipmentStatus();
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static ShipmentStatus map(HttpServletRequest request) throws Exception {

		ShipmentStatus returnVal = new ShipmentStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("statusId")) {
returnVal.setStatusId(request.getParameter("statusId"));
}

		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}
