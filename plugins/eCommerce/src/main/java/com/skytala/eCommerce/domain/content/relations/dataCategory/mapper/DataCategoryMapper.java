package com.skytala.eCommerce.domain.content.relations.dataCategory.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataCategory.model.DataCategory;

public class DataCategoryMapper  {


	public static Map<String, Object> map(DataCategory datacategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(datacategory.getDataCategoryId() != null ){
			returnVal.put("dataCategoryId",datacategory.getDataCategoryId());
}

		if(datacategory.getParentCategoryId() != null ){
			returnVal.put("parentCategoryId",datacategory.getParentCategoryId());
}

		if(datacategory.getCategoryName() != null ){
			returnVal.put("categoryName",datacategory.getCategoryName());
}

		return returnVal;
}


	public static DataCategory map(Map<String, Object> fields) {

		DataCategory returnVal = new DataCategory();

		if(fields.get("dataCategoryId") != null) {
			returnVal.setDataCategoryId((String) fields.get("dataCategoryId"));
}

		if(fields.get("parentCategoryId") != null) {
			returnVal.setParentCategoryId((String) fields.get("parentCategoryId"));
}

		if(fields.get("categoryName") != null) {
			returnVal.setCategoryName((String) fields.get("categoryName"));
}


		return returnVal;
 } 
	public static DataCategory mapstrstr(Map<String, String> fields) throws Exception {

		DataCategory returnVal = new DataCategory();

		if(fields.get("dataCategoryId") != null) {
			returnVal.setDataCategoryId((String) fields.get("dataCategoryId"));
}

		if(fields.get("parentCategoryId") != null) {
			returnVal.setParentCategoryId((String) fields.get("parentCategoryId"));
}

		if(fields.get("categoryName") != null) {
			returnVal.setCategoryName((String) fields.get("categoryName"));
}


		return returnVal;
 } 
	public static DataCategory map(GenericValue val) {

DataCategory returnVal = new DataCategory();
		returnVal.setDataCategoryId(val.getString("dataCategoryId"));
		returnVal.setParentCategoryId(val.getString("parentCategoryId"));
		returnVal.setCategoryName(val.getString("categoryName"));


return returnVal;

}

public static DataCategory map(HttpServletRequest request) throws Exception {

		DataCategory returnVal = new DataCategory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataCategoryId")) {
returnVal.setDataCategoryId(request.getParameter("dataCategoryId"));
}

		if(paramMap.containsKey("parentCategoryId"))  {
returnVal.setParentCategoryId(request.getParameter("parentCategoryId"));
}
		if(paramMap.containsKey("categoryName"))  {
returnVal.setCategoryName(request.getParameter("categoryName"));
}
return returnVal;

}
}
