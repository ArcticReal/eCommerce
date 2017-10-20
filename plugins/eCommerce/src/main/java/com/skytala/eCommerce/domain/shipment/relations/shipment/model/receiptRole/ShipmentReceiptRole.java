package com.skytala.eCommerce.domain.shipment.relations.shipment.model.receiptRole;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receiptRole.ShipmentReceiptRoleMapper;

public class ShipmentReceiptRole implements Serializable{

private static final long serialVersionUID = 1L;
private String receiptId;
private String partyId;
private String roleTypeId;

public String getReceiptId() {
return receiptId;
}

public void setReceiptId(String  receiptId) {
this.receiptId = receiptId;
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


public Map<String, Object> mapAttributeField() {
return ShipmentReceiptRoleMapper.map(this);
}
}
