package com.skytala.eCommerce.domain.party.relations.party.model.profileDefault;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.profileDefault.PartyProfileDefaultMapper;

public class PartyProfileDefault implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String productStoreId;
private String defaultShipAddr;
private String defaultBillAddr;
private String defaultPayMeth;
private String defaultShipMeth;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getDefaultShipAddr() {
return defaultShipAddr;
}

public void setDefaultShipAddr(String  defaultShipAddr) {
this.defaultShipAddr = defaultShipAddr;
}

public String getDefaultBillAddr() {
return defaultBillAddr;
}

public void setDefaultBillAddr(String  defaultBillAddr) {
this.defaultBillAddr = defaultBillAddr;
}

public String getDefaultPayMeth() {
return defaultPayMeth;
}

public void setDefaultPayMeth(String  defaultPayMeth) {
this.defaultPayMeth = defaultPayMeth;
}

public String getDefaultShipMeth() {
return defaultShipMeth;
}

public void setDefaultShipMeth(String  defaultShipMeth) {
this.defaultShipMeth = defaultShipMeth;
}


public Map<String, Object> mapAttributeField() {
return PartyProfileDefaultMapper.map(this);
}
}
