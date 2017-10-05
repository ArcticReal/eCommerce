package com.skytala.eCommerce.domain.productPriceType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productPriceType.model.ProductPriceType;

public class ProductPriceTypeMapper  {


	public static Map<String, Object> map(ProductPriceType productpricetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpricetype.getProductPriceTypeId() != null ){
			returnVal.put("productPriceTypeId",productpricetype.getProductPriceTypeId());
}

		if(productpricetype.getDescription() != null ){
			returnVal.put("description",productpricetype.getDescription());
}

		return returnVal;
}


	public static ProductPriceType map(Map<String, Object> fields) {

		ProductPriceType returnVal = new ProductPriceType();

		if(fields.get("productPriceTypeId") != null) {
			returnVal.setProductPriceTypeId((String) fields.get("productPriceTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductPriceType mapstrstr(Map<String, String> fields) throws Exception {

		ProductPriceType returnVal = new ProductPriceType();

		if(fields.get("productPriceTypeId") != null) {
			returnVal.setProductPriceTypeId((String) fields.get("productPriceTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductPriceType map(GenericValue val) {

ProductPriceType returnVal = new ProductPriceType();
		returnVal.setProductPriceTypeId(val.getString("productPriceTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductPriceType map(HttpServletRequest request) throws Exception {

		ProductPriceType returnVal = new ProductPriceType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPriceTypeId")) {
returnVal.setProductPriceTypeId(request.getParameter("productPriceTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
