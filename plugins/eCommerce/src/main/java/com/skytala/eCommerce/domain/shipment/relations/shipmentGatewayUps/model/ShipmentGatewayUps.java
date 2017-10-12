package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.mapper.ShipmentGatewayUpsMapper;

public class ShipmentGatewayUps implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfigId;
private Long connectUrl;
private Long connectTimeout;
private Long shipperNumber;
private Long billShipperAccountNumber;
private Long accessLicenseNumber;
private Long accessUserId;
private Long accessPassword;
private String saveCertInfo;
private Long saveCertPath;
private String shipperPickupType;
private String customerClassification;
private BigDecimal maxEstimateWeight;
private BigDecimal minEstimateWeight;
private Long codAllowCod;
private BigDecimal codSurchargeAmount;
private String codSurchargeCurrencyUomId;
private String codSurchargeApplyToPackage;
private String codFundsCode;
private Long defaultReturnLabelMemo;
private Long defaultReturnLabelSubject;

public String getShipmentGatewayConfigId() {
return shipmentGatewayConfigId;
}

public void setShipmentGatewayConfigId(String  shipmentGatewayConfigId) {
this.shipmentGatewayConfigId = shipmentGatewayConfigId;
}

public Long getConnectUrl() {
return connectUrl;
}

public void setConnectUrl(Long  connectUrl) {
this.connectUrl = connectUrl;
}

public Long getConnectTimeout() {
return connectTimeout;
}

public void setConnectTimeout(Long  connectTimeout) {
this.connectTimeout = connectTimeout;
}

public Long getShipperNumber() {
return shipperNumber;
}

public void setShipperNumber(Long  shipperNumber) {
this.shipperNumber = shipperNumber;
}

public Long getBillShipperAccountNumber() {
return billShipperAccountNumber;
}

public void setBillShipperAccountNumber(Long  billShipperAccountNumber) {
this.billShipperAccountNumber = billShipperAccountNumber;
}

public Long getAccessLicenseNumber() {
return accessLicenseNumber;
}

public void setAccessLicenseNumber(Long  accessLicenseNumber) {
this.accessLicenseNumber = accessLicenseNumber;
}

public Long getAccessUserId() {
return accessUserId;
}

public void setAccessUserId(Long  accessUserId) {
this.accessUserId = accessUserId;
}

public Long getAccessPassword() {
return accessPassword;
}

public void setAccessPassword(Long  accessPassword) {
this.accessPassword = accessPassword;
}

public String getSaveCertInfo() {
return saveCertInfo;
}

public void setSaveCertInfo(String  saveCertInfo) {
this.saveCertInfo = saveCertInfo;
}

public Long getSaveCertPath() {
return saveCertPath;
}

public void setSaveCertPath(Long  saveCertPath) {
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

public Long getCodAllowCod() {
return codAllowCod;
}

public void setCodAllowCod(Long  codAllowCod) {
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

public Long getDefaultReturnLabelMemo() {
return defaultReturnLabelMemo;
}

public void setDefaultReturnLabelMemo(Long  defaultReturnLabelMemo) {
this.defaultReturnLabelMemo = defaultReturnLabelMemo;
}

public Long getDefaultReturnLabelSubject() {
return defaultReturnLabelSubject;
}

public void setDefaultReturnLabelSubject(Long  defaultReturnLabelSubject) {
this.defaultReturnLabelSubject = defaultReturnLabelSubject;
}


public Map<String, Object> mapAttributeField() {
return ShipmentGatewayUpsMapper.map(this);
}
}
