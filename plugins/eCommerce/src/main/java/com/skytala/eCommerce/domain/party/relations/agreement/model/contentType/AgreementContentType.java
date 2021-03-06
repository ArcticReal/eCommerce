package com.skytala.eCommerce.domain.party.relations.agreement.model.contentType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.contentType.AgreementContentTypeMapper;

public class AgreementContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementContentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getAgreementContentTypeId() {
return agreementContentTypeId;
}

public void setAgreementContentTypeId(String  agreementContentTypeId) {
this.agreementContentTypeId = agreementContentTypeId;
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
return AgreementContentTypeMapper.map(this);
}
}
