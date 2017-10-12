package com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.model.WorkEffortNote;

public class WorkEffortNoteMapper  {


	public static Map<String, Object> map(WorkEffortNote workeffortnote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortnote.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortnote.getWorkEffortId());
}

		if(workeffortnote.getNoteId() != null ){
			returnVal.put("noteId",workeffortnote.getNoteId());
}

		if(workeffortnote.getInternalNote() != null ){
			returnVal.put("internalNote",workeffortnote.getInternalNote());
}

		return returnVal;
}


	public static WorkEffortNote map(Map<String, Object> fields) {

		WorkEffortNote returnVal = new WorkEffortNote();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}

		if(fields.get("internalNote") != null) {
			returnVal.setInternalNote((boolean) fields.get("internalNote"));
}


		return returnVal;
 } 
	public static WorkEffortNote mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortNote returnVal = new WorkEffortNote();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}

		if(fields.get("internalNote") != null) {
String buf;
buf = fields.get("internalNote");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setInternalNote(ibuf);
}


		return returnVal;
 } 
	public static WorkEffortNote map(GenericValue val) {

WorkEffortNote returnVal = new WorkEffortNote();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setNoteId(val.getString("noteId"));
		returnVal.setInternalNote(val.getBoolean("internalNote"));


return returnVal;

}

public static WorkEffortNote map(HttpServletRequest request) throws Exception {

		WorkEffortNote returnVal = new WorkEffortNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
		if(paramMap.containsKey("internalNote"))  {
String buf = request.getParameter("internalNote");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setInternalNote(ibuf);
}
return returnVal;

}
}
