package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.classification;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.classification.SegmentGroupClassification;

public class SegmentGroupClassificationMapper  {


	public static Map<String, Object> map(SegmentGroupClassification segmentgroupclassification) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(segmentgroupclassification.getSegmentGroupId() != null ){
			returnVal.put("segmentGroupId",segmentgroupclassification.getSegmentGroupId());
}

		if(segmentgroupclassification.getPartyClassificationGroupId() != null ){
			returnVal.put("partyClassificationGroupId",segmentgroupclassification.getPartyClassificationGroupId());
}

		return returnVal;
}


	public static SegmentGroupClassification map(Map<String, Object> fields) {

		SegmentGroupClassification returnVal = new SegmentGroupClassification();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
}


		return returnVal;
 } 
	public static SegmentGroupClassification mapstrstr(Map<String, String> fields) throws Exception {

		SegmentGroupClassification returnVal = new SegmentGroupClassification();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
}


		return returnVal;
 } 
	public static SegmentGroupClassification map(GenericValue val) {

SegmentGroupClassification returnVal = new SegmentGroupClassification();
		returnVal.setSegmentGroupId(val.getString("segmentGroupId"));
		returnVal.setPartyClassificationGroupId(val.getString("partyClassificationGroupId"));


return returnVal;

}

public static SegmentGroupClassification map(HttpServletRequest request) throws Exception {

		SegmentGroupClassification returnVal = new SegmentGroupClassification();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("segmentGroupId")) {
returnVal.setSegmentGroupId(request.getParameter("segmentGroupId"));
}

		if(paramMap.containsKey("partyClassificationGroupId"))  {
returnVal.setPartyClassificationGroupId(request.getParameter("partyClassificationGroupId"));
}
return returnVal;

}
}
