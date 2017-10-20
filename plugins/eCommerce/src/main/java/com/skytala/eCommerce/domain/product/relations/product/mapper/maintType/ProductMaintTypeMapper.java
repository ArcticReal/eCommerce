package com.skytala.eCommerce.domain.product.relations.product.mapper.maintType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.maintType.ProductMaintType;

public class ProductMaintTypeMapper  {


	public static Map<String, Object> map(ProductMaintType productmainttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productmainttype.getProductMaintTypeId() != null ){
			returnVal.put("productMaintTypeId",productmainttype.getProductMaintTypeId());
}

		if(productmainttype.getDescription() != null ){
			returnVal.put("description",productmainttype.getDescription());
}

		if(productmainttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productmainttype.getParentTypeId());
}

		return returnVal;
}


	public static ProductMaintType map(Map<String, Object> fields) {

		ProductMaintType returnVal = new ProductMaintType();

		if(fields.get("productMaintTypeId") != null) {
			returnVal.setProductMaintTypeId((String) fields.get("productMaintTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}


		return returnVal;
 } 
	public static ProductMaintType mapstrstr(Map<String, String> fields) throws Exception {

		ProductMaintType returnVal = new ProductMaintType();

		if(fields.get("productMaintTypeId") != null) {
			returnVal.setProductMaintTypeId((String) fields.get("productMaintTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}


		return returnVal;
 } 
	public static ProductMaintType map(GenericValue val) {

ProductMaintType returnVal = new ProductMaintType();
		returnVal.setProductMaintTypeId(val.getString("productMaintTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));


return returnVal;

}

public static ProductMaintType map(HttpServletRequest request) throws Exception {

		ProductMaintType returnVal = new ProductMaintType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productMaintTypeId")) {
returnVal.setProductMaintTypeId(request.getParameter("productMaintTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
return returnVal;

}
}
