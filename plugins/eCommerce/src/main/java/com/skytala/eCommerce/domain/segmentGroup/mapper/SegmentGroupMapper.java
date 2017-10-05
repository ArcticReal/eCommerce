package com.skytala.eCommerce.domain.segmentGroup.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.segmentGroup.model.SegmentGroup;

public class SegmentGroupMapper  {


	public static Map<String, Object> map(SegmentGroup segmentgroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(segmentgroup.getSegmentGroupId() != null ){
			returnVal.put("segmentGroupId",segmentgroup.getSegmentGroupId());
}

		if(segmentgroup.getSegmentGroupTypeId() != null ){
			returnVal.put("segmentGroupTypeId",segmentgroup.getSegmentGroupTypeId());
}

		if(segmentgroup.getDescription() != null ){
			returnVal.put("description",segmentgroup.getDescription());
}

		if(segmentgroup.getProductStoreId() != null ){
			returnVal.put("productStoreId",segmentgroup.getProductStoreId());
}

		return returnVal;
}


	public static SegmentGroup map(Map<String, Object> fields) {

		SegmentGroup returnVal = new SegmentGroup();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("segmentGroupTypeId") != null) {
			returnVal.setSegmentGroupTypeId((String) fields.get("segmentGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}


		return returnVal;
 } 
	public static SegmentGroup mapstrstr(Map<String, String> fields) throws Exception {

		SegmentGroup returnVal = new SegmentGroup();

		if(fields.get("segmentGroupId") != null) {
			returnVal.setSegmentGroupId((String) fields.get("segmentGroupId"));
}

		if(fields.get("segmentGroupTypeId") != null) {
			returnVal.setSegmentGroupTypeId((String) fields.get("segmentGroupTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}


		return returnVal;
 } 
	public static SegmentGroup map(GenericValue val) {

SegmentGroup returnVal = new SegmentGroup();
		returnVal.setSegmentGroupId(val.getString("segmentGroupId"));
		returnVal.setSegmentGroupTypeId(val.getString("segmentGroupTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setProductStoreId(val.getString("productStoreId"));


return returnVal;

}

public static SegmentGroup map(HttpServletRequest request) throws Exception {

		SegmentGroup returnVal = new SegmentGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("segmentGroupId")) {
returnVal.setSegmentGroupId(request.getParameter("segmentGroupId"));
}

		if(paramMap.containsKey("segmentGroupTypeId"))  {
returnVal.setSegmentGroupTypeId(request.getParameter("segmentGroupTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
return returnVal;

}
}
