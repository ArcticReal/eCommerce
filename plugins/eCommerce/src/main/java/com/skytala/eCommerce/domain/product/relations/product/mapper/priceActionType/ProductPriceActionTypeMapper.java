package com.skytala.eCommerce.domain.product.relations.product.mapper.priceActionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.priceActionType.ProductPriceActionType;

public class ProductPriceActionTypeMapper  {


	public static Map<String, Object> map(ProductPriceActionType productpriceactiontype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpriceactiontype.getProductPriceActionTypeId() != null ){
			returnVal.put("productPriceActionTypeId",productpriceactiontype.getProductPriceActionTypeId());
}

		if(productpriceactiontype.getDescription() != null ){
			returnVal.put("description",productpriceactiontype.getDescription());
}

		return returnVal;
}


	public static ProductPriceActionType map(Map<String, Object> fields) {

		ProductPriceActionType returnVal = new ProductPriceActionType();

		if(fields.get("productPriceActionTypeId") != null) {
			returnVal.setProductPriceActionTypeId((String) fields.get("productPriceActionTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductPriceActionType mapstrstr(Map<String, String> fields) throws Exception {

		ProductPriceActionType returnVal = new ProductPriceActionType();

		if(fields.get("productPriceActionTypeId") != null) {
			returnVal.setProductPriceActionTypeId((String) fields.get("productPriceActionTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductPriceActionType map(GenericValue val) {

ProductPriceActionType returnVal = new ProductPriceActionType();
		returnVal.setProductPriceActionTypeId(val.getString("productPriceActionTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductPriceActionType map(HttpServletRequest request) throws Exception {

		ProductPriceActionType returnVal = new ProductPriceActionType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPriceActionTypeId")) {
returnVal.setProductPriceActionTypeId(request.getParameter("productPriceActionTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
