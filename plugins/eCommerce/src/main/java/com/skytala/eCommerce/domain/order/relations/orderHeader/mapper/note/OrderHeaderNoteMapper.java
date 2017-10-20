package com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.note;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.note.OrderHeaderNote;

public class OrderHeaderNoteMapper  {


	public static Map<String, Object> map(OrderHeaderNote orderheadernote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderheadernote.getOrderId() != null ){
			returnVal.put("orderId",orderheadernote.getOrderId());
}

		if(orderheadernote.getNoteId() != null ){
			returnVal.put("noteId",orderheadernote.getNoteId());
}

		if(orderheadernote.getInternalNote() != null ){
			returnVal.put("internalNote",orderheadernote.getInternalNote());
}

		return returnVal;
}


	public static OrderHeaderNote map(Map<String, Object> fields) {

		OrderHeaderNote returnVal = new OrderHeaderNote();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}

		if(fields.get("internalNote") != null) {
			returnVal.setInternalNote((boolean) fields.get("internalNote"));
}


		return returnVal;
 } 
	public static OrderHeaderNote mapstrstr(Map<String, String> fields) throws Exception {

		OrderHeaderNote returnVal = new OrderHeaderNote();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
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
	public static OrderHeaderNote map(GenericValue val) {

OrderHeaderNote returnVal = new OrderHeaderNote();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setNoteId(val.getString("noteId"));
		returnVal.setInternalNote(val.getBoolean("internalNote"));


return returnVal;

}

public static OrderHeaderNote map(HttpServletRequest request) throws Exception {

		OrderHeaderNote returnVal = new OrderHeaderNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
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
