package com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayFedex;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayFedex.ShipmentGatewayFedexMapper;

public class ShipmentGatewayFedex implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfigId;
private Long connectUrl;
private Long connectSoapUrl;
private Long connectTimeout;
private Long accessAccountNbr;
private Long accessMeterNumber;
private Long accessUserKey;
private Long accessUserPwd;
private String labelImageType;
private Long defaultDropoffType;
private Long defaultPackagingType;
private Long templateShipment;
private Long templateSubscription;
private Long rateEstimateTemplate;

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

public Long getConnectSoapUrl() {
return connectSoapUrl;
}

public void setConnectSoapUrl(Long  connectSoapUrl) {
this.connectSoapUrl = connectSoapUrl;
}

public Long getConnectTimeout() {
return connectTimeout;
}

public void setConnectTimeout(Long  connectTimeout) {
this.connectTimeout = connectTimeout;
}

public Long getAccessAccountNbr() {
return accessAccountNbr;
}

public void setAccessAccountNbr(Long  accessAccountNbr) {
this.accessAccountNbr = accessAccountNbr;
}

public Long getAccessMeterNumber() {
return accessMeterNumber;
}

public void setAccessMeterNumber(Long  accessMeterNumber) {
this.accessMeterNumber = accessMeterNumber;
}

public Long getAccessUserKey() {
return accessUserKey;
}

public void setAccessUserKey(Long  accessUserKey) {
this.accessUserKey = accessUserKey;
}

public Long getAccessUserPwd() {
return accessUserPwd;
}

public void setAccessUserPwd(Long  accessUserPwd) {
this.accessUserPwd = accessUserPwd;
}

public String getLabelImageType() {
return labelImageType;
}

public void setLabelImageType(String  labelImageType) {
this.labelImageType = labelImageType;
}

public Long getDefaultDropoffType() {
return defaultDropoffType;
}

public void setDefaultDropoffType(Long  defaultDropoffType) {
this.defaultDropoffType = defaultDropoffType;
}

public Long getDefaultPackagingType() {
return defaultPackagingType;
}

public void setDefaultPackagingType(Long  defaultPackagingType) {
this.defaultPackagingType = defaultPackagingType;
}

public Long getTemplateShipment() {
return templateShipment;
}

public void setTemplateShipment(Long  templateShipment) {
this.templateShipment = templateShipment;
}

public Long getTemplateSubscription() {
return templateSubscription;
}

public void setTemplateSubscription(Long  templateSubscription) {
this.templateSubscription = templateSubscription;
}

public Long getRateEstimateTemplate() {
return rateEstimateTemplate;
}

public void setRateEstimateTemplate(Long  rateEstimateTemplate) {
this.rateEstimateTemplate = rateEstimateTemplate;
}


public Map<String, Object> mapAttributeField() {
return ShipmentGatewayFedexMapper.map(this);
}
}
