package com.skytala.eCommerce.domain.product.relations.facility.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.typeAttr.FacilityTypeAttrMapper;

public class FacilityTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityTypeId;
private String attrName;
private String description;

public String getFacilityTypeId() {
return facilityTypeId;
}

public void setFacilityTypeId(String  facilityTypeId) {
this.facilityTypeId = facilityTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return FacilityTypeAttrMapper.map(this);
}
}
