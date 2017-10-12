package com.skytala.eCommerce.domain.content.relations.metaDataPredicate.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.mapper.MetaDataPredicateMapper;

public class MetaDataPredicate implements Serializable{

private static final long serialVersionUID = 1L;
private String metaDataPredicateId;
private String description;

public String getMetaDataPredicateId() {
return metaDataPredicateId;
}

public void setMetaDataPredicateId(String  metaDataPredicateId) {
this.metaDataPredicateId = metaDataPredicateId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return MetaDataPredicateMapper.map(this);
}
}
