package com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.type.GoodIdentificationType;

public class GoodIdentificationTypeMapper  {


	public static Map<String, Object> map(GoodIdentificationType goodidentificationtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(goodidentificationtype.getGoodIdentificationTypeId() != null ){
			returnVal.put("goodIdentificationTypeId",goodidentificationtype.getGoodIdentificationTypeId());
}

		if(goodidentificationtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",goodidentificationtype.getParentTypeId());
}

		if(goodidentificationtype.getHasTable() != null ){
			returnVal.put("hasTable",goodidentificationtype.getHasTable());
}

		if(goodidentificationtype.getDescription() != null ){
			returnVal.put("description",goodidentificationtype.getDescription());
}

		return returnVal;
}


	public static GoodIdentificationType map(Map<String, Object> fields) {

		GoodIdentificationType returnVal = new GoodIdentificationType();

		if(fields.get("goodIdentificationTypeId") != null) {
			returnVal.setGoodIdentificationTypeId((String) fields.get("goodIdentificationTypeId"));
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
	public static GoodIdentificationType mapstrstr(Map<String, String> fields) throws Exception {

		GoodIdentificationType returnVal = new GoodIdentificationType();

		if(fields.get("goodIdentificationTypeId") != null) {
			returnVal.setGoodIdentificationTypeId((String) fields.get("goodIdentificationTypeId"));
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
	public static GoodIdentificationType map(GenericValue val) {

GoodIdentificationType returnVal = new GoodIdentificationType();
		returnVal.setGoodIdentificationTypeId(val.getString("goodIdentificationTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static GoodIdentificationType map(HttpServletRequest request) throws Exception {

		GoodIdentificationType returnVal = new GoodIdentificationType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("goodIdentificationTypeId")) {
returnVal.setGoodIdentificationTypeId(request.getParameter("goodIdentificationTypeId"));
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
