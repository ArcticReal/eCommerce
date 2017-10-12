package com.skytala.eCommerce.domain.humanres.relations.perfRatingType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.model.PerfRatingType;

public class PerfRatingTypeMapper  {


	public static Map<String, Object> map(PerfRatingType perfratingtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(perfratingtype.getPerfRatingTypeId() != null ){
			returnVal.put("perfRatingTypeId",perfratingtype.getPerfRatingTypeId());
}

		if(perfratingtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",perfratingtype.getParentTypeId());
}

		if(perfratingtype.getHasTable() != null ){
			returnVal.put("hasTable",perfratingtype.getHasTable());
}

		if(perfratingtype.getDescription() != null ){
			returnVal.put("description",perfratingtype.getDescription());
}

		return returnVal;
}


	public static PerfRatingType map(Map<String, Object> fields) {

		PerfRatingType returnVal = new PerfRatingType();

		if(fields.get("perfRatingTypeId") != null) {
			returnVal.setPerfRatingTypeId((String) fields.get("perfRatingTypeId"));
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
	public static PerfRatingType mapstrstr(Map<String, String> fields) throws Exception {

		PerfRatingType returnVal = new PerfRatingType();

		if(fields.get("perfRatingTypeId") != null) {
			returnVal.setPerfRatingTypeId((String) fields.get("perfRatingTypeId"));
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
	public static PerfRatingType map(GenericValue val) {

PerfRatingType returnVal = new PerfRatingType();
		returnVal.setPerfRatingTypeId(val.getString("perfRatingTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PerfRatingType map(HttpServletRequest request) throws Exception {

		PerfRatingType returnVal = new PerfRatingType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("perfRatingTypeId")) {
returnVal.setPerfRatingTypeId(request.getParameter("perfRatingTypeId"));
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
