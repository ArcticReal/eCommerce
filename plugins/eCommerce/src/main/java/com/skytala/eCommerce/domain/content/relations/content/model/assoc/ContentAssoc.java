package com.skytala.eCommerce.domain.content.relations.content.model.assoc;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assoc.ContentAssocMapper;

public class ContentAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String contentIdTo;
private String contentAssocTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String contentAssocPredicateId;
private String dataSourceId;
private Long sequenceNum;
private String mapKey;
private Long upperCoordinate;
private Long leftCoordinate;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getContentIdTo() {
return contentIdTo;
}

public void setContentIdTo(String  contentIdTo) {
this.contentIdTo = contentIdTo;
}

public String getContentAssocTypeId() {
return contentAssocTypeId;
}

public void setContentAssocTypeId(String  contentAssocTypeId) {
this.contentAssocTypeId = contentAssocTypeId;
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

public String getContentAssocPredicateId() {
return contentAssocPredicateId;
}

public void setContentAssocPredicateId(String  contentAssocPredicateId) {
this.contentAssocPredicateId = contentAssocPredicateId;
}

public String getDataSourceId() {
return dataSourceId;
}

public void setDataSourceId(String  dataSourceId) {
this.dataSourceId = dataSourceId;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public String getMapKey() {
return mapKey;
}

public void setMapKey(String  mapKey) {
this.mapKey = mapKey;
}

public Long getUpperCoordinate() {
return upperCoordinate;
}

public void setUpperCoordinate(Long  upperCoordinate) {
this.upperCoordinate = upperCoordinate;
}

public Long getLeftCoordinate() {
return leftCoordinate;
}

public void setLeftCoordinate(Long  leftCoordinate) {
this.leftCoordinate = leftCoordinate;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp  createdDate) {
this.createdDate = createdDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public Timestamp getLastModifiedDate() {
return lastModifiedDate;
}

public void setLastModifiedDate(Timestamp  lastModifiedDate) {
this.lastModifiedDate = lastModifiedDate;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return ContentAssocMapper.map(this);
}
}
