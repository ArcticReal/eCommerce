package com.skytala.eCommerce.domain.content.relations.content.model.revisionItem;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.revisionItem.ContentRevisionItemMapper;

public class ContentRevisionItem implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String contentRevisionSeqId;
private String itemContentId;
private String oldDataResourceId;
private String newDataResourceId;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getContentRevisionSeqId() {
return contentRevisionSeqId;
}

public void setContentRevisionSeqId(String  contentRevisionSeqId) {
this.contentRevisionSeqId = contentRevisionSeqId;
}

public String getItemContentId() {
return itemContentId;
}

public void setItemContentId(String  itemContentId) {
this.itemContentId = itemContentId;
}

public String getOldDataResourceId() {
return oldDataResourceId;
}

public void setOldDataResourceId(String  oldDataResourceId) {
this.oldDataResourceId = oldDataResourceId;
}

public String getNewDataResourceId() {
return newDataResourceId;
}

public void setNewDataResourceId(String  newDataResourceId) {
this.newDataResourceId = newDataResourceId;
}


public Map<String, Object> mapAttributeField() {
return ContentRevisionItemMapper.map(this);
}
}
