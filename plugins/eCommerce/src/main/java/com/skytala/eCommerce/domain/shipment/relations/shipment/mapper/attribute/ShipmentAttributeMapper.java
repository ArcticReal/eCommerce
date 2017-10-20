package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.attribute.ShipmentAttribute;

public class ShipmentAttributeMapper  {


	public static Map<String, Object> map(ShipmentAttribute shipmentattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentattribute.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentattribute.getShipmentId());
}

		if(shipmentattribute.getAttrName() != null ){
			returnVal.put("attrName",shipmentattribute.getAttrName());
}

		if(shipmentattribute.getAttrValue() != null ){
			returnVal.put("attrValue",shipmentattribute.getAttrValue());
}

		if(shipmentattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",shipmentattribute.getAttrDescription());
}

		return returnVal;
}


	public static ShipmentAttribute map(Map<String, Object> fields) {

		ShipmentAttribute returnVal = new ShipmentAttribute();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ShipmentAttribute mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentAttribute returnVal = new ShipmentAttribute();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ShipmentAttribute map(GenericValue val) {

ShipmentAttribute returnVal = new ShipmentAttribute();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static ShipmentAttribute map(HttpServletRequest request) throws Exception {

		ShipmentAttribute returnVal = new ShipmentAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
