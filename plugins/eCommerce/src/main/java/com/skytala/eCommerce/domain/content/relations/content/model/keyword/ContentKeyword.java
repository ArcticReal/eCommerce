package com.skytala.eCommerce.domain.content.relations.content.model.keyword;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.keyword.ContentKeywordMapper;

public class ContentKeyword implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String keyword;
private Long relevancyWeight;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
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
return ContentKeywordMapper.map(this);
}
}
