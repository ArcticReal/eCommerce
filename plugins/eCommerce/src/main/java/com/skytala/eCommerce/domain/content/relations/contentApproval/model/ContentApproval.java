package com.skytala.eCommerce.domain.content.relations.contentApproval.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.contentApproval.mapper.ContentApprovalMapper;

public class ContentApproval implements Serializable{

private static final long serialVersionUID = 1L;
private String contentApprovalId;
private String contentId;
private String contentRevisionSeqId;
private String partyId;
private String roleTypeId;
private String approvalStatusId;
private Timestamp approvalDate;
private Long sequenceNum;
private String comments;

public String getContentApprovalId() {
return contentApprovalId;
}

public void setContentApprovalId(String  contentApprovalId) {
this.contentApprovalId = contentApprovalId;
}

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

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getApprovalStatusId() {
return approvalStatusId;
}

public void setApprovalStatusId(String  approvalStatusId) {
this.approvalStatusId = approvalStatusId;
}

public Timestamp getApprovalDate() {
return approvalDate;
}

public void setApprovalDate(Timestamp  approvalDate) {
this.approvalDate = approvalDate;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return ContentApprovalMapper.map(this);
}
}
