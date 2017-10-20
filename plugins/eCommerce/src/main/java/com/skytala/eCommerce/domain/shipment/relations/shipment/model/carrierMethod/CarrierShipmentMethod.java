package com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierMethod;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierMethod.CarrierShipmentMethodMapper;

public class CarrierShipmentMethod implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentMethodTypeId;
private String partyId;
private String roleTypeId;
private Long sequenceNumber;
private String carrierServiceCode;

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

public Long getSequenceNumber() {
return sequenceNumber;
}

public void setSequenceNumber(Long  sequenceNumber) {
this.sequenceNumber = sequenceNumber;
}

public String getCarrierServiceCode() {
return carrierServiceCode;
}

public void setCarrierServiceCode(String  carrierServiceCode) {
this.carrierServiceCode = carrierServiceCode;
}


public Map<String, Object> mapAttributeField() {
return CarrierShipmentMethodMapper.map(this);
}
}
