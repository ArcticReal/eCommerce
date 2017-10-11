package com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;

public class GoodIdentificationMapper  {


	public static Map<String, Object> map(GoodIdentification goodidentification) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(goodidentification.getGoodIdentificationTypeId() != null ){
			returnVal.put("goodIdentificationTypeId",goodidentification.getGoodIdentificationTypeId());
}

		if(goodidentification.getProductId() != null ){
			returnVal.put("productId",goodidentification.getProductId());
}

		if(goodidentification.getIdValue() != null ){
			returnVal.put("idValue",goodidentification.getIdValue());
}

		return returnVal;
}


	public static GoodIdentification map(Map<String, Object> fields) {

		GoodIdentification returnVal = new GoodIdentification();

		if(fields.get("goodIdentificationTypeId") != null) {
			returnVal.setGoodIdentificationTypeId((String) fields.get("goodIdentificationTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("idValue") != null) {
			returnVal.setIdValue((String) fields.get("idValue"));
}


		return returnVal;
 } 
	public static GoodIdentification mapstrstr(Map<String, String> fields) throws Exception {

		GoodIdentification returnVal = new GoodIdentification();

		if(fields.get("goodIdentificationTypeId") != null) {
			returnVal.setGoodIdentificationTypeId((String) fields.get("goodIdentificationTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("idValue") != null) {
			returnVal.setIdValue((String) fields.get("idValue"));
}


		return returnVal;
 } 
	public static GoodIdentification map(GenericValue val) {

GoodIdentification returnVal = new GoodIdentification();
		returnVal.setGoodIdentificationTypeId(val.getString("goodIdentificationTypeId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setIdValue(val.getString("idValue"));


return returnVal;

}

public static GoodIdentification map(HttpServletRequest request) throws Exception {

		GoodIdentification returnVal = new GoodIdentification();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("goodIdentificationTypeId")) {
returnVal.setGoodIdentificationTypeId(request.getParameter("goodIdentificationTypeId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("idValue"))  {
returnVal.setIdValue(request.getParameter("idValue"));
}
return returnVal;

}
}
