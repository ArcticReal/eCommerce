package com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUps;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUps.ShipmentGatewayUpsMapper;

public class ShipmentGatewayUps implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfigId;
private String connectUrl;
private Long connectTimeout;
private String shipperNumber;
private String billShipperAccountNumber;
private String accessLicenseNumber;
private String accessUserId;
private String accessPassword;
private String saveCertInfo;
private String saveCertPath;
private String shipperPickupType;
private String customerClassification;
private BigDecimal maxEstimateWeight;
private BigDecimal minEstimateWeight;
private String codAllowCod;
private BigDecimal codSurchargeAmount;
private String codSurchargeCurrencyUomId;
private String codSurchargeApplyToPackage;
private String codFundsCode;
private String defaultReturnLabelMemo;
private String defaultReturnLabelSubject;

public String getShipmentGatewayConfigId() {
return shipmentGatewayConfigId;
}

public void setShipmentGatewayConfigId(String  shipmentGatewayConfigId) {
this.shipmentGatewayConfigId = shipmentGatewayConfigId;
}

public String getConnectUrl() {
return connectUrl;
}

public void setConnectUrl(String  connectUrl) {
this.connectUrl = connectUrl;
}

public Long getConnectTimeout() {
return connectTimeout;
}

public void setConnectTimeout(Long  connectTimeout) {
this.connectTimeout = connectTimeout;
}

public String getShipperNumber() {
return shipperNumber;
}

public void setShipperNumber(String  shipperNumber) {
this.shipperNumber = shipperNumber;
}

public String getBillShipperAccountNumber() {
return billShipperAccountNumber;
}

public void setBillShipperAccountNumber(String  billShipperAccountNumber) {
this.billShipperAccountNumber = billShipperAccountNumber;
}

public String getAccessLicenseNumber() {
return accessLicenseNumber;
}

public void setAccessLicenseNumber(String  accessLicenseNumber) {
this.accessLicenseNumber = accessLicenseNumber;
}

public String getAccessUserId() {
return accessUserId;
}

public void setAccessUserId(String  accessUserId) {
this.accessUserId = accessUserId;
}

public String getAccessPassword() {
return accessPassword;
}

public void setAccessPassword(String  accessPassword) {
this.accessPassword = accessPassword;
}

public String getSaveCertInfo() {
return saveCertInfo;
}

public void setSaveCertInfo(String  saveCertInfo) {
this.saveCertInfo = saveCertInfo;
}

public String getSaveCertPath() {
return saveCertPath;
}

public void setSaveCertPath(String  saveCertPath) {
this.saveCertPath = saveCertPath;
}

public String getShipperPickupType() {
return shipperPickupType;
}

public void setShipperPickupType(String  shipperPickupType) {
this.shipperPickupType = shipperPickupType;
}

public String getCustomerClassification() {
return customerClassification;
}

public void setCustomerClassification(String  customerClassification) {
this.customerClassification = customerClassification;
}

public BigDecimal getMaxEstimateWeight() {
return maxEstimateWeight;
}

public void setMaxEstimateWeight(BigDecimal  maxEstimateWeight) {
this.maxEstimateWeight = maxEstimateWeight;
}

public BigDecimal getMinEstimateWeight() {
return minEstimateWeight;
}

public void setMinEstimateWeight(BigDecimal  minEstimateWeight) {
this.minEstimateWeight = minEstimateWeight;
}

public String getCodAllowCod() {
return codAllowCod;
}

public void setCodAllowCod(String  codAllowCod) {
this.codAllowCod = codAllowCod;
}

public BigDecimal getCodSurchargeAmount() {
return codSurchargeAmount;
}

public void setCodSurchargeAmount(BigDecimal  codSurchargeAmount) {
this.codSurchargeAmount = codSurchargeAmount;
}

public String getCodSurchargeCurrencyUomId() {
return codSurchargeCurrencyUomId;
}

public void setCodSurchargeCurrencyUomId(String  codSurchargeCurrencyUomId) {
this.codSurchargeCurrencyUomId = codSurchargeCurrencyUomId;
}

public String getCodSurchargeApplyToPackage() {
return codSurchargeApplyToPackage;
}

public void setCodSurchargeApplyToPackage(String  codSurchargeApplyToPackage) {
this.codSurchargeApplyToPackage = codSurchargeApplyToPackage;
}

public String getCodFundsCode() {
return codFundsCode;
}

public void setCodFundsCode(String  codFundsCode) {
this.codFundsCode = codFundsCode;
}

public String getDefaultReturnLabelMemo() {
return defaultReturnLabelMemo;
}

public void setDefaultReturnLabelMemo(String  defaultReturnLabelMemo) {
this.defaultReturnLabelMemo = defaultReturnLabelMemo;
}

public String getDefaultReturnLabelSubject() {
return defaultReturnLabelSubject;
}

public void setDefaultReturnLabelSubject(String  defaultReturnLabelSubject) {
this.defaultReturnLabelSubject = defaultReturnLabelSubject;
}


public Map<String, Object> mapAttributeField() {
return ShipmentGatewayUpsMapper.map(this);
}
}
