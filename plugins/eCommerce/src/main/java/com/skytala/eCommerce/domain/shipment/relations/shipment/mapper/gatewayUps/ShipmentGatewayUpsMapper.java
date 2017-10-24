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
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("shipperNumber") != null) {
			returnVal.setShipperNumber((String) fields.get("shipperNumber"));
}

		if(fields.get("billShipperAccountNumber") != null) {
			returnVal.setBillShipperAccountNumber((String) fields.get("billShipperAccountNumber"));
}

		if(fields.get("accessLicenseNumber") != null) {
			returnVal.setAccessLicenseNumber((String) fields.get("accessLicenseNumber"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((String) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((String) fields.get("accessPassword"));
}

		if(fields.get("saveCertInfo") != null) {
			returnVal.setSaveCertInfo((String) fields.get("saveCertInfo"));
}

		if(fields.get("saveCertPath") != null) {
			returnVal.setSaveCertPath((String) fields.get("saveCertPath"));
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
			returnVal.setCodAllowCod((String) fields.get("codAllowCod"));
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
			returnVal.setDefaultReturnLabelMemo((String) fields.get("defaultReturnLabelMemo"));
}

		if(fields.get("defaultReturnLabelSubject") != null) {
			returnVal.setDefaultReturnLabelSubject((String) fields.get("defaultReturnLabelSubject"));
}


		return returnVal;
 } 
	public static ShipmentGatewayUps mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayUps returnVal = new ShipmentGatewayUps();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
}

		if(fields.get("connectTimeout") != null) {
String buf;
buf = fields.get("connectTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectTimeout(ibuf);
}

		if(fields.get("shipperNumber") != null) {
			returnVal.setShipperNumber((String) fields.get("shipperNumber"));
}

		if(fields.get("billShipperAccountNumber") != null) {
			returnVal.setBillShipperAccountNumber((String) fields.get("billShipperAccountNumber"));
}

		if(fields.get("accessLicenseNumber") != null) {
			returnVal.setAccessLicenseNumber((String) fields.get("accessLicenseNumber"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((String) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((String) fields.get("accessPassword"));
}

		if(fields.get("saveCertInfo") != null) {
			returnVal.setSaveCertInfo((String) fields.get("saveCertInfo"));
}

		if(fields.get("saveCertPath") != null) {
			returnVal.setSaveCertPath((String) fields.get("saveCertPath"));
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
			returnVal.setCodAllowCod((String) fields.get("codAllowCod"));
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
			returnVal.setDefaultReturnLabelMemo((String) fields.get("defaultReturnLabelMemo"));
}

		if(fields.get("defaultReturnLabelSubject") != null) {
			returnVal.setDefaultReturnLabelSubject((String) fields.get("defaultReturnLabelSubject"));
}


		return returnVal;
 } 
	public static ShipmentGatewayUps map(GenericValue val) {

ShipmentGatewayUps returnVal = new ShipmentGatewayUps();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setConnectUrl(val.getString("connectUrl"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setShipperNumber(val.getString("shipperNumber"));
		returnVal.setBillShipperAccountNumber(val.getString("billShipperAccountNumber"));
		returnVal.setAccessLicenseNumber(val.getString("accessLicenseNumber"));
		returnVal.setAccessUserId(val.getString("accessUserId"));
		returnVal.setAccessPassword(val.getString("accessPassword"));
		returnVal.setSaveCertInfo(val.getString("saveCertInfo"));
		returnVal.setSaveCertPath(val.getString("saveCertPath"));
		returnVal.setShipperPickupType(val.getString("shipperPickupType"));
		returnVal.setCustomerClassification(val.getString("customerClassification"));
		returnVal.setMaxEstimateWeight(val.getBigDecimal("maxEstimateWeight"));
		returnVal.setMinEstimateWeight(val.getBigDecimal("minEstimateWeight"));
		returnVal.setCodAllowCod(val.getString("codAllowCod"));
		returnVal.setCodSurchargeAmount(val.getBigDecimal("codSurchargeAmount"));
		returnVal.setCodSurchargeCurrencyUomId(val.getString("codSurchargeCurrencyUomId"));
		returnVal.setCodSurchargeApplyToPackage(val.getString("codSurchargeApplyToPackage"));
		returnVal.setCodFundsCode(val.getString("codFundsCode"));
		returnVal.setDefaultReturnLabelMemo(val.getString("defaultReturnLabelMemo"));
		returnVal.setDefaultReturnLabelSubject(val.getString("defaultReturnLabelSubject"));


return returnVal;

}

public static ShipmentGatewayUps map(HttpServletRequest request) throws Exception {

		ShipmentGatewayUps returnVal = new ShipmentGatewayUps();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("connectUrl"))  {
returnVal.setConnectUrl(request.getParameter("connectUrl"));
}
		if(paramMap.containsKey("connectTimeout"))  {
String buf = request.getParameter("connectTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectTimeout(ibuf);
}
		if(paramMap.containsKey("shipperNumber"))  {
returnVal.setShipperNumber(request.getParameter("shipperNumber"));
}
		if(paramMap.containsKey("billShipperAccountNumber"))  {
returnVal.setBillShipperAccountNumber(request.getParameter("billShipperAccountNumber"));
}
		if(paramMap.containsKey("accessLicenseNumber"))  {
returnVal.setAccessLicenseNumber(request.getParameter("accessLicenseNumber"));
}
		if(paramMap.containsKey("accessUserId"))  {
returnVal.setAccessUserId(request.getParameter("accessUserId"));
}
		if(paramMap.containsKey("accessPassword"))  {
returnVal.setAccessPassword(request.getParameter("accessPassword"));
}
		if(paramMap.containsKey("saveCertInfo"))  {
returnVal.setSaveCertInfo(request.getParameter("saveCertInfo"));
}
		if(paramMap.containsKey("saveCertPath"))  {
returnVal.setSaveCertPath(request.getParameter("saveCertPath"));
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
returnVal.setCodAllowCod(request.getParameter("codAllowCod"));
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
returnVal.setDefaultReturnLabelMemo(request.getParameter("defaultReturnLabelMemo"));
}
		if(paramMap.containsKey("defaultReturnLabelSubject"))  {
returnVal.setDefaultReturnLabelSubject(request.getParameter("defaultReturnLabelSubject"));
}
return returnVal;

}
}
