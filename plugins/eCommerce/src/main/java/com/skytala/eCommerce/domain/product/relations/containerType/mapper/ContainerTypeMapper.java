package com.skytala.eCommerce.domain.product.relations.containerType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.containerType.model.ContainerType;

public class ContainerTypeMapper  {


	public static Map<String, Object> map(ContainerType containertype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(containertype.getContainerTypeId() != null ){
			returnVal.put("containerTypeId",containertype.getContainerTypeId());
}

		if(containertype.getDescription() != null ){
			returnVal.put("description",containertype.getDescription());
}

		return returnVal;
}


	public static ContainerType map(Map<String, Object> fields) {

		ContainerType returnVal = new ContainerType();

		if(fields.get("containerTypeId") != null) {
			returnVal.setContainerTypeId((String) fields.get("containerTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContainerType mapstrstr(Map<String, String> fields) throws Exception {

		ContainerType returnVal = new ContainerType();

		if(fields.get("containerTypeId") != null) {
			returnVal.setContainerTypeId((String) fields.get("containerTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContainerType map(GenericValue val) {

ContainerType returnVal = new ContainerType();
		returnVal.setContainerTypeId(val.getString("containerTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContainerType map(HttpServletRequest request) throws Exception {

		ContainerType returnVal = new ContainerType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("containerTypeId")) {
returnVal.setContainerTypeId(request.getParameter("containerTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
