package com.skytala.eCommerce.domain.order.relations.custRequestItemNote.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.model.CustRequestItemNote;

public class CustRequestItemNoteMapper  {


	public static Map<String, Object> map(CustRequestItemNote custrequestitemnote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestitemnote.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestitemnote.getCustRequestId());
}

		if(custrequestitemnote.getCustRequestItemSeqId() != null ){
			returnVal.put("custRequestItemSeqId",custrequestitemnote.getCustRequestItemSeqId());
}

		if(custrequestitemnote.getNoteId() != null ){
			returnVal.put("noteId",custrequestitemnote.getNoteId());
}

		return returnVal;
}


	public static CustRequestItemNote map(Map<String, Object> fields) {

		CustRequestItemNote returnVal = new CustRequestItemNote();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static CustRequestItemNote mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestItemNote returnVal = new CustRequestItemNote();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static CustRequestItemNote map(GenericValue val) {

CustRequestItemNote returnVal = new CustRequestItemNote();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCustRequestItemSeqId(val.getString("custRequestItemSeqId"));
		returnVal.setNoteId(val.getString("noteId"));


return returnVal;

}

public static CustRequestItemNote map(HttpServletRequest request) throws Exception {

		CustRequestItemNote returnVal = new CustRequestItemNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("custRequestItemSeqId"))  {
returnVal.setCustRequestItemSeqId(request.getParameter("custRequestItemSeqId"));
}
		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
return returnVal;

}
}
