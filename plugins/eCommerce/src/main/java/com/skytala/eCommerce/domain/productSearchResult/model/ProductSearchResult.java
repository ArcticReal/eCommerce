package com.skytala.eCommerce.domain.productSearchResult.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productSearchResult.mapper.ProductSearchResultMapper;

public class ProductSearchResult implements Serializable{

private static final long serialVersionUID = 1L;
private String productSearchResultId;
private String visitId;
private String orderByName;
private Boolean isAscending;
private Long numResults;
private BigDecimal secondsTotal;
private Timestamp searchDate;

public String getProductSearchResultId() {
return productSearchResultId;
}

public void setProductSearchResultId(String  productSearchResultId) {
this.productSearchResultId = productSearchResultId;
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
return ProductSearchResultMapper.map(this);
}
}
