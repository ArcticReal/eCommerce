package com.skytala.eCommerce.domain.humanres.relations.perfReview.model.itemType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.itemType.PerfReviewItemTypeMapper;

public class PerfReviewItemType implements Serializable{

private static final long serialVersionUID = 1L;
private String perfReviewItemTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getPerfReviewItemTypeId() {
return perfReviewItemTypeId;
}

public void setPerfReviewItemTypeId(String  perfReviewItemTypeId) {
this.perfReviewItemTypeId = perfReviewItemTypeId;
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
return PerfReviewItemTypeMapper.map(this);
}
}
