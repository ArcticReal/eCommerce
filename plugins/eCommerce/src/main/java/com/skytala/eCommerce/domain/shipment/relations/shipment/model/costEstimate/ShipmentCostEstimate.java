package com.skytala.eCommerce.domain.shipment.relations.shipment.model.costEstimate;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.costEstimate.ShipmentCostEstimateMapper;

public class ShipmentCostEstimate implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentCostEstimateId;
private String shipmentMethodTypeId;
private String carrierPartyId;
private String carrierRoleTypeId;
private String productStoreShipMethId;
private String productStoreId;
private String partyId;
private String roleTypeId;
private String geoIdTo;
private String geoIdFrom;
private String weightBreakId;
private String weightUomId;
private BigDecimal weightUnitPrice;
private String quantityBreakId;
private String quantityUomId;
private BigDecimal quantityUnitPrice;
private String priceBreakId;
private String priceUomId;
private BigDecimal priceUnitPrice;
private BigDecimal orderFlatPrice;
private BigDecimal orderPricePercent;
private BigDecimal orderItemFlatPrice;
private BigDecimal shippingPricePercent;
private String productFeatureGroupId;
private BigDecimal oversizeUnit;
private BigDecimal oversizePrice;
private BigDecimal featurePercent;
private BigDecimal featurePrice;

public String getShipmentCostEstimateId() {
return shipmentCostEstimateId;
}

public void setShipmentCostEstimateId(String  shipmentCostEstimateId) {
this.shipmentCostEstimateId = shipmentCostEstimateId;
}

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getCarrierPartyId() {
return carrierPartyId;
}

public void setCarrierPartyId(String  carrierPartyId) {
this.carrierPartyId = carrierPartyId;
}

public String getCarrierRoleTypeId() {
return carrierRoleTypeId;
}

public void setCarrierRoleTypeId(String  carrierRoleTypeId) {
this.carrierRoleTypeId = carrierRoleTypeId;
}

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

public String getGeoIdTo() {
return geoIdTo;
}

public void setGeoIdTo(String  geoIdTo) {
this.geoIdTo = geoIdTo;
}

public String getGeoIdFrom() {
return geoIdFrom;
}

public void setGeoIdFrom(String  geoIdFrom) {
this.geoIdFrom = geoIdFrom;
}

public String getWeightBreakId() {
return weightBreakId;
}

public void setWeightBreakId(String  weightBreakId) {
this.weightBreakId = weightBreakId;
}

public String getWeightUomId() {
return weightUomId;
}

public void setWeightUomId(String  weightUomId) {
this.weightUomId = weightUomId;
}

public BigDecimal getWeightUnitPrice() {
return weightUnitPrice;
}

public void setWeightUnitPrice(BigDecimal  weightUnitPrice) {
this.weightUnitPrice = weightUnitPrice;
}

public String getQuantityBreakId() {
return quantityBreakId;
}

public void setQuantityBreakId(String  quantityBreakId) {
this.quantityBreakId = quantityBreakId;
}

public String getQuantityUomId() {
return quantityUomId;
}

public void setQuantityUomId(String  quantityUomId) {
this.quantityUomId = quantityUomId;
}

public BigDecimal getQuantityUnitPrice() {
return quantityUnitPrice;
}

public void setQuantityUnitPrice(BigDecimal  quantityUnitPrice) {
this.quantityUnitPrice = quantityUnitPrice;
}

public String getPriceBreakId() {
return priceBreakId;
}

public void setPriceBreakId(String  priceBreakId) {
this.priceBreakId = priceBreakId;
}

public String getPriceUomId() {
return priceUomId;
}

public void setPriceUomId(String  priceUomId) {
this.priceUomId = priceUomId;
}

public BigDecimal getPriceUnitPrice() {
return priceUnitPrice;
}

public void setPriceUnitPrice(BigDecimal  priceUnitPrice) {
this.priceUnitPrice = priceUnitPrice;
}

public BigDecimal getOrderFlatPrice() {
return orderFlatPrice;
}

public void setOrderFlatPrice(BigDecimal  orderFlatPrice) {
this.orderFlatPrice = orderFlatPrice;
}

public BigDecimal getOrderPricePercent() {
return orderPricePercent;
}

public void setOrderPricePercent(BigDecimal  orderPricePercent) {
this.orderPricePercent = orderPricePercent;
}

public BigDecimal getOrderItemFlatPrice() {
return orderItemFlatPrice;
}

public void setOrderItemFlatPrice(BigDecimal  orderItemFlatPrice) {
this.orderItemFlatPrice = orderItemFlatPrice;
}

public BigDecimal getShippingPricePercent() {
return shippingPricePercent;
}

public void setShippingPricePercent(BigDecimal  shippingPricePercent) {
this.shippingPricePercent = shippingPricePercent;
}

public String getProductFeatureGroupId() {
return productFeatureGroupId;
}

public void setProductFeatureGroupId(String  productFeatureGroupId) {
this.productFeatureGroupId = productFeatureGroupId;
}

public BigDecimal getOversizeUnit() {
return oversizeUnit;
}

public void setOversizeUnit(BigDecimal  oversizeUnit) {
this.oversizeUnit = oversizeUnit;
}

public BigDecimal getOversizePrice() {
return oversizePrice;
}

public void setOversizePrice(BigDecimal  oversizePrice) {
this.oversizePrice = oversizePrice;
}

public BigDecimal getFeaturePercent() {
return featurePercent;
}

public void setFeaturePercent(BigDecimal  featurePercent) {
this.featurePercent = featurePercent;
}

public BigDecimal getFeaturePrice() {
return featurePrice;
}

public void setFeaturePrice(BigDecimal  featurePrice) {
this.featurePrice = featurePrice;
}


public Map<String, Object> mapAttributeField() {
return ShipmentCostEstimateMapper.map(this);
}
}
