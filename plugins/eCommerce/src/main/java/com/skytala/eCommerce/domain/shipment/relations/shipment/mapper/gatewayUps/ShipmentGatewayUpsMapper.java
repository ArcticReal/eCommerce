package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUps;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUps.ShipmentGatewayUps;

public class ShipmentGatewayUpsMapper  {


	public static Map<String, Object> map(ShipmentGatewayUps shipmentgatewayups) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentgatewayups.getShipmentGatewayConfigId() != null ){
			returnVal.put("shipmentGatewayConfigId",shipmentgatewayups.getShipmentGatewayConfigId());
}

		if(shipmentgatewayups.getConnectUrl() != null ){
			returnVal.put("connectUrl",shipmentgatewayups.getConnectUrl());
}

		if(shipmentgatewayups.getConnectTimeout() != null ){
			returnVal.put("connectTimeout",shipmentgatewayups.getConnectTimeout());
}

		if(shipmentgatewayups.getShipperNumber() != null ){
			returnVal.put("shipperNumber",shipmentgatewayups.getShipperNumber());
}

		if(shipmentgatewayups.getBillShipperAccountNumber() != null ){
			returnVal.put("billShipperAccountNumber",shipmentgatewayups.getBillShipperAccountNumber());
}

		if(shipmentgatewayups.getAccessLicenseNumber() != null ){
			returnVal.put("accessLicenseNumber",shipmentgatewayups.getAccessLicenseNumber());
}

		if(shipmentgatewayups.getAccessUserId() != null ){
			returnVal.put("accessUserId",shipmentgatewayups.getAccessUserId());
}

		if(shipmentgatewayups.getAccessPassword() != null ){
			returnVal.put("accessPassword",shipmentgatewayups.getAccessPassword());
}

		if(shipmentgatewayups.getSaveCertInfo() != null ){
			returnVal.put("saveCertInfo",shipmentgatewayups.getSaveCertInfo());
}

		if(shipmentgatewayups.getSaveCertPath() != null ){
			returnVal.put("saveCertPath",shipmentgatewayups.getSaveCertPath());
}

		if(shipmentgatewayups.getShipperPickupType() != null ){
			returnVal.put("shipperPickupType",shipmentgatewayups.getShipperPickupType());
}

		if(shipmentgatewayups.getCustomerClassification() != null ){
			returnVal.put("customerClassification",shipmentgatewayups.getCustomerClassification());
}

		if(shipmentgatewayups.getMaxEstimateWeight() != null ){
			returnVal.put("maxEstimateWeight",shipmentgatewayups.getMaxEstimateWeight());
}

		if(shipmentgatewayups.getMinEstimateWeight() != null ){
			returnVal.put("minEstimateWeight",shipmentgatewayups.getMinEstimateWeight());
}

		if(shipmentgatewayups.getCodAllowCod() != null ){
			returnVal.put("codAllowCod",shipmentgatewayups.getCodAllowCod());
}

		if(shipmentgatewayups.getCodSurchargeAmount() != null ){
			returnVal.put("codSurchargeAmount",shipmentgatewayups.getCodSurchargeAmount());
}

		if(shipmentgatewayups.getCodSurchargeCurrencyUomId() != null ){
			returnVal.put("codSurchargeCurrencyUomId",shipmentgatewayups.getCodSurchargeCurrencyUomId());
}

		if(shipmentgatewayups.getCodSurchargeApplyToPackage() != null ){
			returnVal.put("codSurchargeApplyToPackage",shipmentgatewayups.getCodSurchargeApplyToPackage());
}

		if(shipmentgatewayups.getCodFundsCode() != null ){
			returnVal.put("codFundsCode",shipmentgatewayups.getCodFundsCode());
}

		if(shipmentgatewayups.getDefaultReturnLabelMemo() != null ){
			returnVal.put("defaultReturnLabelMemo",shipmentgatewayups.getDefaultReturnLabelMemo());
}

		if(shipmentgatewayups.getDefaultReturnLabelSubject() != null ){
			returnVal.put("defaultReturnLabelSubject",shipmentgatewayups.getDefaultReturnLabelSubject());
}

		return returnVal;
}


	public static ShipmentGatewayUps map(Map<String, Object> fields) {

		ShipmentGatewayUps returnVal = new ShipmentGatewayUps();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
			returnVal.setConnectUrl((long) fields.get("connectUrl"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("shipperNumber") != null) {
			returnVal.setShipperNumber((long) fields.get("shipperNumber"));
}

		if(fields.get("billShipperAccountNumber") != null) {
			returnVal.setBillShipperAccountNumber((long) fields.get("billShipperAccountNumber"));
}

		if(fields.get("accessLicenseNumber") != null) {
			returnVal.setAccessLicenseNumber((long) fields.get("accessLicenseNumber"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((long) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((long) fields.get("accessPassword"));
}

		if(fields.get("saveCertInfo") != null) {
			returnVal.setSaveCertInfo((String) fields.get("saveCertInfo"));
}

		if(fields.get("saveCertPath") != null) {
			returnVal.setSaveCertPath((long) fields.get("saveCertPath"));
}

		if(fields.get("shipperPickupType") != null) {
			returnVal.setShipperPickupType((String) fields.get("shipperPickupType"));
}

		if(fields.get("customerClassification") != null) {
			returnVal.setCustomerClassification((String) fields.get("customerClassification"));
}

		if(fields.get("maxEstimateWeight") != null) {
			returnVal.setMaxEstimateWeight((BigDecimal) fields.get("maxEstimateWeight"));
}

		if(fields.get("minEstimateWeight") != null) {
			returnVal.setMinEstimateWeight((BigDecimal) fields.get("minEstimateWeight"));
}

		if(fields.get("codAllowCod") != null) {
			returnVal.setCodAllowCod((long) fields.get("codAllowCod"));
}

		if(fields.get("codSurchargeAmount") != null) {
			returnVal.setCodSurchargeAmount((BigDecimal) fields.get("codSurchargeAmount"));
}

		if(fields.get("codSurchargeCurrencyUomId") != null) {
			returnVal.setCodSurchargeCurrencyUomId((String) fields.get("codSurchargeCurrencyUomId"));
}

		if(fields.get("codSurchargeApplyToPackage") != null) {
			returnVal.setCodSurchargeApplyToPackage((String) fields.get("codSurchargeApplyToPackage"));
}

		if(fields.get("codFundsCode") != null) {
			returnVal.setCodFundsCode((String) fields.get("codFundsCode"));
}

		if(fields.get("defaultReturnLabelMemo") != null) {
			returnVal.setDefaultReturnLabelMemo((long) fields.get("defaultReturnLabelMemo"));
}

		if(fields.get("defaultReturnLabelSubject") != null) {
			returnVal.setDefaultReturnLabelSubject((long) fields.get("defaultReturnLabelSubject"));
}


		return returnVal;
 } 
	public static ShipmentGatewayUps mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayUps returnVal = new ShipmentGatewayUps();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
String buf;
buf = fields.get("connectUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectUrl(ibuf);
}

		if(fields.get("connectTimeout") != null) {
String buf;
buf = fields.get("connectTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectTimeout(ibuf);
}

		if(fields.get("shipperNumber") != null) {
String buf;
buf = fields.get("shipperNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setShipperNumber(ibuf);
}

		if(fields.get("billShipperAccountNumber") != null) {
String buf;
buf = fields.get("billShipperAccountNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setBillShipperAccountNumber(ibuf);
}

		if(fields.get("accessLicenseNumber") != null) {
String buf;
buf = fields.get("accessLicenseNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessLicenseNumber(ibuf);
}

		if(fields.get("accessUserId") != null) {
String buf;
buf = fields.get("accessUserId");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessUserId(ibuf);
}

		if(fields.get("accessPassword") != null) {
String buf;
buf = fields.get("accessPassword");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessPassword(ibuf);
}

		if(fields.get("saveCertInfo") != null) {
			returnVal.setSaveCertInfo((String) fields.get("saveCertInfo"));
}

		if(fields.get("saveCertPath") != null) {
String buf;
buf = fields.get("saveCertPath");
long ibuf = Long.parseLong(buf);
			returnVal.setSaveCertPath(ibuf);
}

		if(fields.get("shipperPickupType") != null) {
			returnVal.setShipperPickupType((String) fields.get("shipperPickupType"));
}

		if(fields.get("customerClassification") != null) {
			returnVal.setCustomerClassification((String) fields.get("customerClassification"));
}

		if(fields.get("maxEstimateWeight") != null) {
String buf;
buf = fields.get("maxEstimateWeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxEstimateWeight(bd);
}

		if(fields.get("minEstimateWeight") != null) {
String buf;
buf = fields.get("minEstimateWeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinEstimateWeight(bd);
}

		if(fields.get("codAllowCod") != null) {
String buf;
buf = fields.get("codAllowCod");
long ibuf = Long.parseLong(buf);
			returnVal.setCodAllowCod(ibuf);
}

		if(fields.get("codSurchargeAmount") != null) {
String buf;
buf = fields.get("codSurchargeAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCodSurchargeAmount(bd);
}

		if(fields.get("codSurchargeCurrencyUomId") != null) {
			returnVal.setCodSurchargeCurrencyUomId((String) fields.get("codSurchargeCurrencyUomId"));
}

		if(fields.get("codSurchargeApplyToPackage") != null) {
			returnVal.setCodSurchargeApplyToPackage((String) fields.get("codSurchargeApplyToPackage"));
}

		if(fields.get("codFundsCode") != null) {
			returnVal.setCodFundsCode((String) fields.get("codFundsCode"));
}

		if(fields.get("defaultReturnLabelMemo") != null) {
String buf;
buf = fields.get("defaultReturnLabelMemo");
long ibuf = Long.parseLong(buf);
			returnVal.setDefaultReturnLabelMemo(ibuf);
}

		if(fields.get("defaultReturnLabelSubject") != null) {
String buf;
buf = fields.get("defaultReturnLabelSubject");
long ibuf = Long.parseLong(buf);
			returnVal.setDefaultReturnLabelSubject(ibuf);
}


		return returnVal;
 } 
	public static ShipmentGatewayUps map(GenericValue val) {

ShipmentGatewayUps returnVal = new ShipmentGatewayUps();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setConnectUrl(val.getLong("connectUrl"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setShipperNumber(val.getLong("shipperNumber"));
		returnVal.setBillShipperAccountNumber(val.getLong("billShipperAccountNumber"));
		returnVal.setAccessLicenseNumber(val.getLong("accessLicenseNumber"));
		returnVal.setAccessUserId(val.getLong("accessUserId"));
		returnVal.setAccessPassword(val.getLong("accessPassword"));
		returnVal.setSaveCertInfo(val.getString("saveCertInfo"));
		returnVal.setSaveCertPath(val.getLong("saveCertPath"));
		returnVal.setShipperPickupType(val.getString("shipperPickupType"));
		returnVal.setCustomerClassification(val.getString("customerClassification"));
		returnVal.setMaxEstimateWeight(val.getBigDecimal("maxEstimateWeight"));
		returnVal.setMinEstimateWeight(val.getBigDecimal("minEstimateWeight"));
		returnVal.setCodAllowCod(val.getLong("codAllowCod"));
		returnVal.setCodSurchargeAmount(val.getBigDecimal("codSurchargeAmount"));
		returnVal.setCodSurchargeCurrencyUomId(val.getString("codSurchargeCurrencyUomId"));
		returnVal.setCodSurchargeApplyToPackage(val.getString("codSurchargeApplyToPackage"));
		returnVal.setCodFundsCode(val.getString("codFundsCode"));
		returnVal.setDefaultReturnLabelMemo(val.getLong("defaultReturnLabelMemo"));
		returnVal.setDefaultReturnLabelSubject(val.getLong("defaultReturnLabelSubject"));


return returnVal;

}

public static ShipmentGatewayUps map(HttpServletRequest request) throws Exception {

		ShipmentGatewayUps returnVal = new ShipmentGatewayUps();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("connectUrl"))  {
String buf = request.getParameter("connectUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectUrl(ibuf);
}
		if(paramMap.containsKey("connectTimeout"))  {
String buf = request.getParameter("connectTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectTimeout(ibuf);
}
		if(paramMap.containsKey("shipperNumber"))  {
String buf = request.getParameter("shipperNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setShipperNumber(ibuf);
}
		if(paramMap.containsKey("billShipperAccountNumber"))  {
String buf = request.getParameter("billShipperAccountNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setBillShipperAccountNumber(ibuf);
}
		if(paramMap.containsKey("accessLicenseNumber"))  {
String buf = request.getParameter("accessLicenseNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessLicenseNumber(ibuf);
}
		if(paramMap.containsKey("accessUserId"))  {
String buf = request.getParameter("accessUserId");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessUserId(ibuf);
}
		if(paramMap.containsKey("accessPassword"))  {
String buf = request.getParameter("accessPassword");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessPassword(ibuf);
}
		if(paramMap.containsKey("saveCertInfo"))  {
returnVal.setSaveCertInfo(request.getParameter("saveCertInfo"));
}
		if(paramMap.containsKey("saveCertPath"))  {
String buf = request.getParameter("saveCertPath");
Long ibuf = Long.parseLong(buf);
returnVal.setSaveCertPath(ibuf);
}
		if(paramMap.containsKey("shipperPickupType"))  {
returnVal.setShipperPickupType(request.getParameter("shipperPickupType"));
}
		if(paramMap.containsKey("customerClassification"))  {
returnVal.setCustomerClassification(request.getParameter("customerClassification"));
}
		if(paramMap.containsKey("maxEstimateWeight"))  {
String buf = request.getParameter("maxEstimateWeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxEstimateWeight(bd);
}
		if(paramMap.containsKey("minEstimateWeight"))  {
String buf = request.getParameter("minEstimateWeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinEstimateWeight(bd);
}
		if(paramMap.containsKey("codAllowCod"))  {
String buf = request.getParameter("codAllowCod");
Long ibuf = Long.parseLong(buf);
returnVal.setCodAllowCod(ibuf);
}
		if(paramMap.containsKey("codSurchargeAmount"))  {
String buf = request.getParameter("codSurchargeAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCodSurchargeAmount(bd);
}
		if(paramMap.containsKey("codSurchargeCurrencyUomId"))  {
returnVal.setCodSurchargeCurrencyUomId(request.getParameter("codSurchargeCurrencyUomId"));
}
		if(paramMap.containsKey("codSurchargeApplyToPackage"))  {
returnVal.setCodSurchargeApplyToPackage(request.getParameter("codSurchargeApplyToPackage"));
}
		if(paramMap.containsKey("codFundsCode"))  {
returnVal.setCodFundsCode(request.getParameter("codFundsCode"));
}
		if(paramMap.containsKey("defaultReturnLabelMemo"))  {
String buf = request.getParameter("defaultReturnLabelMemo");
Long ibuf = Long.parseLong(buf);
returnVal.setDefaultReturnLabelMemo(ibuf);
}
		if(paramMap.containsKey("defaultReturnLabelSubject"))  {
String buf = request.getParameter("defaultReturnLabelSubject");
Long ibuf = Long.parseLong(buf);
returnVal.setDefaultReturnLabelSubject(ibuf);
}
return returnVal;

}
}
