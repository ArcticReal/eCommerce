package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.classification;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.classification.SegmentGroupClassificationMapper;

public class SegmentGroupClassification implements Serializable{

private static final long serialVersionUID = 1L;
private String segmentGroupId;
private String partyClassificationGroupId;

public String getSegmentGroupId() {
return segmentGroupId;
}

public void setSegmentGroupId(String  segmentGroupId) {
this.segmentGroupId = segmentGroupId;
}

public String getPartyClassificationGroupId() {
return partyClassificationGroupId;
}

public void setPartyClassificationGroupId(String  partyClassificationGroupId) {
this.partyClassificationGroupId = partyClassificationGroupId;
}


public Map<String, Object> mapAttributeField() {
return SegmentGroupClassificationMapper.map(this);
}
}
