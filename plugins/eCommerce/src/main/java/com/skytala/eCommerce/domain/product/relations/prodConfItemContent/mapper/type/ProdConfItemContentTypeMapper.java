package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.type.ProdConfItemContentType;

public class ProdConfItemContentTypeMapper  {


	public static Map<String, Object> map(ProdConfItemContentType prodconfitemcontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prodconfitemcontenttype.getConfItemContentTypeId() != null ){
			returnVal.put("confItemContentTypeId",prodconfitemcontenttype.getConfItemContentTypeId());
}

		if(prodconfitemcontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",prodconfitemcontenttype.getParentTypeId());
}

		if(prodconfitemcontenttype.getHasTable() != null ){
			returnVal.put("hasTable",prodconfitemcontenttype.getHasTable());
}

		if(prodconfitemcontenttype.getDescription() != null ){
			returnVal.put("description",prodconfitemcontenttype.getDescription());
}

		return returnVal;
}


	public static ProdConfItemContentType map(Map<String, Object> fields) {

		ProdConfItemContentType returnVal = new ProdConfItemContentType();

		if(fields.get("confItemContentTypeId") != null) {
			returnVal.setConfItemContentTypeId((String) fields.get("confItemContentTypeId"));
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
	public static ProdConfItemContentType mapstrstr(Map<String, String> fields) throws Exception {

		ProdConfItemContentType returnVal = new ProdConfItemContentType();

		if(fields.get("confItemContentTypeId") != null) {
			returnVal.setConfItemContentTypeId((String) fields.get("confItemContentTypeId"));
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
	public static ProdConfItemContentType map(GenericValue val) {

ProdConfItemContentType returnVal = new ProdConfItemContentType();
		returnVal.setConfItemContentTypeId(val.getString("confItemContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProdConfItemContentType map(HttpServletRequest request) throws Exception {

		ProdConfItemContentType returnVal = new ProdConfItemContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("confItemContentTypeId")) {
returnVal.setConfItemContentTypeId(request.getParameter("confItemContentTypeId"));
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
