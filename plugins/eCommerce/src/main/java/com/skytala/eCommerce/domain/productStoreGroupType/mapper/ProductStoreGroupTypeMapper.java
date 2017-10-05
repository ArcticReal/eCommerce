package com.skytala.eCommerce.domain.productStoreGroupType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productStoreGroupType.model.ProductStoreGroupType;

public class ProductStoreGroupTypeMapper  {


	public static Map<String, Object> map(ProductStoreGroupType productstoregrouptype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoregrouptype.getProductStoreGroupTypeId() != null ){
			returnVal.put("productStoreGroupTypeId",productstoregrouptype.getProductStoreGroupTypeId());
}

		if(productstoregrouptype.getDescription() != null ){
			returnVal.put("description",productstoregrouptype.getDescription());
}

		return returnVal;
}


	public static ProductStoreGroupType map(Map<String, Object> fields) {

		ProductStoreGroupType returnVal = new ProductStoreGroupType();

		if(fields.get("productStoreGroupTypeId") != null) {
			returnVal.setProductStoreGroupTypeId((String) fields.get("productStoreGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductStoreGroupType mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreGroupType returnVal = new ProductStoreGroupType();

		if(fields.get("productStoreGroupTypeId") != null) {
			returnVal.setProductStoreGroupTypeId((String) fields.get("productStoreGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductStoreGroupType map(GenericValue val) {

ProductStoreGroupType returnVal = new ProductStoreGroupType();
		returnVal.setProductStoreGroupTypeId(val.getString("productStoreGroupTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductStoreGroupType map(HttpServletRequest request) throws Exception {

		ProductStoreGroupType returnVal = new ProductStoreGroupType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreGroupTypeId")) {
returnVal.setProductStoreGroupTypeId(request.getParameter("productStoreGroupTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
