package com.skytala.eCommerce.domain.humanres.relations.perfReviewItemType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.perfReviewItemType.model.PerfReviewItemType;

public class PerfReviewItemTypeMapper  {


	public static Map<String, Object> map(PerfReviewItemType perfreviewitemtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(perfreviewitemtype.getPerfReviewItemTypeId() != null ){
			returnVal.put("perfReviewItemTypeId",perfreviewitemtype.getPerfReviewItemTypeId());
}

		if(perfreviewitemtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",perfreviewitemtype.getParentTypeId());
}

		if(perfreviewitemtype.getHasTable() != null ){
			returnVal.put("hasTable",perfreviewitemtype.getHasTable());
}

		if(perfreviewitemtype.getDescription() != null ){
			returnVal.put("description",perfreviewitemtype.getDescription());
}

		return returnVal;
}


	public static PerfReviewItemType map(Map<String, Object> fields) {

		PerfReviewItemType returnVal = new PerfReviewItemType();

		if(fields.get("perfReviewItemTypeId") != null) {
			returnVal.setPerfReviewItemTypeId((String) fields.get("perfReviewItemTypeId"));
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
	public static PerfReviewItemType mapstrstr(Map<String, String> fields) throws Exception {

		PerfReviewItemType returnVal = new PerfReviewItemType();

		if(fields.get("perfReviewItemTypeId") != null) {
			returnVal.setPerfReviewItemTypeId((String) fields.get("perfReviewItemTypeId"));
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
	public static PerfReviewItemType map(GenericValue val) {

PerfReviewItemType returnVal = new PerfReviewItemType();
		returnVal.setPerfReviewItemTypeId(val.getString("perfReviewItemTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PerfReviewItemType map(HttpServletRequest request) throws Exception {

		PerfReviewItemType returnVal = new PerfReviewItemType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("perfReviewItemTypeId")) {
returnVal.setPerfReviewItemTypeId(request.getParameter("perfReviewItemTypeId"));
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
