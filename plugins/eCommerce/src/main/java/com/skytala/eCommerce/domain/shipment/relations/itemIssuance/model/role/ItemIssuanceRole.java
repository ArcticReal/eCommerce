package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper.role.ItemIssuanceRoleMapper;

public class ItemIssuanceRole implements Serializable{

private static final long serialVersionUID = 1L;
private String itemIssuanceId;
private String partyId;
private String roleTypeId;

public String getItemIssuanceId() {
return itemIssuanceId;
}

public void setItemIssuanceId(String  itemIssuanceId) {
this.itemIssuanceId = itemIssuanceId;
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
return ItemIssuanceRoleMapper.map(this);
}
}
