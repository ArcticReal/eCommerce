package com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.mapper.ProductStoreShipmentMethMapper;

public class ProductStoreShipmentMeth implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreShipMethId;
private String productStoreId;
private String shipmentMethodTypeId;
private String partyId;
private String roleTypeId;
private String companyPartyId;
private BigDecimal minWeight;
private BigDecimal maxWeight;
private BigDecimal minSize;
private BigDecimal maxSize;
private BigDecimal minTotal;
private BigDecimal maxTotal;
private Boolean allowUspsAddr;
private Boolean requireUspsAddr;
private Boolean allowCompanyAddr;
private Boolean requireCompanyAddr;
private Boolean includeNoChargeItems;
private String includeFeatureGroup;
private String excludeFeatureGroup;
private String includeGeoId;
private String excludeGeoId;
private String serviceName;
private String configProps;
private String shipmentCustomMethodId;
private String shipmentGatewayConfigId;
private Long sequenceNumber;
private BigDecimal allowancePercent;
private BigDecimal minimumPrice;

public String getProductStoreShipMethId() {
return productStoreShipMethId;
}

public void setProductStoreShipMethId(String  productStoreShipMethId) {
this.productStoreShipMethId = productStoreShipMethId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getCompanyPartyId() {
return companyPartyId;
}

public void setCompanyPartyId(String  companyPartyId) {
this.companyPartyId = companyPartyId;
}

public BigDecimal getMinWeight() {
return minWeight;
}

public void setMinWeight(BigDecimal  minWeight) {
this.minWeight = minWeight;
}

public BigDecimal getMaxWeight() {
return maxWeight;
}

public void setMaxWeight(BigDecimal  maxWeight) {
this.maxWeight = maxWeight;
}

public BigDecimal getMinSize() {
return minSize;
}

public void setMinSize(BigDecimal  minSize) {
this.minSize = minSize;
}

public BigDecimal getMaxSize() {
return maxSize;
}

public void setMaxSize(BigDecimal  maxSize) {
this.maxSize = maxSize;
}

public BigDecimal getMinTotal() {
return minTotal;
}

public void setMinTotal(BigDecimal  minTotal) {
this.minTotal = minTotal;
}

public BigDecimal getMaxTotal() {
return maxTotal;
}

public void setMaxTotal(BigDecimal  maxTotal) {
this.maxTotal = maxTotal;
}

public Boolean getAllowUspsAddr() {
return allowUspsAddr;
}

public void setAllowUspsAddr(Boolean  allowUspsAddr) {
this.allowUspsAddr = allowUspsAddr;
}

public Boolean getRequireUspsAddr() {
return requireUspsAddr;
}

public void setRequireUspsAddr(Boolean  requireUspsAddr) {
this.requireUspsAddr = requireUspsAddr;
}

public Boolean getAllowCompanyAddr() {
return allowCompanyAddr;
}

public void setAllowCompanyAddr(Boolean  allowCompanyAddr) {
this.allowCompanyAddr = allowCompanyAddr;
}

public Boolean getRequireCompanyAddr() {
return requireCompanyAddr;
}

public void setRequireCompanyAddr(Boolean  requireCompanyAddr) {
this.requireCompanyAddr = requireCompanyAddr;
}

public Boolean getIncludeNoChargeItems() {
return includeNoChargeItems;
}

public void setIncludeNoChargeItems(Boolean  includeNoChargeItems) {
this.includeNoChargeItems = includeNoChargeItems;
}

public String getIncludeFeatureGroup() {
return includeFeatureGroup;
}

public void setIncludeFeatureGroup(String  includeFeatureGroup) {
this.includeFeatureGroup = includeFeatureGroup;
}

public String getExcludeFeatureGroup() {
return excludeFeatureGroup;
}

public void setExcludeFeatureGroup(String  excludeFeatureGroup) {
this.excludeFeatureGroup = excludeFeatureGroup;
}

public String getIncludeGeoId() {
return includeGeoId;
}

public void setIncludeGeoId(String  includeGeoId) {
this.includeGeoId = includeGeoId;
}

public String getExcludeGeoId() {
return excludeGeoId;
}

public void setExcludeGeoId(String  excludeGeoId) {
this.excludeGeoId = excludeGeoId;
}

public String getServiceName() {
return serviceName;
}

public void setServiceName(String  serviceName) {
this.serviceName = serviceName;
}

public String getConfigProps() {
return configProps;
}

public void setConfigProps(String  configProps) {
this.configProps = configProps;
}

public String getShipmentCustomMethodId() {
return shipmentCustomMethodId;
}

public void setShipmentCustomMethodId(String  shipmentCustomMethodId) {
this.shipmentCustomMethodId = shipmentCustomMethodId;
}

public String getShipmentGatewayConfigId() {
return shipmentGatewayConfigId;
}

public void setShipmentGatewayConfigId(String  shipmentGatewayConfigId) {
this.shipmentGatewayConfigId = shipmentGatewayConfigId;
}

public Long getSequenceNumber() {
return sequenceNumber;
}

public void setSequenceNumber(Long  sequenceNumber) {
this.sequenceNumber = sequenceNumber;
}

public BigDecimal getAllowancePercent() {
return allowancePercent;
}

public void setAllowancePercent(BigDecimal  allowancePercent) {
this.allowancePercent = allowancePercent;
}

public BigDecimal getMinimumPrice() {
return minimumPrice;
}

public void setMinimumPrice(BigDecimal  minimumPrice) {
this.minimumPrice = minimumPrice;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreShipmentMethMapper.map(this);
}
}
