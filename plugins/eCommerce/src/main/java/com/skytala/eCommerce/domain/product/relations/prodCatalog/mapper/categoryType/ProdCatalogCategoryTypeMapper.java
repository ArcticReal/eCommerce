package com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.categoryType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType.ProdCatalogCategoryType;

public class ProdCatalogCategoryTypeMapper  {


	public static Map<String, Object> map(ProdCatalogCategoryType prodcatalogcategorytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prodcatalogcategorytype.getProdCatalogCategoryTypeId() != null ){
			returnVal.put("prodCatalogCategoryTypeId",prodcatalogcategorytype.getProdCatalogCategoryTypeId());
}

		if(prodcatalogcategorytype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",prodcatalogcategorytype.getParentTypeId());
}

		if(prodcatalogcategorytype.getDescription() != null ){
			returnVal.put("description",prodcatalogcategorytype.getDescription());
}

		return returnVal;
}


	public static ProdCatalogCategoryType map(Map<String, Object> fields) {

		ProdCatalogCategoryType returnVal = new ProdCatalogCategoryType();

		if(fields.get("prodCatalogCategoryTypeId") != null) {
			returnVal.setProdCatalogCategoryTypeId((String) fields.get("prodCatalogCategoryTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProdCatalogCategoryType mapstrstr(Map<String, String> fields) throws Exception {

		ProdCatalogCategoryType returnVal = new ProdCatalogCategoryType();

		if(fields.get("prodCatalogCategoryTypeId") != null) {
			returnVal.setProdCatalogCategoryTypeId((String) fields.get("prodCatalogCategoryTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProdCatalogCategoryType map(GenericValue val) {

ProdCatalogCategoryType returnVal = new ProdCatalogCategoryType();
		returnVal.setProdCatalogCategoryTypeId(val.getString("prodCatalogCategoryTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProdCatalogCategoryType map(HttpServletRequest request) throws Exception {

		ProdCatalogCategoryType returnVal = new ProdCatalogCategoryType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("prodCatalogCategoryTypeId")) {
returnVal.setProdCatalogCategoryTypeId(request.getParameter("prodCatalogCategoryTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
