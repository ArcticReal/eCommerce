package com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.model.ProductAverageCostType;

public class ProductAverageCostTypeMapper  {


	public static Map<String, Object> map(ProductAverageCostType productaveragecosttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productaveragecosttype.getProductAverageCostTypeId() != null ){
			returnVal.put("productAverageCostTypeId",productaveragecosttype.getProductAverageCostTypeId());
}

		if(productaveragecosttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productaveragecosttype.getParentTypeId());
}

		if(productaveragecosttype.getHasTable() != null ){
			returnVal.put("hasTable",productaveragecosttype.getHasTable());
}

		if(productaveragecosttype.getDescription() != null ){
			returnVal.put("description",productaveragecosttype.getDescription());
}

		return returnVal;
}


	public static ProductAverageCostType map(Map<String, Object> fields) {

		ProductAverageCostType returnVal = new ProductAverageCostType();

		if(fields.get("productAverageCostTypeId") != null) {
			returnVal.setProductAverageCostTypeId((String) fields.get("productAverageCostTypeId"));
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
	public static ProductAverageCostType mapstrstr(Map<String, String> fields) throws Exception {

		ProductAverageCostType returnVal = new ProductAverageCostType();

		if(fields.get("productAverageCostTypeId") != null) {
			returnVal.setProductAverageCostTypeId((String) fields.get("productAverageCostTypeId"));
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
	public static ProductAverageCostType map(GenericValue val) {

ProductAverageCostType returnVal = new ProductAverageCostType();
		returnVal.setProductAverageCostTypeId(val.getString("productAverageCostTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductAverageCostType map(HttpServletRequest request) throws Exception {

		ProductAverageCostType returnVal = new ProductAverageCostType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productAverageCostTypeId")) {
returnVal.setProductAverageCostTypeId(request.getParameter("productAverageCostTypeId"));
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
