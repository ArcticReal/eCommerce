package com.skytala.eCommerce.domain.shipmentBoxType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipmentBoxType.model.ShipmentBoxType;

public class ShipmentBoxTypeMapper  {


	public static Map<String, Object> map(ShipmentBoxType shipmentboxtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentboxtype.getShipmentBoxTypeId() != null ){
			returnVal.put("shipmentBoxTypeId",shipmentboxtype.getShipmentBoxTypeId());
}

		if(shipmentboxtype.getDescription() != null ){
			returnVal.put("description",shipmentboxtype.getDescription());
}

		if(shipmentboxtype.getDimensionUomId() != null ){
			returnVal.put("dimensionUomId",shipmentboxtype.getDimensionUomId());
}

		if(shipmentboxtype.getBoxLength() != null ){
			returnVal.put("boxLength",shipmentboxtype.getBoxLength());
}

		if(shipmentboxtype.getBoxWidth() != null ){
			returnVal.put("boxWidth",shipmentboxtype.getBoxWidth());
}

		if(shipmentboxtype.getBoxHeight() != null ){
			returnVal.put("boxHeight",shipmentboxtype.getBoxHeight());
}

		if(shipmentboxtype.getWeightUomId() != null ){
			returnVal.put("weightUomId",shipmentboxtype.getWeightUomId());
}

		if(shipmentboxtype.getBoxWeight() != null ){
			returnVal.put("boxWeight",shipmentboxtype.getBoxWeight());
}

		return returnVal;
}


	public static ShipmentBoxType map(Map<String, Object> fields) {

		ShipmentBoxType returnVal = new ShipmentBoxType();

		if(fields.get("shipmentBoxTypeId") != null) {
			returnVal.setShipmentBoxTypeId((String) fields.get("shipmentBoxTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("dimensionUomId") != null) {
			returnVal.setDimensionUomId((String) fields.get("dimensionUomId"));
}

		if(fields.get("boxLength") != null) {
			returnVal.setBoxLength((BigDecimal) fields.get("boxLength"));
}

		if(fields.get("boxWidth") != null) {
			returnVal.setBoxWidth((BigDecimal) fields.get("boxWidth"));
}

		if(fields.get("boxHeight") != null) {
			returnVal.setBoxHeight((BigDecimal) fields.get("boxHeight"));
}

		if(fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
}

		if(fields.get("boxWeight") != null) {
			returnVal.setBoxWeight((BigDecimal) fields.get("boxWeight"));
}


		return returnVal;
 } 
	public static ShipmentBoxType mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentBoxType returnVal = new ShipmentBoxType();

		if(fields.get("shipmentBoxTypeId") != null) {
			returnVal.setShipmentBoxTypeId((String) fields.get("shipmentBoxTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("dimensionUomId") != null) {
			returnVal.setDimensionUomId((String) fields.get("dimensionUomId"));
}

		if(fields.get("boxLength") != null) {
String buf;
buf = fields.get("boxLength");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxLength(bd);
}

		if(fields.get("boxWidth") != null) {
String buf;
buf = fields.get("boxWidth");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxWidth(bd);
}

		if(fields.get("boxHeight") != null) {
String buf;
buf = fields.get("boxHeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxHeight(bd);
}

		if(fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
}

		if(fields.get("boxWeight") != null) {
String buf;
buf = fields.get("boxWeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxWeight(bd);
}


		return returnVal;
 } 
	public static ShipmentBoxType map(GenericValue val) {

ShipmentBoxType returnVal = new ShipmentBoxType();
		returnVal.setShipmentBoxTypeId(val.getString("shipmentBoxTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setDimensionUomId(val.getString("dimensionUomId"));
		returnVal.setBoxLength(val.getBigDecimal("boxLength"));
		returnVal.setBoxWidth(val.getBigDecimal("boxWidth"));
		returnVal.setBoxHeight(val.getBigDecimal("boxHeight"));
		returnVal.setWeightUomId(val.getString("weightUomId"));
		returnVal.setBoxWeight(val.getBigDecimal("boxWeight"));


return returnVal;

}

public static ShipmentBoxType map(HttpServletRequest request) throws Exception {

		ShipmentBoxType returnVal = new ShipmentBoxType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentBoxTypeId")) {
returnVal.setShipmentBoxTypeId(request.getParameter("shipmentBoxTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("dimensionUomId"))  {
returnVal.setDimensionUomId(request.getParameter("dimensionUomId"));
}
		if(paramMap.containsKey("boxLength"))  {
String buf = request.getParameter("boxLength");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxLength(bd);
}
		if(paramMap.containsKey("boxWidth"))  {
String buf = request.getParameter("boxWidth");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxWidth(bd);
}
		if(paramMap.containsKey("boxHeight"))  {
String buf = request.getParameter("boxHeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxHeight(bd);
}
		if(paramMap.containsKey("weightUomId"))  {
returnVal.setWeightUomId(request.getParameter("weightUomId"));
}
		if(paramMap.containsKey("boxWeight"))  {
String buf = request.getParameter("boxWeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxWeight(bd);
}
return returnVal;

}
}
