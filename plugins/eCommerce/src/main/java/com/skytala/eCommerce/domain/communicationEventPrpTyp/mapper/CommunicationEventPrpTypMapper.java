package com.skytala.eCommerce.domain.communicationEventPrpTyp.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.communicationEventPrpTyp.model.CommunicationEventPrpTyp;

public class CommunicationEventPrpTypMapper  {


	public static Map<String, Object> map(CommunicationEventPrpTyp communicationeventprptyp) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationeventprptyp.getCommunicationEventPrpTypId() != null ){
			returnVal.put("communicationEventPrpTypId",communicationeventprptyp.getCommunicationEventPrpTypId());
}

		if(communicationeventprptyp.getParentTypeId() != null ){
			returnVal.put("parentTypeId",communicationeventprptyp.getParentTypeId());
}

		if(communicationeventprptyp.getHasTable() != null ){
			returnVal.put("hasTable",communicationeventprptyp.getHasTable());
}

		if(communicationeventprptyp.getDescription() != null ){
			returnVal.put("description",communicationeventprptyp.getDescription());
}

		return returnVal;
}


	public static CommunicationEventPrpTyp map(Map<String, Object> fields) {

		CommunicationEventPrpTyp returnVal = new CommunicationEventPrpTyp();

		if(fields.get("communicationEventPrpTypId") != null) {
			returnVal.setCommunicationEventPrpTypId((String) fields.get("communicationEventPrpTypId"));
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


		return returnVal;
 } 
	public static CommunicationEventPrpTyp mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEventPrpTyp returnVal = new CommunicationEventPrpTyp();

		if(fields.get("communicationEventPrpTypId") != null) {
			returnVal.setCommunicationEventPrpTypId((String) fields.get("communicationEventPrpTypId"));
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


		return returnVal;
 } 
	public static CommunicationEventPrpTyp map(GenericValue val) {

CommunicationEventPrpTyp returnVal = new CommunicationEventPrpTyp();
		returnVal.setCommunicationEventPrpTypId(val.getString("communicationEventPrpTypId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CommunicationEventPrpTyp map(HttpServletRequest request) throws Exception {

		CommunicationEventPrpTyp returnVal = new CommunicationEventPrpTyp();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("communicationEventPrpTypId")) {
returnVal.setCommunicationEventPrpTypId(request.getParameter("communicationEventPrpTypId"));
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
return returnVal;

}
}
