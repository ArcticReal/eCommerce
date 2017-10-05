package com.skytala.eCommerce.domain.employmentAppSourceType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.employmentAppSourceType.model.EmploymentAppSourceType;

public class EmploymentAppSourceTypeMapper  {


	public static Map<String, Object> map(EmploymentAppSourceType employmentappsourcetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(employmentappsourcetype.getEmploymentAppSourceTypeId() != null ){
			returnVal.put("employmentAppSourceTypeId",employmentappsourcetype.getEmploymentAppSourceTypeId());
}

		if(employmentappsourcetype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",employmentappsourcetype.getParentTypeId());
}

		if(employmentappsourcetype.getHasTable() != null ){
			returnVal.put("hasTable",employmentappsourcetype.getHasTable());
}

		if(employmentappsourcetype.getDescription() != null ){
			returnVal.put("description",employmentappsourcetype.getDescription());
}

		return returnVal;
}


	public static EmploymentAppSourceType map(Map<String, Object> fields) {

		EmploymentAppSourceType returnVal = new EmploymentAppSourceType();

		if(fields.get("employmentAppSourceTypeId") != null) {
			returnVal.setEmploymentAppSourceTypeId((String) fields.get("employmentAppSourceTypeId"));
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
	public static EmploymentAppSourceType mapstrstr(Map<String, String> fields) throws Exception {

		EmploymentAppSourceType returnVal = new EmploymentAppSourceType();

		if(fields.get("employmentAppSourceTypeId") != null) {
			returnVal.setEmploymentAppSourceTypeId((String) fields.get("employmentAppSourceTypeId"));
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
	public static EmploymentAppSourceType map(GenericValue val) {

EmploymentAppSourceType returnVal = new EmploymentAppSourceType();
		returnVal.setEmploymentAppSourceTypeId(val.getString("employmentAppSourceTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static EmploymentAppSourceType map(HttpServletRequest request) throws Exception {

		EmploymentAppSourceType returnVal = new EmploymentAppSourceType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("employmentAppSourceTypeId")) {
returnVal.setEmploymentAppSourceTypeId(request.getParameter("employmentAppSourceTypeId"));
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
