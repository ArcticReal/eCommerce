package com.skytala.eCommerce.domain.contentAssocPredicate.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.contentAssocPredicate.mapper.ContentAssocPredicateMapper;

public class ContentAssocPredicate implements Serializable{

private static final long serialVersionUID = 1L;
private String contentAssocPredicateId;
private String description;

public String getContentAssocPredicateId() {
return contentAssocPredicateId;
}

public void setContentAssocPredicateId(String  contentAssocPredicateId) {
this.contentAssocPredicateId = contentAssocPredicateId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContentAssocPredicateMapper.map(this);
}
}
