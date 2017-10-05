package com.skytala.eCommerce.domain.workEffortSearchResult.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workEffortSearchResult.mapper.WorkEffortSearchResultMapper;

public class WorkEffortSearchResult implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortSearchResultId;
private String visitId;
private String orderByName;
private Boolean isAscending;
private Long numResults;
private BigDecimal secondsTotal;
private Timestamp searchDate;

public String getWorkEffortSearchResultId() {
return workEffortSearchResultId;
}

public void setWorkEffortSearchResultId(String  workEffortSearchResultId) {
this.workEffortSearchResultId = workEffortSearchResultId;
}

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
}

public String getOrderByName() {
return orderByName;
}

public void setOrderByName(String  orderByName) {
this.orderByName = orderByName;
}

public Boolean getIsAscending() {
return isAscending;
}

public void setIsAscending(Boolean  isAscending) {
this.isAscending = isAscending;
}

public Long getNumResults() {
return numResults;
}

public void setNumResults(Long  numResults) {
this.numResults = numResults;
}

public BigDecimal getSecondsTotal() {
return secondsTotal;
}

public void setSecondsTotal(BigDecimal  secondsTotal) {
this.secondsTotal = secondsTotal;
}

public Timestamp getSearchDate() {
return searchDate;
}

public void setSearchDate(Timestamp  searchDate) {
this.searchDate = searchDate;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortSearchResultMapper.map(this);
}
}
