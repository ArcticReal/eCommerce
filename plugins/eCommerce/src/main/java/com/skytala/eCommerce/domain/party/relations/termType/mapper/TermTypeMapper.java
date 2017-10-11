package com.skytala.eCommerce.domain.party.relations.termType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.termType.model.TermType;

public class TermTypeMapper  {


	public static Map<String, Object> map(TermType termtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(termtype.getTermTypeId() != null ){
			returnVal.put("termTypeId",termtype.getTermTypeId());
}

		if(termtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",termtype.getParentTypeId());
}

		if(termtype.getHasTable() != null ){
			returnVal.put("hasTable",termtype.getHasTable());
}

		if(termtype.getDescription() != null ){
			returnVal.put("description",termtype.getDescription());
}

		return returnVal;
}


	public static TermType map(Map<String, Object> fields) {

		TermType returnVal = new TermType();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
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
	public static TermType mapstrstr(Map<String, String> fields) throws Exception {

		TermType returnVal = new TermType();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
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
	public static TermType map(GenericValue val) {

TermType returnVal = new TermType();
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TermType map(HttpServletRequest request) throws Exception {

		TermType returnVal = new TermType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("termTypeId")) {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
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
