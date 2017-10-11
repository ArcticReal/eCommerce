package com.skytala.eCommerce.domain.party.relations.partyNote.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyNote.model.PartyNote;

public class PartyNoteMapper  {


	public static Map<String, Object> map(PartyNote partynote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partynote.getPartyId() != null ){
			returnVal.put("partyId",partynote.getPartyId());
}

		if(partynote.getNoteId() != null ){
			returnVal.put("noteId",partynote.getNoteId());
}

		return returnVal;
}


	public static PartyNote map(Map<String, Object> fields) {

		PartyNote returnVal = new PartyNote();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static PartyNote mapstrstr(Map<String, String> fields) throws Exception {

		PartyNote returnVal = new PartyNote();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static PartyNote map(GenericValue val) {

PartyNote returnVal = new PartyNote();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setNoteId(val.getString("noteId"));


return returnVal;

}

public static PartyNote map(HttpServletRequest request) throws Exception {

		PartyNote returnVal = new PartyNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
return returnVal;

}
}
