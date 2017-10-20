package com.skytala.eCommerce.domain.order.relations.orderHeader.model.note;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.note.OrderHeaderNoteMapper;

public class OrderHeaderNote implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String noteId;
private Boolean internalNote;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}

public Boolean getInternalNote() {
return internalNote;
}

public void setInternalNote(Boolean  internalNote) {
this.internalNote = internalNote;
}


public Map<String, Object> mapAttributeField() {
return OrderHeaderNoteMapper.map(this);
}
}
