package com.skytala.eCommerce.domain.trainingRequest.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.trainingRequest.mapper.TrainingRequestMapper;

public class TrainingRequest implements Serializable{

private static final long serialVersionUID = 1L;
private String trainingRequestId;

public String getTrainingRequestId() {
return trainingRequestId;
}

public void setTrainingRequestId(String  trainingRequestId) {
this.trainingRequestId = trainingRequestId;
}


public Map<String, Object> mapAttributeField() {
return TrainingRequestMapper.map(this);
}
}
