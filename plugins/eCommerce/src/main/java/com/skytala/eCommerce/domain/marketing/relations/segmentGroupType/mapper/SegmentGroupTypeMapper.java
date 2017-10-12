package com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.model.SegmentGroupType;

public class SegmentGroupTypeMapper  {


	public static Map<String, Object> map(SegmentGroupType segmentgrouptype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(segmentgrouptype.getSegmentGroupTypeId() != null ){
			returnVal.put("segmentGroupTypeId",segmentgrouptype.getSegmentGroupTypeId());
}

		if(segmentgrouptype.getDescription() != null ){
			returnVal.put("description",segmentgrouptype.getDescription());
}

		return returnVal;
}


	public static SegmentGroupType map(Map<String, Object> fields) {

		SegmentGroupType returnVal = new SegmentGroupType();

		if(fields.get("segmentGroupTypeId") != null) {
			returnVal.setSegmentGroupTypeId((String) fields.get("segmentGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SegmentGroupType mapstrstr(Map<String, String> fields) throws Exception {

		SegmentGroupType returnVal = new SegmentGroupType();

		if(fields.get("segmentGroupTypeId") != null) {
			returnVal.setSegmentGroupTypeId((String) fields.get("segmentGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SegmentGroupType map(GenericValue val) {

SegmentGroupType returnVal = new SegmentGroupType();
		returnVal.setSegmentGroupTypeId(val.getString("segmentGroupTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SegmentGroupType map(HttpServletRequest request) throws Exception {

		SegmentGroupType returnVal = new SegmentGroupType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("segmentGroupTypeId")) {
returnVal.setSegmentGroupTypeId(request.getParameter("segmentGroupTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
