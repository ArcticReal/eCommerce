package com.skytala.eCommerce.domain.shipmentType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipmentType.model.ShipmentType;

public class ShipmentTypeMapper  {


	public static Map<String, Object> map(ShipmentType shipmenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmenttype.getShipmentTypeId() != null ){
			returnVal.put("shipmentTypeId",shipmenttype.getShipmentTypeId());
}

		if(shipmenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",shipmenttype.getParentTypeId());
}

		if(shipmenttype.getHasTable() != null ){
			returnVal.put("hasTable",shipmenttype.getHasTable());
}

		if(shipmenttype.getDescription() != null ){
			returnVal.put("description",shipmenttype.getDescription());
}

		return returnVal;
}


	public static ShipmentType map(Map<String, Object> fields) {

		ShipmentType returnVal = new ShipmentType();

		if(fields.get("shipmentTypeId") != null) {
			returnVal.setShipmentTypeId((String) fields.get("shipmentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentType mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentType returnVal = new ShipmentType();

		if(fields.get("shipmentTypeId") != null) {
			returnVal.setShipmentTypeId((String) fields.get("shipmentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentType map(GenericValue val) {

ShipmentType returnVal = new ShipmentType();
		returnVal.setShipmentTypeId(val.getString("shipmentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ShipmentType map(HttpServletRequest request) throws Exception {

		ShipmentType returnVal = new ShipmentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentTypeId")) {
returnVal.setShipmentTypeId(request.getParameter("shipmentTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
