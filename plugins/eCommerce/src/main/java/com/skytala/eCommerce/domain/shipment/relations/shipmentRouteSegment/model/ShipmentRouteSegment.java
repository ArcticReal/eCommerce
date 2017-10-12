package com.skytala.eCommerce.domain.shipment.relations.shipmentRouteSegment.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentRouteSegment.mapper.ShipmentRouteSegmentMapper;

public class ShipmentRouteSegment implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentRouteSegmentId;
private String deliveryId;
private String originFacilityId;
private String destFacilityId;
private String originContactMechId;
private String originTelecomNumberId;
private String destContactMechId;
private String destTelecomNumberId;
private String carrierPartyId;
private String shipmentMethodTypeId;
private String carrierServiceStatusId;
private String carrierDeliveryZone;
private String carrierRestrictionCodes;
private String carrierRestrictionDesc;
private BigDecimal billingWeight;
private String billingWeightUomId;
private BigDecimal actualTransportCost;
private BigDecimal actualServiceCost;
private BigDecimal actualOtherCost;
private BigDecimal actualCost;
private String currencyUomId;
private Timestamp actualStartDate;
private Timestamp actualArrivalDate;
private Timestamp estimatedStartDate;
private Timestamp estimatedArrivalDate;
private String trackingIdNumber;
private String trackingDigest;
private String updatedByUserLoginId;
private Timestamp lastUpdatedDate;
private String homeDeliveryType;
private Timestamp homeDeliveryDate;
private String thirdPartyAccountNumber;
private String thirdPartyPostalCode;
private String thirdPartyCountryGeoCode;
private byte[] upsHighValueReport;

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentRouteSegmentId() {
return shipmentRouteSegmentId;
}

public void setShipmentRouteSegmentId(String  shipmentRouteSegmentId) {
this.shipmentRouteSegmentId = shipmentRouteSegmentId;
}

public String getDeliveryId() {
return deliveryId;
}

public void setDeliveryId(String  deliveryId) {
this.deliveryId = deliveryId;
}

public String getOriginFacilityId() {
return originFacilityId;
}

public void setOriginFacilityId(String  originFacilityId) {
this.originFacilityId = originFacilityId;
}

public String getDestFacilityId() {
return destFacilityId;
}

public void setDestFacilityId(String  destFacilityId) {
this.destFacilityId = destFacilityId;
}

public String getOriginContactMechId() {
return originContactMechId;
}

public void setOriginContactMechId(String  originContactMechId) {
this.originContactMechId = originContactMechId;
}

public String getOriginTelecomNumberId() {
return originTelecomNumberId;
}

public void setOriginTelecomNumberId(String  originTelecomNumberId) {
this.originTelecomNumberId = originTelecomNumberId;
}

public String getDestContactMechId() {
return destContactMechId;
}

public void setDestContactMechId(String  destContactMechId) {
this.destContactMechId = destContactMechId;
}

public String getDestTelecomNumberId() {
return destTelecomNumberId;
}

public void setDestTelecomNumberId(String  destTelecomNumberId) {
this.destTelecomNumberId = destTelecomNumberId;
}

public String getCarrierPartyId() {
return carrierPartyId;
}

public void setCarrierPartyId(String  carrierPartyId) {
this.carrierPartyId = carrierPartyId;
}

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getCarrierServiceStatusId() {
return carrierServiceStatusId;
}

public void setCarrierServiceStatusId(String  carrierServiceStatusId) {
this.carrierServiceStatusId = carrierServiceStatusId;
}

public String getCarrierDeliveryZone() {
return carrierDeliveryZone;
}

public void setCarrierDeliveryZone(String  carrierDeliveryZone) {
this.carrierDeliveryZone = carrierDeliveryZone;
}

public String getCarrierRestrictionCodes() {
return carrierRestrictionCodes;
}

public void setCarrierRestrictionCodes(String  carrierRestrictionCodes) {
this.carrierRestrictionCodes = carrierRestrictionCodes;
}

public String getCarrierRestrictionDesc() {
return carrierRestrictionDesc;
}

public void setCarrierRestrictionDesc(String  carrierRestrictionDesc) {
this.carrierRestrictionDesc = carrierRestrictionDesc;
}

public BigDecimal getBillingWeight() {
return billingWeight;
}

public void setBillingWeight(BigDecimal  billingWeight) {
this.billingWeight = billingWeight;
}

public String getBillingWeightUomId() {
return billingWeightUomId;
}

public void setBillingWeightUomId(String  billingWeightUomId) {
this.billingWeightUomId = billingWeightUomId;
}

public BigDecimal getActualTransportCost() {
return actualTransportCost;
}

public void setActualTransportCost(BigDecimal  actualTransportCost) {
this.actualTransportCost = actualTransportCost;
}

public BigDecimal getActualServiceCost() {
return actualServiceCost;
}

public void setActualServiceCost(BigDecimal  actualServiceCost) {
this.actualServiceCost = actualServiceCost;
}

public BigDecimal getActualOtherCost() {
return actualOtherCost;
}

public void setActualOtherCost(BigDecimal  actualOtherCost) {
this.actualOtherCost = actualOtherCost;
}

public BigDecimal getActualCost() {
return actualCost;
}

public void setActualCost(BigDecimal  actualCost) {
this.actualCost = actualCost;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public Timestamp getActualStartDate() {
return actualStartDate;
}

public void setActualStartDate(Timestamp  actualStartDate) {
this.actualStartDate = actualStartDate;
}

public Timestamp getActualArrivalDate() {
return actualArrivalDate;
}

public void setActualArrivalDate(Timestamp  actualArrivalDate) {
this.actualArrivalDate = actualArrivalDate;
}

public Timestamp getEstimatedStartDate() {
return estimatedStartDate;
}

public void setEstimatedStartDate(Timestamp  estimatedStartDate) {
this.estimatedStartDate = estimatedStartDate;
}

public Timestamp getEstimatedArrivalDate() {
return estimatedArrivalDate;
}

public void setEstimatedArrivalDate(Timestamp  estimatedArrivalDate) {
this.estimatedArrivalDate = estimatedArrivalDate;
}

public String getTrackingIdNumber() {
return trackingIdNumber;
}

public void setTrackingIdNumber(String  trackingIdNumber) {
this.trackingIdNumber = trackingIdNumber;
}

public String getTrackingDigest() {
return trackingDigest;
}

public void setTrackingDigest(String  trackingDigest) {
this.trackingDigest = trackingDigest;
}

public String getUpdatedByUserLoginId() {
return updatedByUserLoginId;
}

public void setUpdatedByUserLoginId(String  updatedByUserLoginId) {
this.updatedByUserLoginId = updatedByUserLoginId;
}

public Timestamp getLastUpdatedDate() {
return lastUpdatedDate;
}

public void setLastUpdatedDate(Timestamp  lastUpdatedDate) {
this.lastUpdatedDate = lastUpdatedDate;
}

public String getHomeDeliveryType() {
return homeDeliveryType;
}

public void setHomeDeliveryType(String  homeDeliveryType) {
this.homeDeliveryType = homeDeliveryType;
}

public Timestamp getHomeDeliveryDate() {
return homeDeliveryDate;
}

public void setHomeDeliveryDate(Timestamp  homeDeliveryDate) {
this.homeDeliveryDate = homeDeliveryDate;
}

public String getThirdPartyAccountNumber() {
return thirdPartyAccountNumber;
}

public void setThirdPartyAccountNumber(String  thirdPartyAccountNumber) {
this.thirdPartyAccountNumber = thirdPartyAccountNumber;
}

public String getThirdPartyPostalCode() {
return thirdPartyPostalCode;
}

public void setThirdPartyPostalCode(String  thirdPartyPostalCode) {
this.thirdPartyPostalCode = thirdPartyPostalCode;
}

public String getThirdPartyCountryGeoCode() {
return thirdPartyCountryGeoCode;
}

public void setThirdPartyCountryGeoCode(String  thirdPartyCountryGeoCode) {
this.thirdPartyCountryGeoCode = thirdPartyCountryGeoCode;
}

public byte[] getUpsHighValueReport() {
return upsHighValueReport;
}

public void setUpsHighValueReport(byte[]  upsHighValueReport) {
this.upsHighValueReport = upsHighValueReport;
}


public Map<String, Object> mapAttributeField() {
return ShipmentRouteSegmentMapper.map(this);
}
}
