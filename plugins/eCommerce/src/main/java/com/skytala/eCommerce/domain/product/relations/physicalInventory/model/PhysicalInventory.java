package com.skytala.eCommerce.domain.product.relations.physicalInventory.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.mapper.PhysicalInventoryMapper;

public class PhysicalInventory implements Serializable{

private static final long serialVersionUID = 1L;
private String physicalInventoryId;
private Timestamp physicalInventoryDate;
private String partyId;
private String generalComments;

public String getPhysicalInventoryId() {
return physicalInventoryId;
}

public void setPhysicalInventoryId(String  physicalInventoryId) {
this.physicalInventoryId = physicalInventoryId;
}

public Timestamp getPhysicalInventoryDate() {
return physicalInventoryDate;
}

public void setPhysicalInventoryDate(Timestamp  physicalInventoryDate) {
this.physicalInventoryDate = physicalInventoryDate;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getGeneralComments() {
return generalComments;
}

public void setGeneralComments(String  generalComments) {
this.generalComments = generalComments;
}


public Map<String, Object> mapAttributeField() {
return PhysicalInventoryMapper.map(this);
}
}
