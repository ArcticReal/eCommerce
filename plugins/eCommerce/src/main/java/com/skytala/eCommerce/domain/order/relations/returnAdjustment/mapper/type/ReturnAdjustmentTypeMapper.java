package com.skytala.eCommerce.domain.order.relations.returnAdjustment.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.type.ReturnAdjustmentType;

public class ReturnAdjustmentTypeMapper  {


	public static Map<String, Object> map(ReturnAdjustmentType returnadjustmenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnadjustmenttype.getReturnAdjustmentTypeId() != null ){
			returnVal.put("returnAdjustmentTypeId",returnadjustmenttype.getReturnAdjustmentTypeId());
}

		if(returnadjustmenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",returnadjustmenttype.getParentTypeId());
}

		if(returnadjustmenttype.getHasTable() != null ){
			returnVal.put("hasTable",returnadjustmenttype.getHasTable());
}

		if(returnadjustmenttype.getDescription() != null ){
			returnVal.put("description",returnadjustmenttype.getDescription());
}

		return returnVal;
}


	public static ReturnAdjustmentType map(Map<String, Object> fields) {

		ReturnAdjustmentType returnVal = new ReturnAdjustmentType();

		if(fields.get("returnAdjustmentTypeId") != null) {
			returnVal.setReturnAdjustmentTypeId((String) fields.get("returnAdjustmentTypeId"));
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
	public static ReturnAdjustmentType mapstrstr(Map<String, String> fields) throws Exception {

		ReturnAdjustmentType returnVal = new ReturnAdjustmentType();

		if(fields.get("returnAdjustmentTypeId") != null) {
			returnVal.setReturnAdjustmentTypeId((String) fields.get("returnAdjustmentTypeId"));
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
	public static ReturnAdjustmentType map(GenericValue val) {

ReturnAdjustmentType returnVal = new ReturnAdjustmentType();
		returnVal.setReturnAdjustmentTypeId(val.getString("returnAdjustmentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ReturnAdjustmentType map(HttpServletRequest request) throws Exception {

		ReturnAdjustmentType returnVal = new ReturnAdjustmentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnAdjustmentTypeId")) {
returnVal.setReturnAdjustmentTypeId(request.getParameter("returnAdjustmentTypeId"));
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
