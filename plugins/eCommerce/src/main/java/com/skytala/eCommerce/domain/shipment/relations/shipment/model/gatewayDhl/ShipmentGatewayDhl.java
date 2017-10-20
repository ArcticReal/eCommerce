package com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayDhl;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayDhl.ShipmentGatewayDhlMapper;

public class ShipmentGatewayDhl implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfigId;
private Long connectUrl;
private Long connectTimeout;
private String headVersion;
private Long headAction;
private Long accessUserId;
private Long accessPassword;
private Long accessAccountNbr;
private Long accessShippingKey;
private String labelImageFormat;
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

public Long getConnectTimeout() {
return connectTimeout;
}

public void setConnectTimeout(Long  connectTimeout) {
this.connectTimeout = connectTimeout;
}

public String getHeadVersion() {
return headVersion;
}

public void setHeadVersion(String  headVersion) {
this.headVersion = headVersion;
}

public Long getHeadAction() {
return headAction;
}

public void setHeadAction(Long  headAction) {
this.headAction = headAction;
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

public Long getAccessAccountNbr() {
return accessAccountNbr;
}

public void setAccessAccountNbr(Long  accessAccountNbr) {
this.accessAccountNbr = accessAccountNbr;
}

public Long getAccessShippingKey() {
return accessShippingKey;
}

public void setAccessShippingKey(Long  accessShippingKey) {
this.accessShippingKey = accessShippingKey;
}

public String getLabelImageFormat() {
return labelImageFormat;
}

public void setLabelImageFormat(String  labelImageFormat) {
this.labelImageFormat = labelImageFormat;
}

public Long getRateEstimateTemplate() {
return rateEstimateTemplate;
}

public void setRateEstimateTemplate(Long  rateEstimateTemplate) {
this.rateEstimateTemplate = rateEstimateTemplate;
}


public Map<String, Object> mapAttributeField() {
return ShipmentGatewayDhlMapper.map(this);
}
}
