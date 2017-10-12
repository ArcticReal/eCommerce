package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.mapper.ContentSearchConstraintMapper;

public class ContentSearchConstraint implements Serializable{

private static final long serialVersionUID = 1L;
private String contentSearchResultId;
private String constraintSeqId;
private String constraintName;
private String infoString;
private Boolean includeSubCategories;
private Boolean isAnd;
private Boolean anyPrefix;
private Boolean anySuffix;
private Boolean removeStems;
private String lowValue;
private String highValue;

public String getContentSearchResultId() {
return contentSearchResultId;
}

public void setContentSearchResultId(String  contentSearchResultId) {
this.contentSearchResultId = contentSearchResultId;
}

public String getConstraintSeqId() {
return constraintSeqId;
}

public void setConstraintSeqId(String  constraintSeqId) {
this.constraintSeqId = constraintSeqId;
}

public String getConstraintName() {
return constraintName;
}

public void setConstraintName(String  constraintName) {
this.constraintName = constraintName;
}

public String getInfoString() {
return infoString;
}

public void setInfoString(String  infoString) {
this.infoString = infoString;
}

public Boolean getIncludeSubCategories() {
return includeSubCategories;
}

public void setIncludeSubCategories(Boolean  includeSubCategories) {
this.includeSubCategories = includeSubCategories;
}

public Boolean getIsAnd() {
return isAnd;
}

public void setIsAnd(Boolean  isAnd) {
this.isAnd = isAnd;
}

public Boolean getAnyPrefix() {
return anyPrefix;
}

public void setAnyPrefix(Boolean  anyPrefix) {
this.anyPrefix = anyPrefix;
}

public Boolean getAnySuffix() {
return anySuffix;
}

public void setAnySuffix(Boolean  anySuffix) {
this.anySuffix = anySuffix;
}

public Boolean getRemoveStems() {
return removeStems;
}

public void setRemoveStems(Boolean  removeStems) {
this.removeStems = removeStems;
}

public String getLowValue() {
return lowValue;
}

public void setLowValue(String  lowValue) {
this.lowValue = lowValue;
}

public String getHighValue() {
return highValue;
}

public void setHighValue(String  highValue) {
this.highValue = highValue;
}


public Map<String, Object> mapAttributeField() {
return ContentSearchConstraintMapper.map(this);
}
}
