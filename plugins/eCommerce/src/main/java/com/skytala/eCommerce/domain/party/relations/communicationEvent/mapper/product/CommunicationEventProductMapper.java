package com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.product.CommunicationEventProduct;

public class CommunicationEventProductMapper  {


	public static Map<String, Object> map(CommunicationEventProduct communicationeventproduct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationeventproduct.getProductId() != null ){
			returnVal.put("productId",communicationeventproduct.getProductId());
}

		if(communicationeventproduct.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",communicationeventproduct.getCommunicationEventId());
}

		return returnVal;
}


	public static CommunicationEventProduct map(Map<String, Object> fields) {

		CommunicationEventProduct returnVal = new CommunicationEventProduct();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static CommunicationEventProduct mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEventProduct returnVal = new CommunicationEventProduct();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static CommunicationEventProduct map(GenericValue val) {

CommunicationEventProduct returnVal = new CommunicationEventProduct();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));


return returnVal;

}

public static CommunicationEventProduct map(HttpServletRequest request) throws Exception {

		CommunicationEventProduct returnVal = new CommunicationEventProduct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
return returnVal;

}
}
