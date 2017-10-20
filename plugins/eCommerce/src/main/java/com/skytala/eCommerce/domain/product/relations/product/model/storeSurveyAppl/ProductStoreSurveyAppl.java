package com.skytala.eCommerce.domain.product.relations.product.model.storeSurveyAppl;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeSurveyAppl.ProductStoreSurveyApplMapper;

public class ProductStoreSurveyAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreSurveyId;
private String productStoreId;
private String surveyApplTypeId;
private String groupName;
private String surveyId;
private String productId;
private String productCategoryId;
private Timestamp fromDate;
private Timestamp thruDate;
private String surveyTemplate;
private String resultTemplate;
private Long sequenceNum;

public String getProductStoreSurveyId() {
return productStoreSurveyId;
}

public void setProductStoreSurveyId(String  productStoreSurveyId) {
this.productStoreSurveyId = productStoreSurveyId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getSurveyApplTypeId() {
return surveyApplTypeId;
}

public void setSurveyApplTypeId(String  surveyApplTypeId) {
this.surveyApplTypeId = surveyApplTypeId;
}

public String getGroupName() {
return groupName;
}

public void setGroupName(String  groupName) {
this.groupName = groupName;
}

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public String getSurveyTemplate() {
return surveyTemplate;
}

public void setSurveyTemplate(String  surveyTemplate) {
this.surveyTemplate = surveyTemplate;
}

public String getResultTemplate() {
return resultTemplate;
}

public void setResultTemplate(String  resultTemplate) {
this.resultTemplate = resultTemplate;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreSurveyApplMapper.map(this);
}
}
