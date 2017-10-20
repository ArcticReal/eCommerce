package com.skytala.eCommerce.domain.content.relations.content.model.revision;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.revision.ContentRevisionMapper;

public class ContentRevision implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String contentRevisionSeqId;
private String committedByPartyId;
private String comments;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getContentRevisionSeqId() {
return contentRevisionSeqId;
}

public void setContentRevisionSeqId(String  contentRevisionSeqId) {
this.contentRevisionSeqId = contentRevisionSeqId;
}

public String getCommittedByPartyId() {
return committedByPartyId;
}

public void setCommittedByPartyId(String  committedByPartyId) {
this.committedByPartyId = committedByPartyId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return ContentRevisionMapper.map(this);
}
}
