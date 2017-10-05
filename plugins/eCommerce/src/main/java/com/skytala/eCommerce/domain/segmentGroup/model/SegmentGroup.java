package com.skytala.eCommerce.domain.segmentGroup.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.segmentGroup.mapper.SegmentGroupMapper;

public class SegmentGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String segmentGroupId;
private String segmentGroupTypeId;
private String description;
private String productStoreId;

public String getSegmentGroupId() {
return segmentGroupId;
}

public void setSegmentGroupId(String  segmentGroupId) {
this.segmentGroupId = segmentGroupId;
}

public String getSegmentGroupTypeId() {
return segmentGroupTypeId;
}

public void setSegmentGroupTypeId(String  segmentGroupTypeId) {
this.segmentGroupTypeId = segmentGroupTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}


public Map<String, Object> mapAttributeField() {
return SegmentGroupMapper.map(this);
}
}
