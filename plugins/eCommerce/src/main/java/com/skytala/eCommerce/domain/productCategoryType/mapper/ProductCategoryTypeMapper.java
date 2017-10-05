package com.skytala.eCommerce.domain.productCategoryType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productCategoryType.model.ProductCategoryType;

public class ProductCategoryTypeMapper  {


	public static Map<String, Object> map(ProductCategoryType productcategorytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategorytype.getProductCategoryTypeId() != null ){
			returnVal.put("productCategoryTypeId",productcategorytype.getProductCategoryTypeId());
}

		if(productcategorytype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productcategorytype.getParentTypeId());
}

		if(productcategorytype.getHasTable() != null ){
			returnVal.put("hasTable",productcategorytype.getHasTable());
}

		if(productcategorytype.getDescription() != null ){
			returnVal.put("description",productcategorytype.getDescription());
}

		return returnVal;
}


	public static ProductCategoryType map(Map<String, Object> fields) {

		ProductCategoryType returnVal = new ProductCategoryType();

		if(fields.get("productCategoryTypeId") != null) {
			returnVal.setProductCategoryTypeId((String) fields.get("productCategoryTypeId"));
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
	public static ProductCategoryType mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryType returnVal = new ProductCategoryType();

		if(fields.get("productCategoryTypeId") != null) {
			returnVal.setProductCategoryTypeId((String) fields.get("productCategoryTypeId"));
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
	public static ProductCategoryType map(GenericValue val) {

ProductCategoryType returnVal = new ProductCategoryType();
		returnVal.setProductCategoryTypeId(val.getString("productCategoryTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductCategoryType map(HttpServletRequest request) throws Exception {

		ProductCategoryType returnVal = new ProductCategoryType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryTypeId")) {
returnVal.setProductCategoryTypeId(request.getParameter("productCategoryTypeId"));
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
