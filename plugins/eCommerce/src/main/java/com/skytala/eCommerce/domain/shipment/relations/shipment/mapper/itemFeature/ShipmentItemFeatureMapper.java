package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.itemFeature;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemFeature.ShipmentItemFeature;

public class ShipmentItemFeatureMapper  {


	public static Map<String, Object> map(ShipmentItemFeature shipmentitemfeature) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentitemfeature.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentitemfeature.getShipmentId());
}

		if(shipmentitemfeature.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",shipmentitemfeature.getShipmentItemSeqId());
}

		if(shipmentitemfeature.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",shipmentitemfeature.getProductFeatureId());
}

		return returnVal;
}


	public static ShipmentItemFeature map(Map<String, Object> fields) {

		ShipmentItemFeature returnVal = new ShipmentItemFeature();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}


		return returnVal;
 } 
	public static ShipmentItemFeature mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentItemFeature returnVal = new ShipmentItemFeature();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}


		return returnVal;
 } 
	public static ShipmentItemFeature map(GenericValue val) {

ShipmentItemFeature returnVal = new ShipmentItemFeature();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));


return returnVal;

}

public static ShipmentItemFeature map(HttpServletRequest request) throws Exception {

		ShipmentItemFeature returnVal = new ShipmentItemFeature();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
return returnVal;

}
}
