package com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.role.ProdCatalogRoleMapper;

public class ProdCatalogRole implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String roleTypeId;
private String prodCatalogId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

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

public String getProdCatalogId() {
return prodCatalogId;
}

public void setProdCatalogId(String  prodCatalogId) {
this.prodCatalogId = prodCatalogId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProdCatalogRoleMapper.map(this);
}
}
