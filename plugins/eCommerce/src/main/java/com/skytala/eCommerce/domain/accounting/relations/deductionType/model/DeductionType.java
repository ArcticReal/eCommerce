package com.skytala.eCommerce.domain.accounting.relations.deductionType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.deductionType.mapper.DeductionTypeMapper;

public class DeductionType implements Serializable{

private static final long serialVersionUID = 1L;
private String deductionTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getDeductionTypeId() {
return deductionTypeId;
}

public void setDeductionTypeId(String  deductionTypeId) {
this.deductionTypeId = deductionTypeId;
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
return DeductionTypeMapper.map(this);
}
}
