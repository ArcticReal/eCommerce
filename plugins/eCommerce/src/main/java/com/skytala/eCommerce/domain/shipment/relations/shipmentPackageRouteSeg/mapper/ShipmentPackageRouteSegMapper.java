package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageRouteSeg.model.ShipmentPackageRouteSeg;

public class ShipmentPackageRouteSegMapper  {


	public static Map<String, Object> map(ShipmentPackageRouteSeg shipmentpackagerouteseg) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentpackagerouteseg.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentpackagerouteseg.getShipmentId());
}

		if(shipmentpackagerouteseg.getShipmentPackageSeqId() != null ){
			returnVal.put("shipmentPackageSeqId",shipmentpackagerouteseg.getShipmentPackageSeqId());
}

		if(shipmentpackagerouteseg.getShipmentRouteSegmentId() != null ){
			returnVal.put("shipmentRouteSegmentId",shipmentpackagerouteseg.getShipmentRouteSegmentId());
}

		if(shipmentpackagerouteseg.getTrackingCode() != null ){
			returnVal.put("trackingCode",shipmentpackagerouteseg.getTrackingCode());
}

		if(shipmentpackagerouteseg.getBoxNumber() != null ){
			returnVal.put("boxNumber",shipmentpackagerouteseg.getBoxNumber());
}

		if(shipmentpackagerouteseg.getLabelImage() != null ){
			returnVal.put("labelImage",shipmentpackagerouteseg.getLabelImage());
}

		if(shipmentpackagerouteseg.getLabelIntlSignImage() != null ){
			returnVal.put("labelIntlSignImage",shipmentpackagerouteseg.getLabelIntlSignImage());
}

		if(shipmentpackagerouteseg.getLabelHtml() != null ){
			returnVal.put("labelHtml",shipmentpackagerouteseg.getLabelHtml());
}

		if(shipmentpackagerouteseg.getLabelPrinted() != null ){
			returnVal.put("labelPrinted",shipmentpackagerouteseg.getLabelPrinted());
}

		if(shipmentpackagerouteseg.getInternationalInvoice() != null ){
			returnVal.put("internationalInvoice",shipmentpackagerouteseg.getInternationalInvoice());
}

		if(shipmentpackagerouteseg.getPackageTransportCost() != null ){
			returnVal.put("packageTransportCost",shipmentpackagerouteseg.getPackageTransportCost());
}

		if(shipmentpackagerouteseg.getPackageServiceCost() != null ){
			returnVal.put("packageServiceCost",shipmentpackagerouteseg.getPackageServiceCost());
}

		if(shipmentpackagerouteseg.getPackageOtherCost() != null ){
			returnVal.put("packageOtherCost",shipmentpackagerouteseg.getPackageOtherCost());
}

		if(shipmentpackagerouteseg.getCodAmount() != null ){
			returnVal.put("codAmount",shipmentpackagerouteseg.getCodAmount());
}

		if(shipmentpackagerouteseg.getInsuredAmount() != null ){
			returnVal.put("insuredAmount",shipmentpackagerouteseg.getInsuredAmount());
}

		if(shipmentpackagerouteseg.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",shipmentpackagerouteseg.getCurrencyUomId());
}

		return returnVal;
}


	public static ShipmentPackageRouteSeg map(Map<String, Object> fields) {

		ShipmentPackageRouteSeg returnVal = new ShipmentPackageRouteSeg();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("shipmentRouteSegmentId") != null) {
			returnVal.setShipmentRouteSegmentId((String) fields.get("shipmentRouteSegmentId"));
}

		if(fields.get("trackingCode") != null) {
			returnVal.setTrackingCode((String) fields.get("trackingCode"));
}

		if(fields.get("boxNumber") != null) {
			returnVal.setBoxNumber((String) fields.get("boxNumber"));
}

		if(fields.get("labelImage") != null) {
			returnVal.setLabelImage((byte[]) fields.get("labelImage"));
}

		if(fields.get("labelIntlSignImage") != null) {
			returnVal.setLabelIntlSignImage((byte[]) fields.get("labelIntlSignImage"));
}

		if(fields.get("labelHtml") != null) {
			returnVal.setLabelHtml((String) fields.get("labelHtml"));
}

		if(fields.get("labelPrinted") != null) {
			returnVal.setLabelPrinted((boolean) fields.get("labelPrinted"));
}

		if(fields.get("internationalInvoice") != null) {
			returnVal.setInternationalInvoice((byte[]) fields.get("internationalInvoice"));
}

		if(fields.get("packageTransportCost") != null) {
			returnVal.setPackageTransportCost((BigDecimal) fields.get("packageTransportCost"));
}

		if(fields.get("packageServiceCost") != null) {
			returnVal.setPackageServiceCost((BigDecimal) fields.get("packageServiceCost"));
}

		if(fields.get("packageOtherCost") != null) {
			returnVal.setPackageOtherCost((BigDecimal) fields.get("packageOtherCost"));
}

		if(fields.get("codAmount") != null) {
			returnVal.setCodAmount((BigDecimal) fields.get("codAmount"));
}

		if(fields.get("insuredAmount") != null) {
			returnVal.setInsuredAmount((BigDecimal) fields.get("insuredAmount"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}


		return returnVal;
 } 
	public static ShipmentPackageRouteSeg mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentPackageRouteSeg returnVal = new ShipmentPackageRouteSeg();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("shipmentRouteSegmentId") != null) {
			returnVal.setShipmentRouteSegmentId((String) fields.get("shipmentRouteSegmentId"));
}

		if(fields.get("trackingCode") != null) {
			returnVal.setTrackingCode((String) fields.get("trackingCode"));
}

		if(fields.get("boxNumber") != null) {
			returnVal.setBoxNumber((String) fields.get("boxNumber"));
}

		if(fields.get("labelImage") != null) {
String buf = fields.get("labelImage");
byte[] ibuf = buf.getBytes();
			returnVal.setLabelImage(ibuf);
}

		if(fields.get("labelIntlSignImage") != null) {
String buf = fields.get("labelIntlSignImage");
byte[] ibuf = buf.getBytes();
			returnVal.setLabelIntlSignImage(ibuf);
}

		if(fields.get("labelHtml") != null) {
			returnVal.setLabelHtml((String) fields.get("labelHtml"));
}

		if(fields.get("labelPrinted") != null) {
String buf;
buf = fields.get("labelPrinted");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setLabelPrinted(ibuf);
}

		if(fields.get("internationalInvoice") != null) {
String buf = fields.get("internationalInvoice");
byte[] ibuf = buf.getBytes();
			returnVal.setInternationalInvoice(ibuf);
}

		if(fields.get("packageTransportCost") != null) {
String buf;
buf = fields.get("packageTransportCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPackageTransportCost(bd);
}

		if(fields.get("packageServiceCost") != null) {
String buf;
buf = fields.get("packageServiceCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPackageServiceCost(bd);
}

		if(fields.get("packageOtherCost") != null) {
String buf;
buf = fields.get("packageOtherCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPackageOtherCost(bd);
}

		if(fields.get("codAmount") != null) {
String buf;
buf = fields.get("codAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCodAmount(bd);
}

		if(fields.get("insuredAmount") != null) {
String buf;
buf = fields.get("insuredAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setInsuredAmount(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}


		return returnVal;
 } 
	public static ShipmentPackageRouteSeg map(GenericValue val) {

ShipmentPackageRouteSeg returnVal = new ShipmentPackageRouteSeg();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentPackageSeqId(val.getString("shipmentPackageSeqId"));
		returnVal.setShipmentRouteSegmentId(val.getString("shipmentRouteSegmentId"));
		returnVal.setTrackingCode(val.getString("trackingCode"));
		returnVal.setBoxNumber(val.getString("boxNumber"));
		returnVal.setLabelImage(val.getBytes("labelImage"));
		returnVal.setLabelIntlSignImage(val.getBytes("labelIntlSignImage"));
		returnVal.setLabelHtml(val.getString("labelHtml"));
		returnVal.setLabelPrinted(val.getBoolean("labelPrinted"));
		returnVal.setInternationalInvoice(val.getBytes("internationalInvoice"));
		returnVal.setPackageTransportCost(val.getBigDecimal("packageTransportCost"));
		returnVal.setPackageServiceCost(val.getBigDecimal("packageServiceCost"));
		returnVal.setPackageOtherCost(val.getBigDecimal("packageOtherCost"));
		returnVal.setCodAmount(val.getBigDecimal("codAmount"));
		returnVal.setInsuredAmount(val.getBigDecimal("insuredAmount"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));


return returnVal;

}

public static ShipmentPackageRouteSeg map(HttpServletRequest request) throws Exception {

		ShipmentPackageRouteSeg returnVal = new ShipmentPackageRouteSeg();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentPackageSeqId"))  {
returnVal.setShipmentPackageSeqId(request.getParameter("shipmentPackageSeqId"));
}
		if(paramMap.containsKey("shipmentRouteSegmentId"))  {
returnVal.setShipmentRouteSegmentId(request.getParameter("shipmentRouteSegmentId"));
}
		if(paramMap.containsKey("trackingCode"))  {
returnVal.setTrackingCode(request.getParameter("trackingCode"));
}
		if(paramMap.containsKey("boxNumber"))  {
returnVal.setBoxNumber(request.getParameter("boxNumber"));
}
		if(paramMap.containsKey("labelImage"))  {
String buf = request.getParameter("labelImage");
byte[] ibuf = buf.getBytes();
returnVal.setLabelImage(ibuf);
}
		if(paramMap.containsKey("labelIntlSignImage"))  {
String buf = request.getParameter("labelIntlSignImage");
byte[] ibuf = buf.getBytes();
returnVal.setLabelIntlSignImage(ibuf);
}
		if(paramMap.containsKey("labelHtml"))  {
returnVal.setLabelHtml(request.getParameter("labelHtml"));
}
		if(paramMap.containsKey("labelPrinted"))  {
String buf = request.getParameter("labelPrinted");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setLabelPrinted(ibuf);
}
		if(paramMap.containsKey("internationalInvoice"))  {
String buf = request.getParameter("internationalInvoice");
byte[] ibuf = buf.getBytes();
returnVal.setInternationalInvoice(ibuf);
}
		if(paramMap.containsKey("packageTransportCost"))  {
String buf = request.getParameter("packageTransportCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPackageTransportCost(bd);
}
		if(paramMap.containsKey("packageServiceCost"))  {
String buf = request.getParameter("packageServiceCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPackageServiceCost(bd);
}
		if(paramMap.containsKey("packageOtherCost"))  {
String buf = request.getParameter("packageOtherCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPackageOtherCost(bd);
}
		if(paramMap.containsKey("codAmount"))  {
String buf = request.getParameter("codAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCodAmount(bd);
}
		if(paramMap.containsKey("insuredAmount"))  {
String buf = request.getParameter("insuredAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setInsuredAmount(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
return returnVal;

}
}
