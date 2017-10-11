package com.skytala.eCommerce.domain.party.relations.agreementItemType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.mapper.AgreementItemTypeMapper;

public class AgreementItemType implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementItemTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getAgreementItemTypeId() {
return agreementItemTypeId;
}

public void setAgreementItemTypeId(String  agreementItemTypeId) {
this.agreementItemTypeId = agreementItemTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return AgreementItemTypeMapper.map(this);
}
}
