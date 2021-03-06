package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.type.SegmentGroupTypeMapper;

public class SegmentGroupType implements Serializable{

private static final long serialVersionUID = 1L;
private String segmentGroupTypeId;
private String description;

public String getSegmentGroupTypeId() {
return segmentGroupTypeId;
}

public void setSegmentGroupTypeId(String  segmentGroupTypeId) {
this.segmentGroupTypeId = segmentGroupTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SegmentGroupTypeMapper.map(this);
}
}
