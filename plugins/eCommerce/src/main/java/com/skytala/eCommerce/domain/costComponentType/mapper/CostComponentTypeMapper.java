package com.skytala.eCommerce.domain.costComponentType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.costComponentType.model.CostComponentType;

public class CostComponentTypeMapper  {


	public static Map<String, Object> map(CostComponentType costcomponenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(costcomponenttype.getCostComponentTypeId() != null ){
			returnVal.put("costComponentTypeId",costcomponenttype.getCostComponentTypeId());
}

		if(costcomponenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",costcomponenttype.getParentTypeId());
}

		if(costcomponenttype.getHasTable() != null ){
			returnVal.put("hasTable",costcomponenttype.getHasTable());
}

		if(costcomponenttype.getDescription() != null ){
			returnVal.put("description",costcomponenttype.getDescription());
}

		return returnVal;
}


	public static CostComponentType map(Map<String, Object> fields) {

		CostComponentType returnVal = new CostComponentType();

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
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
	public static CostComponentType mapstrstr(Map<String, String> fields) throws Exception {

		CostComponentType returnVal = new CostComponentType();

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
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
	public static CostComponentType map(GenericValue val) {

CostComponentType returnVal = new CostComponentType();
		returnVal.setCostComponentTypeId(val.getString("costComponentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CostComponentType map(HttpServletRequest request) throws Exception {

		CostComponentType returnVal = new CostComponentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("costComponentTypeId")) {
returnVal.setCostComponentTypeId(request.getParameter("costComponentTypeId"));
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
