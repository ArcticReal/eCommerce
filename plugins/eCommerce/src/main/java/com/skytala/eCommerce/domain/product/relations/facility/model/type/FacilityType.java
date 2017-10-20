package com.skytala.eCommerce.domain.product.relations.facility.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.type.FacilityTypeMapper;

public class FacilityType implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getFacilityTypeId() {
return facilityTypeId;
}

public void setFacilityTypeId(String  facilityTypeId) {
this.facilityTypeId = facilityTypeId;
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
return FacilityTypeMapper.map(this);
}
}
