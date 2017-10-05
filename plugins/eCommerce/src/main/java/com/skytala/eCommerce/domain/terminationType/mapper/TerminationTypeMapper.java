package com.skytala.eCommerce.domain.terminationType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.terminationType.model.TerminationType;

public class TerminationTypeMapper  {


	public static Map<String, Object> map(TerminationType terminationtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(terminationtype.getTerminationTypeId() != null ){
			returnVal.put("terminationTypeId",terminationtype.getTerminationTypeId());
}

		if(terminationtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",terminationtype.getParentTypeId());
}

		if(terminationtype.getHasTable() != null ){
			returnVal.put("hasTable",terminationtype.getHasTable());
}

		if(terminationtype.getDescription() != null ){
			returnVal.put("description",terminationtype.getDescription());
}

		return returnVal;
}


	public static TerminationType map(Map<String, Object> fields) {

		TerminationType returnVal = new TerminationType();

		if(fields.get("terminationTypeId") != null) {
			returnVal.setTerminationTypeId((String) fields.get("terminationTypeId"));
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
	public static TerminationType mapstrstr(Map<String, String> fields) throws Exception {

		TerminationType returnVal = new TerminationType();

		if(fields.get("terminationTypeId") != null) {
			returnVal.setTerminationTypeId((String) fields.get("terminationTypeId"));
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
	public static TerminationType map(GenericValue val) {

TerminationType returnVal = new TerminationType();
		returnVal.setTerminationTypeId(val.getString("terminationTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TerminationType map(HttpServletRequest request) throws Exception {

		TerminationType returnVal = new TerminationType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("terminationTypeId")) {
returnVal.setTerminationTypeId(request.getParameter("terminationTypeId"));
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
