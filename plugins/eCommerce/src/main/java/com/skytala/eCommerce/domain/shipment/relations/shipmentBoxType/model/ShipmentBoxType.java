package com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.mapper.ShipmentBoxTypeMapper;

public class ShipmentBoxType implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentBoxTypeId;
private String description;
private String dimensionUomId;
private BigDecimal boxLength;
private BigDecimal boxWidth;
private BigDecimal boxHeight;
private String weightUomId;
private BigDecimal boxWeight;

public String getShipmentBoxTypeId() {
return shipmentBoxTypeId;
}

public void setShipmentBoxTypeId(String  shipmentBoxTypeId) {
this.shipmentBoxTypeId = shipmentBoxTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getDimensionUomId() {
return dimensionUomId;
}

public void setDimensionUomId(String  dimensionUomId) {
this.dimensionUomId = dimensionUomId;
}

public BigDecimal getBoxLength() {
return boxLength;
}

public void setBoxLength(BigDecimal  boxLength) {
this.boxLength = boxLength;
}

public BigDecimal getBoxWidth() {
return boxWidth;
}

public void setBoxWidth(BigDecimal  boxWidth) {
this.boxWidth = boxWidth;
}

public BigDecimal getBoxHeight() {
return boxHeight;
}

public void setBoxHeight(BigDecimal  boxHeight) {
this.boxHeight = boxHeight;
}

public String getWeightUomId() {
return weightUomId;
}

public void setWeightUomId(String  weightUomId) {
this.weightUomId = weightUomId;
}

public BigDecimal getBoxWeight() {
return boxWeight;
}

public void setBoxWeight(BigDecimal  boxWeight) {
this.boxWeight = boxWeight;
}


public Map<String, Object> mapAttributeField() {
return ShipmentBoxTypeMapper.map(this);
}
}
