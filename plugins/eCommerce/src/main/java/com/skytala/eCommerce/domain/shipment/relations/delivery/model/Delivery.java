package com.skytala.eCommerce.domain.shipment.relations.delivery.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.delivery.mapper.DeliveryMapper;

public class Delivery implements Serializable{

private static final long serialVersionUID = 1L;
private String deliveryId;
private String originFacilityId;
private String destFacilityId;
private Timestamp actualStartDate;
private Timestamp actualArrivalDate;
private Timestamp estimatedStartDate;
private Timestamp estimatedArrivalDate;
private String fixedAssetId;
private BigDecimal startMileage;
private BigDecimal endMileage;
private BigDecimal fuelUsed;

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

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public BigDecimal getStartMileage() {
return startMileage;
}

public void setStartMileage(BigDecimal  startMileage) {
this.startMileage = startMileage;
}

public BigDecimal getEndMileage() {
return endMileage;
}

public void setEndMileage(BigDecimal  endMileage) {
this.endMileage = endMileage;
}

public BigDecimal getFuelUsed() {
return fuelUsed;
}

public void setFuelUsed(BigDecimal  fuelUsed) {
this.fuelUsed = fuelUsed;
}


public Map<String, Object> mapAttributeField() {
return DeliveryMapper.map(this);
}
}
