package com.skytala.eCommerce.domain.returnItemType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnItemType.model.ReturnItemType;

public class ReturnItemTypeMapper  {


	public static Map<String, Object> map(ReturnItemType returnitemtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnitemtype.getReturnItemTypeId() != null ){
			returnVal.put("returnItemTypeId",returnitemtype.getReturnItemTypeId());
}

		if(returnitemtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",returnitemtype.getParentTypeId());
}

		if(returnitemtype.getDescription() != null ){
			returnVal.put("description",returnitemtype.getDescription());
}

		return returnVal;
}


	public static ReturnItemType map(Map<String, Object> fields) {

		ReturnItemType returnVal = new ReturnItemType();

		if(fields.get("returnItemTypeId") != null) {
			returnVal.setReturnItemTypeId((String) fields.get("returnItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ReturnItemType mapstrstr(Map<String, String> fields) throws Exception {

		ReturnItemType returnVal = new ReturnItemType();

		if(fields.get("returnItemTypeId") != null) {
			returnVal.setReturnItemTypeId((String) fields.get("returnItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ReturnItemType map(GenericValue val) {

ReturnItemType returnVal = new ReturnItemType();
		returnVal.setReturnItemTypeId(val.getString("returnItemTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ReturnItemType map(HttpServletRequest request) throws Exception {

		ReturnItemType returnVal = new ReturnItemType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnItemTypeId")) {
returnVal.setReturnItemTypeId(request.getParameter("returnItemTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
