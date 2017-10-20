package com.skytala.eCommerce.domain.order.relations.custRequest.model.itemNote;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemNote.CustRequestItemNoteMapper;

public class CustRequestItemNote implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String custRequestItemSeqId;
private String noteId;

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getCustRequestItemSeqId() {
return custRequestItemSeqId;
}

public void setCustRequestItemSeqId(String  custRequestItemSeqId) {
this.custRequestItemSeqId = custRequestItemSeqId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}


public Map<String, Object> mapAttributeField() {
return CustRequestItemNoteMapper.map(this);
}
}
