package com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.mapper.PerfReviewItemMapper;

public class PerfReviewItem implements Serializable{

private static final long serialVersionUID = 1L;
private String employeePartyId;
private String employeeRoleTypeId;
private String perfReviewId;
private String perfReviewItemSeqId;
private String perfReviewItemTypeId;
private String perfRatingTypeId;
private String comments;

public String getEmployeePartyId() {
return employeePartyId;
}

public void setEmployeePartyId(String  employeePartyId) {
this.employeePartyId = employeePartyId;
}

public String getEmployeeRoleTypeId() {
return employeeRoleTypeId;
}

public void setEmployeeRoleTypeId(String  employeeRoleTypeId) {
this.employeeRoleTypeId = employeeRoleTypeId;
}

public String getPerfReviewId() {
return perfReviewId;
}

public void setPerfReviewId(String  perfReviewId) {
this.perfReviewId = perfReviewId;
}

public String getPerfReviewItemSeqId() {
return perfReviewItemSeqId;
}

public void setPerfReviewItemSeqId(String  perfReviewItemSeqId) {
this.perfReviewItemSeqId = perfReviewItemSeqId;
}

public String getPerfReviewItemTypeId() {
return perfReviewItemTypeId;
}

public void setPerfReviewItemTypeId(String  perfReviewItemTypeId) {
this.perfReviewItemTypeId = perfReviewItemTypeId;
}

public String getPerfRatingTypeId() {
return perfRatingTypeId;
}

public void setPerfRatingTypeId(String  perfRatingTypeId) {
this.perfRatingTypeId = perfRatingTypeId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return PerfReviewItemMapper.map(this);
}
}
