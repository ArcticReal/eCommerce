package com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.carrierShipment.FacilityCarrierShipmentMapper;

public class FacilityCarrierShipment implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityId;
private String partyId;
private String roleTypeId;
private String shipmentMethodTypeId;

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
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

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}


public Map<String, Object> mapAttributeField() {
return FacilityCarrierShipmentMapper.map(this);
}
}
