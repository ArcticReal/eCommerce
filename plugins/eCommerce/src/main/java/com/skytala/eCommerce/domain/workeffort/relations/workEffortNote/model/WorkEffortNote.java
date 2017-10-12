package com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.mapper.WorkEffortNoteMapper;

public class WorkEffortNote implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String noteId;
private Boolean internalNote;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
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
return WorkEffortNoteMapper.map(this);
}
}
