package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.review;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.review.WorkEffortReviewMapper;

public class WorkEffortReview implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String userLoginId;
private Timestamp reviewDate;
private String statusId;
private Boolean postedAnonymous;
private BigDecimal rating;
private String reviewText;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
}

public Timestamp getReviewDate() {
return reviewDate;
}

public void setReviewDate(Timestamp  reviewDate) {
this.reviewDate = reviewDate;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Boolean getPostedAnonymous() {
return postedAnonymous;
}

public void setPostedAnonymous(Boolean  postedAnonymous) {
this.postedAnonymous = postedAnonymous;
}

public BigDecimal getRating() {
return rating;
}

public void setRating(BigDecimal  rating) {
this.rating = rating;
}

public String getReviewText() {
return reviewText;
}

public void setReviewText(String  reviewText) {
this.reviewText = reviewText;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortReviewMapper.map(this);
}
}
