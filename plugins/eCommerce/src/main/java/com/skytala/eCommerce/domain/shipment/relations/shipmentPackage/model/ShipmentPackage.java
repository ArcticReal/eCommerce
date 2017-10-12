package com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.mapper.ShipmentPackageMapper;

public class ShipmentPackage implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentPackageSeqId;
private String shipmentBoxTypeId;
private Timestamp dateCreated;
private BigDecimal boxLength;
private BigDecimal boxHeight;
private BigDecimal boxWidth;
private String dimensionUomId;
private BigDecimal weight;
private String weightUomId;
private BigDecimal insuredValue;

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentPackageSeqId() {
return shipmentPackageSeqId;
}

public void setShipmentPackageSeqId(String  shipmentPackageSeqId) {
this.shipmentPackageSeqId = shipmentPackageSeqId;
}

public String getShipmentBoxTypeId() {
return shipmentBoxTypeId;
}

public void setShipmentBoxTypeId(String  shipmentBoxTypeId) {
this.shipmentBoxTypeId = shipmentBoxTypeId;
}

public Timestamp getDateCreated() {
return dateCreated;
}

public void setDateCreated(Timestamp  dateCreated) {
this.dateCreated = dateCreated;
}

public BigDecimal getBoxLength() {
return boxLength;
}

public void setBoxLength(BigDecimal  boxLength) {
this.boxLength = boxLength;
}

public BigDecimal getBoxHeight() {
return boxHeight;
}

public void setBoxHeight(BigDecimal  boxHeight) {
this.boxHeight = boxHeight;
}

public BigDecimal getBoxWidth() {
return boxWidth;
}

public void setBoxWidth(BigDecimal  boxWidth) {
this.boxWidth = boxWidth;
}

public String getDimensionUomId() {
return dimensionUomId;
}

public void setDimensionUomId(String  dimensionUomId) {
this.dimensionUomId = dimensionUomId;
}

public BigDecimal getWeight() {
return weight;
}

public void setWeight(BigDecimal  weight) {
this.weight = weight;
}

public String getWeightUomId() {
return weightUomId;
}

public void setWeightUomId(String  weightUomId) {
this.weightUomId = weightUomId;
}

public BigDecimal getInsuredValue() {
return insuredValue;
}

public void setInsuredValue(BigDecimal  insuredValue) {
this.insuredValue = insuredValue;
}


public Map<String, Object> mapAttributeField() {
return ShipmentPackageMapper.map(this);
}
}
