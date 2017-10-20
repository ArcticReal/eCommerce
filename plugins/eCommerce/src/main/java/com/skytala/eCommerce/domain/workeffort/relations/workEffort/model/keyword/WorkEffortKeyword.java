package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.keyword;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.keyword.WorkEffortKeywordMapper;

public class WorkEffortKeyword implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String keyword;
private Long relevancyWeight;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getKeyword() {
return keyword;
}

public void setKeyword(String  keyword) {
this.keyword = keyword;
}

public Long getRelevancyWeight() {
return relevancyWeight;
}

public void setRelevancyWeight(Long  relevancyWeight) {
this.relevancyWeight = relevancyWeight;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortKeywordMapper.map(this);
}
}
