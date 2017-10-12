package com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.model.ShipmentPackage;

public class ShipmentPackageMapper  {


	public static Map<String, Object> map(ShipmentPackage shipmentpackage) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentpackage.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentpackage.getShipmentId());
}

		if(shipmentpackage.getShipmentPackageSeqId() != null ){
			returnVal.put("shipmentPackageSeqId",shipmentpackage.getShipmentPackageSeqId());
}

		if(shipmentpackage.getShipmentBoxTypeId() != null ){
			returnVal.put("shipmentBoxTypeId",shipmentpackage.getShipmentBoxTypeId());
}

		if(shipmentpackage.getDateCreated() != null ){
			returnVal.put("dateCreated",shipmentpackage.getDateCreated());
}

		if(shipmentpackage.getBoxLength() != null ){
			returnVal.put("boxLength",shipmentpackage.getBoxLength());
}

		if(shipmentpackage.getBoxHeight() != null ){
			returnVal.put("boxHeight",shipmentpackage.getBoxHeight());
}

		if(shipmentpackage.getBoxWidth() != null ){
			returnVal.put("boxWidth",shipmentpackage.getBoxWidth());
}

		if(shipmentpackage.getDimensionUomId() != null ){
			returnVal.put("dimensionUomId",shipmentpackage.getDimensionUomId());
}

		if(shipmentpackage.getWeight() != null ){
			returnVal.put("weight",shipmentpackage.getWeight());
}

		if(shipmentpackage.getWeightUomId() != null ){
			returnVal.put("weightUomId",shipmentpackage.getWeightUomId());
}

		if(shipmentpackage.getInsuredValue() != null ){
			returnVal.put("insuredValue",shipmentpackage.getInsuredValue());
}

		return returnVal;
}


	public static ShipmentPackage map(Map<String, Object> fields) {

		ShipmentPackage returnVal = new ShipmentPackage();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("shipmentBoxTypeId") != null) {
			returnVal.setShipmentBoxTypeId((String) fields.get("shipmentBoxTypeId"));
}

		if(fields.get("dateCreated") != null) {
			returnVal.setDateCreated((Timestamp) fields.get("dateCreated"));
}

		if(fields.get("boxLength") != null) {
			returnVal.setBoxLength((BigDecimal) fields.get("boxLength"));
}

		if(fields.get("boxHeight") != null) {
			returnVal.setBoxHeight((BigDecimal) fields.get("boxHeight"));
}

		if(fields.get("boxWidth") != null) {
			returnVal.setBoxWidth((BigDecimal) fields.get("boxWidth"));
}

		if(fields.get("dimensionUomId") != null) {
			returnVal.setDimensionUomId((String) fields.get("dimensionUomId"));
}

		if(fields.get("weight") != null) {
			returnVal.setWeight((BigDecimal) fields.get("weight"));
}

		if(fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
}

		if(fields.get("insuredValue") != null) {
			returnVal.setInsuredValue((BigDecimal) fields.get("insuredValue"));
}


		return returnVal;
 } 
	public static ShipmentPackage mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentPackage returnVal = new ShipmentPackage();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("shipmentBoxTypeId") != null) {
			returnVal.setShipmentBoxTypeId((String) fields.get("shipmentBoxTypeId"));
}

		if(fields.get("dateCreated") != null) {
String buf = fields.get("dateCreated");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateCreated(ibuf);
}

		if(fields.get("boxLength") != null) {
String buf;
buf = fields.get("boxLength");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxLength(bd);
}

		if(fields.get("boxHeight") != null) {
String buf;
buf = fields.get("boxHeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxHeight(bd);
}

		if(fields.get("boxWidth") != null) {
String buf;
buf = fields.get("boxWidth");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxWidth(bd);
}

		if(fields.get("dimensionUomId") != null) {
			returnVal.setDimensionUomId((String) fields.get("dimensionUomId"));
}

		if(fields.get("weight") != null) {
String buf;
buf = fields.get("weight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeight(bd);
}

		if(fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
}

		if(fields.get("insuredValue") != null) {
String buf;
buf = fields.get("insuredValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setInsuredValue(bd);
}


		return returnVal;
 } 
	public static ShipmentPackage map(GenericValue val) {

ShipmentPackage returnVal = new ShipmentPackage();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentPackageSeqId(val.getString("shipmentPackageSeqId"));
		returnVal.setShipmentBoxTypeId(val.getString("shipmentBoxTypeId"));
		returnVal.setDateCreated(val.getTimestamp("dateCreated"));
		returnVal.setBoxLength(val.getBigDecimal("boxLength"));
		returnVal.setBoxHeight(val.getBigDecimal("boxHeight"));
		returnVal.setBoxWidth(val.getBigDecimal("boxWidth"));
		returnVal.setDimensionUomId(val.getString("dimensionUomId"));
		returnVal.setWeight(val.getBigDecimal("weight"));
		returnVal.setWeightUomId(val.getString("weightUomId"));
		returnVal.setInsuredValue(val.getBigDecimal("insuredValue"));


return returnVal;

}

public static ShipmentPackage map(HttpServletRequest request) throws Exception {

		ShipmentPackage returnVal = new ShipmentPackage();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentPackageSeqId"))  {
returnVal.setShipmentPackageSeqId(request.getParameter("shipmentPackageSeqId"));
}
		if(paramMap.containsKey("shipmentBoxTypeId"))  {
returnVal.setShipmentBoxTypeId(request.getParameter("shipmentBoxTypeId"));
}
		if(paramMap.containsKey("dateCreated"))  {
String buf = request.getParameter("dateCreated");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateCreated(ibuf);
}
		if(paramMap.containsKey("boxLength"))  {
String buf = request.getParameter("boxLength");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxLength(bd);
}
		if(paramMap.containsKey("boxHeight"))  {
String buf = request.getParameter("boxHeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxHeight(bd);
}
		if(paramMap.containsKey("boxWidth"))  {
String buf = request.getParameter("boxWidth");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBoxWidth(bd);
}
		if(paramMap.containsKey("dimensionUomId"))  {
returnVal.setDimensionUomId(request.getParameter("dimensionUomId"));
}
		if(paramMap.containsKey("weight"))  {
String buf = request.getParameter("weight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeight(bd);
}
		if(paramMap.containsKey("weightUomId"))  {
returnVal.setWeightUomId(request.getParameter("weightUomId"));
}
		if(paramMap.containsKey("insuredValue"))  {
String buf = request.getParameter("insuredValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setInsuredValue(bd);
}
return returnVal;

}
}
