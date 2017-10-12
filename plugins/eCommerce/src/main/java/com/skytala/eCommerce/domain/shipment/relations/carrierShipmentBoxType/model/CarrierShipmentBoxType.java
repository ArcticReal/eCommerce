package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentBoxType.mapper.CarrierShipmentBoxTypeMapper;

public class CarrierShipmentBoxType implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentBoxTypeId;
private String partyId;
private String packagingTypeCode;
private String oversizeCode;

public String getShipmentBoxTypeId() {
return shipmentBoxTypeId;
}

public void setShipmentBoxTypeId(String  shipmentBoxTypeId) {
this.shipmentBoxTypeId = shipmentBoxTypeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getPackagingTypeCode() {
return packagingTypeCode;
}

public void setPackagingTypeCode(String  packagingTypeCode) {
this.packagingTypeCode = packagingTypeCode;
}

public String getOversizeCode() {
return oversizeCode;
}

public void setOversizeCode(String  oversizeCode) {
this.oversizeCode = oversizeCode;
}


public Map<String, Object> mapAttributeField() {
return CarrierShipmentBoxTypeMapper.map(this);
}
}
