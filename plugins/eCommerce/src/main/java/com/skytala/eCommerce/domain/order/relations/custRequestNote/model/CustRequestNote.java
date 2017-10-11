package com.skytala.eCommerce.domain.order.relations.custRequestNote.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequestNote.mapper.CustRequestNoteMapper;

public class CustRequestNote implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String noteId;

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}


public Map<String, Object> mapAttributeField() {
return CustRequestNoteMapper.map(this);
}
}
