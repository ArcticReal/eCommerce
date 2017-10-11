package com.skytala.eCommerce.domain.order.relations.custRequestNote.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestNote.model.CustRequestNote;

public class CustRequestNoteMapper  {


	public static Map<String, Object> map(CustRequestNote custrequestnote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestnote.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestnote.getCustRequestId());
}

		if(custrequestnote.getNoteId() != null ){
			returnVal.put("noteId",custrequestnote.getNoteId());
}

		return returnVal;
}


	public static CustRequestNote map(Map<String, Object> fields) {

		CustRequestNote returnVal = new CustRequestNote();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static CustRequestNote mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestNote returnVal = new CustRequestNote();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static CustRequestNote map(GenericValue val) {

CustRequestNote returnVal = new CustRequestNote();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setNoteId(val.getString("noteId"));


return returnVal;

}

public static CustRequestNote map(HttpServletRequest request) throws Exception {

		CustRequestNote returnVal = new CustRequestNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
return returnVal;

}
}
