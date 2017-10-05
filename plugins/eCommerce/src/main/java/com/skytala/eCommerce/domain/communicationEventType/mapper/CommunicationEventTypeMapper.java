package com.skytala.eCommerce.domain.communicationEventType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.communicationEventType.model.CommunicationEventType;

public class CommunicationEventTypeMapper  {


	public static Map<String, Object> map(CommunicationEventType communicationeventtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationeventtype.getCommunicationEventTypeId() != null ){
			returnVal.put("communicationEventTypeId",communicationeventtype.getCommunicationEventTypeId());
}

		if(communicationeventtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",communicationeventtype.getParentTypeId());
}

		if(communicationeventtype.getHasTable() != null ){
			returnVal.put("hasTable",communicationeventtype.getHasTable());
}

		if(communicationeventtype.getDescription() != null ){
			returnVal.put("description",communicationeventtype.getDescription());
}

		if(communicationeventtype.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",communicationeventtype.getContactMechTypeId());
}

		return returnVal;
}


	public static CommunicationEventType map(Map<String, Object> fields) {

		CommunicationEventType returnVal = new CommunicationEventType();

		if(fields.get("communicationEventTypeId") != null) {
			returnVal.setCommunicationEventTypeId((String) fields.get("communicationEventTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}


		return returnVal;
 } 
	public static CommunicationEventType mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEventType returnVal = new CommunicationEventType();

		if(fields.get("communicationEventTypeId") != null) {
			returnVal.setCommunicationEventTypeId((String) fields.get("communicationEventTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}


		return returnVal;
 } 
	public static CommunicationEventType map(GenericValue val) {

CommunicationEventType returnVal = new CommunicationEventType();
		returnVal.setCommunicationEventTypeId(val.getString("communicationEventTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));


return returnVal;

}

public static CommunicationEventType map(HttpServletRequest request) throws Exception {

		CommunicationEventType returnVal = new CommunicationEventType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("communicationEventTypeId")) {
returnVal.setCommunicationEventTypeId(request.getParameter("communicationEventTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("contactMechTypeId"))  {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}
return returnVal;

}
}
