package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;

public class MrpEventMapper  {


	public static Map<String, Object> map(MrpEvent mrpevent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(mrpevent.getMrpId() != null ){
			returnVal.put("mrpId",mrpevent.getMrpId());
}

		if(mrpevent.getProductId() != null ){
			returnVal.put("productId",mrpevent.getProductId());
}

		if(mrpevent.getEventDate() != null ){
			returnVal.put("eventDate",mrpevent.getEventDate());
}

		if(mrpevent.getMrpEventTypeId() != null ){
			returnVal.put("mrpEventTypeId",mrpevent.getMrpEventTypeId());
}

		if(mrpevent.getFacilityId() != null ){
			returnVal.put("facilityId",mrpevent.getFacilityId());
}

		if(mrpevent.getQuantity() != null ){
			returnVal.put("quantity",mrpevent.getQuantity());
}

		if(mrpevent.getEventName() != null ){
			returnVal.put("eventName",mrpevent.getEventName());
}

		if(mrpevent.getIsLate() != null ){
			returnVal.put("isLate",mrpevent.getIsLate());
}

		return returnVal;
}


	public static MrpEvent map(Map<String, Object> fields) {

		MrpEvent returnVal = new MrpEvent();

		if(fields.get("mrpId") != null) {
			returnVal.setMrpId((String) fields.get("mrpId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("eventDate") != null) {
			returnVal.setEventDate((Timestamp) fields.get("eventDate"));
}

		if(fields.get("mrpEventTypeId") != null) {
			returnVal.setMrpEventTypeId((String) fields.get("mrpEventTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("eventName") != null) {
			returnVal.setEventName((String) fields.get("eventName"));
}

		if(fields.get("isLate") != null) {
			returnVal.setIsLate((boolean) fields.get("isLate"));
}


		return returnVal;
 } 
	public static MrpEvent mapstrstr(Map<String, String> fields) throws Exception {

		MrpEvent returnVal = new MrpEvent();

		if(fields.get("mrpId") != null) {
			returnVal.setMrpId((String) fields.get("mrpId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("eventDate") != null) {
String buf = fields.get("eventDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEventDate(ibuf);
}

		if(fields.get("mrpEventTypeId") != null) {
			returnVal.setMrpEventTypeId((String) fields.get("mrpEventTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("eventName") != null) {
			returnVal.setEventName((String) fields.get("eventName"));
}

		if(fields.get("isLate") != null) {
String buf;
buf = fields.get("isLate");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsLate(ibuf);
}


		return returnVal;
 } 
	public static MrpEvent map(GenericValue val) {

MrpEvent returnVal = new MrpEvent();
		returnVal.setMrpId(val.getString("mrpId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setEventDate(val.getTimestamp("eventDate"));
		returnVal.setMrpEventTypeId(val.getString("mrpEventTypeId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setEventName(val.getString("eventName"));
		returnVal.setIsLate(val.getBoolean("isLate"));


return returnVal;

}

public static MrpEvent map(HttpServletRequest request) throws Exception {

		MrpEvent returnVal = new MrpEvent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("mrpId")) {
returnVal.setMrpId(request.getParameter("mrpId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("eventDate"))  {
String buf = request.getParameter("eventDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEventDate(ibuf);
}
		if(paramMap.containsKey("mrpEventTypeId"))  {
returnVal.setMrpEventTypeId(request.getParameter("mrpEventTypeId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("eventName"))  {
returnVal.setEventName(request.getParameter("eventName"));
}
		if(paramMap.containsKey("isLate"))  {
String buf = request.getParameter("isLate");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsLate(ibuf);
}
return returnVal;

}
}
