package com.skytala.eCommerce.domain.product.relations.container.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.container.model.Container;

public class ContainerMapper  {


	public static Map<String, Object> map(Container container) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(container.getContainerId() != null ){
			returnVal.put("containerId",container.getContainerId());
}

		if(container.getContainerTypeId() != null ){
			returnVal.put("containerTypeId",container.getContainerTypeId());
}

		if(container.getFacilityId() != null ){
			returnVal.put("facilityId",container.getFacilityId());
}

		if(container.getDescription() != null ){
			returnVal.put("description",container.getDescription());
}

		return returnVal;
}


	public static Container map(Map<String, Object> fields) {

		Container returnVal = new Container();

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("containerTypeId") != null) {
			returnVal.setContainerTypeId((String) fields.get("containerTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static Container mapstrstr(Map<String, String> fields) throws Exception {

		Container returnVal = new Container();

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("containerTypeId") != null) {
			returnVal.setContainerTypeId((String) fields.get("containerTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static Container map(GenericValue val) {

Container returnVal = new Container();
		returnVal.setContainerId(val.getString("containerId"));
		returnVal.setContainerTypeId(val.getString("containerTypeId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static Container map(HttpServletRequest request) throws Exception {

		Container returnVal = new Container();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("containerId")) {
returnVal.setContainerId(request.getParameter("containerId"));
}

		if(paramMap.containsKey("containerTypeId"))  {
returnVal.setContainerTypeId(request.getParameter("containerTypeId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
