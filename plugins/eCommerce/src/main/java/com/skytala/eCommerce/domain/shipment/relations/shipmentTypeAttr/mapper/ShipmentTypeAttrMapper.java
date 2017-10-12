package com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.model.ShipmentTypeAttr;

public class ShipmentTypeAttrMapper  {


	public static Map<String, Object> map(ShipmentTypeAttr shipmenttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmenttypeattr.getShipmentTypeId() != null ){
			returnVal.put("shipmentTypeId",shipmenttypeattr.getShipmentTypeId());
}

		if(shipmenttypeattr.getAttrName() != null ){
			returnVal.put("attrName",shipmenttypeattr.getAttrName());
}

		if(shipmenttypeattr.getDescription() != null ){
			returnVal.put("description",shipmenttypeattr.getDescription());
}

		return returnVal;
}


	public static ShipmentTypeAttr map(Map<String, Object> fields) {

		ShipmentTypeAttr returnVal = new ShipmentTypeAttr();

		if(fields.get("shipmentTypeId") != null) {
			returnVal.setShipmentTypeId((String) fields.get("shipmentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentTypeAttr returnVal = new ShipmentTypeAttr();

		if(fields.get("shipmentTypeId") != null) {
			returnVal.setShipmentTypeId((String) fields.get("shipmentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentTypeAttr map(GenericValue val) {

ShipmentTypeAttr returnVal = new ShipmentTypeAttr();
		returnVal.setShipmentTypeId(val.getString("shipmentTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ShipmentTypeAttr map(HttpServletRequest request) throws Exception {

		ShipmentTypeAttr returnVal = new ShipmentTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentTypeId")) {
returnVal.setShipmentTypeId(request.getParameter("shipmentTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
