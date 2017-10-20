package com.skytala.eCommerce.domain.product.relations.product.mapper.configStats;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.configStats.ProductConfigStats;

public class ProductConfigStatsMapper  {


	public static Map<String, Object> map(ProductConfigStats productconfigstats) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productconfigstats.getConfigId() != null ){
			returnVal.put("configId",productconfigstats.getConfigId());
}

		if(productconfigstats.getProductId() != null ){
			returnVal.put("productId",productconfigstats.getProductId());
}

		if(productconfigstats.getNumOfConfs() != null ){
			returnVal.put("numOfConfs",productconfigstats.getNumOfConfs());
}

		if(productconfigstats.getConfigTypeId() != null ){
			returnVal.put("configTypeId",productconfigstats.getConfigTypeId());
}

		return returnVal;
}


	public static ProductConfigStats map(Map<String, Object> fields) {

		ProductConfigStats returnVal = new ProductConfigStats();

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("numOfConfs") != null) {
			returnVal.setNumOfConfs((long) fields.get("numOfConfs"));
}

		if(fields.get("configTypeId") != null) {
			returnVal.setConfigTypeId((String) fields.get("configTypeId"));
}


		return returnVal;
 } 
	public static ProductConfigStats mapstrstr(Map<String, String> fields) throws Exception {

		ProductConfigStats returnVal = new ProductConfigStats();

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("numOfConfs") != null) {
String buf;
buf = fields.get("numOfConfs");
long ibuf = Long.parseLong(buf);
			returnVal.setNumOfConfs(ibuf);
}

		if(fields.get("configTypeId") != null) {
			returnVal.setConfigTypeId((String) fields.get("configTypeId"));
}


		return returnVal;
 } 
	public static ProductConfigStats map(GenericValue val) {

ProductConfigStats returnVal = new ProductConfigStats();
		returnVal.setConfigId(val.getString("configId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setNumOfConfs(val.getLong("numOfConfs"));
		returnVal.setConfigTypeId(val.getString("configTypeId"));


return returnVal;

}

public static ProductConfigStats map(HttpServletRequest request) throws Exception {

		ProductConfigStats returnVal = new ProductConfigStats();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("configId")) {
returnVal.setConfigId(request.getParameter("configId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("numOfConfs"))  {
String buf = request.getParameter("numOfConfs");
Long ibuf = Long.parseLong(buf);
returnVal.setNumOfConfs(ibuf);
}
		if(paramMap.containsKey("configTypeId"))  {
returnVal.setConfigTypeId(request.getParameter("configTypeId"));
}
return returnVal;

}
}
