package com.skytala.eCommerce.domain.party.relations.party.model.note;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.note.PartyNoteMapper;

public class PartyNote implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String noteId;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}


public Map<String, Object> mapAttributeField() {
return PartyNoteMapper.map(this);
}
}
