package com.skytala.eCommerce.domain.deductionType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.deductionType.model.DeductionType;

public class DeductionTypeMapper  {


	public static Map<String, Object> map(DeductionType deductiontype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(deductiontype.getDeductionTypeId() != null ){
			returnVal.put("deductionTypeId",deductiontype.getDeductionTypeId());
}

		if(deductiontype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",deductiontype.getParentTypeId());
}

		if(deductiontype.getHasTable() != null ){
			returnVal.put("hasTable",deductiontype.getHasTable());
}

		if(deductiontype.getDescription() != null ){
			returnVal.put("description",deductiontype.getDescription());
}

		return returnVal;
}


	public static DeductionType map(Map<String, Object> fields) {

		DeductionType returnVal = new DeductionType();

		if(fields.get("deductionTypeId") != null) {
			returnVal.setDeductionTypeId((String) fields.get("deductionTypeId"));
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
	public static DeductionType mapstrstr(Map<String, String> fields) throws Exception {

		DeductionType returnVal = new DeductionType();

		if(fields.get("deductionTypeId") != null) {
			returnVal.setDeductionTypeId((String) fields.get("deductionTypeId"));
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
	public static DeductionType map(GenericValue val) {

DeductionType returnVal = new DeductionType();
		returnVal.setDeductionTypeId(val.getString("deductionTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static DeductionType map(HttpServletRequest request) throws Exception {

		DeductionType returnVal = new DeductionType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("deductionTypeId")) {
returnVal.setDeductionTypeId(request.getParameter("deductionTypeId"));
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
