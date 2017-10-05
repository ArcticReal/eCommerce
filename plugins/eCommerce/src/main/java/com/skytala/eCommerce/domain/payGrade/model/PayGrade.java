package com.skytala.eCommerce.domain.payGrade.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.payGrade.mapper.PayGradeMapper;

public class PayGrade implements Serializable{

private static final long serialVersionUID = 1L;
private String payGradeId;
private String payGradeName;
private String comments;

public String getPayGradeId() {
return payGradeId;
}

public void setPayGradeId(String  payGradeId) {
this.payGradeId = payGradeId;
}

public String getPayGradeName() {
return payGradeName;
}

public void setPayGradeName(String  payGradeName) {
this.payGradeName = payGradeName;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return PayGradeMapper.map(this);
}
}
