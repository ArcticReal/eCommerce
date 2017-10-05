package com.skytala.eCommerce.domain.finAccountTransType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.finAccountTransType.model.FinAccountTransType;

public class FinAccountTransTypeMapper  {


	public static Map<String, Object> map(FinAccountTransType finaccounttranstype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccounttranstype.getFinAccountTransTypeId() != null ){
			returnVal.put("finAccountTransTypeId",finaccounttranstype.getFinAccountTransTypeId());
}

		if(finaccounttranstype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",finaccounttranstype.getParentTypeId());
}

		if(finaccounttranstype.getHasTable() != null ){
			returnVal.put("hasTable",finaccounttranstype.getHasTable());
}

		if(finaccounttranstype.getDescription() != null ){
			returnVal.put("description",finaccounttranstype.getDescription());
}

		return returnVal;
}


	public static FinAccountTransType map(Map<String, Object> fields) {

		FinAccountTransType returnVal = new FinAccountTransType();

		if(fields.get("finAccountTransTypeId") != null) {
			returnVal.setFinAccountTransTypeId((String) fields.get("finAccountTransTypeId"));
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
	public static FinAccountTransType mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountTransType returnVal = new FinAccountTransType();

		if(fields.get("finAccountTransTypeId") != null) {
			returnVal.setFinAccountTransTypeId((String) fields.get("finAccountTransTypeId"));
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
	public static FinAccountTransType map(GenericValue val) {

FinAccountTransType returnVal = new FinAccountTransType();
		returnVal.setFinAccountTransTypeId(val.getString("finAccountTransTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FinAccountTransType map(HttpServletRequest request) throws Exception {

		FinAccountTransType returnVal = new FinAccountTransType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountTransTypeId")) {
returnVal.setFinAccountTransTypeId(request.getParameter("finAccountTransTypeId"));
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
