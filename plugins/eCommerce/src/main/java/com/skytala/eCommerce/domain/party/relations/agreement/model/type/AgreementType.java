package com.skytala.eCommerce.domain.party.relations.agreement.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.type.AgreementTypeMapper;

public class AgreementType implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getAgreementTypeId() {
return agreementTypeId;
}

public void setAgreementTypeId(String  agreementTypeId) {
this.agreementTypeId = agreementTypeId;
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
return AgreementTypeMapper.map(this);
}
}
