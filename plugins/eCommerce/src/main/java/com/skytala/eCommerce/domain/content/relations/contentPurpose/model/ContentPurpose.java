package com.skytala.eCommerce.domain.content.relations.contentPurpose.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.contentPurpose.mapper.ContentPurposeMapper;

public class ContentPurpose implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String contentPurposeTypeId;
private Long sequenceNum;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getContentPurposeTypeId() {
return contentPurposeTypeId;
}

public void setContentPurposeTypeId(String  contentPurposeTypeId) {
this.contentPurposeTypeId = contentPurposeTypeId;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ContentPurposeMapper.map(this);
}
}
