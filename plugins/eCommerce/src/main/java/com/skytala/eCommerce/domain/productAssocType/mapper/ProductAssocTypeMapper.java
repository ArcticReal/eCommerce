package com.skytala.eCommerce.domain.productAssocType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productAssocType.model.ProductAssocType;

public class ProductAssocTypeMapper  {


	public static Map<String, Object> map(ProductAssocType productassoctype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productassoctype.getProductAssocTypeId() != null ){
			returnVal.put("productAssocTypeId",productassoctype.getProductAssocTypeId());
}

		if(productassoctype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productassoctype.getParentTypeId());
}

		if(productassoctype.getHasTable() != null ){
			returnVal.put("hasTable",productassoctype.getHasTable());
}

		if(productassoctype.getDescription() != null ){
			returnVal.put("description",productassoctype.getDescription());
}

		return returnVal;
}


	public static ProductAssocType map(Map<String, Object> fields) {

		ProductAssocType returnVal = new ProductAssocType();

		if(fields.get("productAssocTypeId") != null) {
			returnVal.setProductAssocTypeId((String) fields.get("productAssocTypeId"));
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
	public static ProductAssocType mapstrstr(Map<String, String> fields) throws Exception {

		ProductAssocType returnVal = new ProductAssocType();

		if(fields.get("productAssocTypeId") != null) {
			returnVal.setProductAssocTypeId((String) fields.get("productAssocTypeId"));
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
	public static ProductAssocType map(GenericValue val) {

ProductAssocType returnVal = new ProductAssocType();
		returnVal.setProductAssocTypeId(val.getString("productAssocTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductAssocType map(HttpServletRequest request) throws Exception {

		ProductAssocType returnVal = new ProductAssocType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productAssocTypeId")) {
returnVal.setProductAssocTypeId(request.getParameter("productAssocTypeId"));
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
