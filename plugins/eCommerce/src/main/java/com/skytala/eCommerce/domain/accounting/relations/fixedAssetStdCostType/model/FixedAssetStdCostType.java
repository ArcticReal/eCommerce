package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.mapper.FixedAssetStdCostTypeMapper;

public class FixedAssetStdCostType implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetStdCostTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getFixedAssetStdCostTypeId() {
return fixedAssetStdCostTypeId;
}

public void setFixedAssetStdCostTypeId(String  fixedAssetStdCostTypeId) {
this.fixedAssetStdCostTypeId = fixedAssetStdCostTypeId;
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
return FixedAssetStdCostTypeMapper.map(this);
}
}
