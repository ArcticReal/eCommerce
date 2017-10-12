package com.skytala.eCommerce.domain.content.relations.contentRevisionItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.model.ContentRevisionItem;

public class ContentRevisionItemMapper  {


	public static Map<String, Object> map(ContentRevisionItem contentrevisionitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentrevisionitem.getContentId() != null ){
			returnVal.put("contentId",contentrevisionitem.getContentId());
}

		if(contentrevisionitem.getContentRevisionSeqId() != null ){
			returnVal.put("contentRevisionSeqId",contentrevisionitem.getContentRevisionSeqId());
}

		if(contentrevisionitem.getItemContentId() != null ){
			returnVal.put("itemContentId",contentrevisionitem.getItemContentId());
}

		if(contentrevisionitem.getOldDataResourceId() != null ){
			returnVal.put("oldDataResourceId",contentrevisionitem.getOldDataResourceId());
}

		if(contentrevisionitem.getNewDataResourceId() != null ){
			returnVal.put("newDataResourceId",contentrevisionitem.getNewDataResourceId());
}

		return returnVal;
}


	public static ContentRevisionItem map(Map<String, Object> fields) {

		ContentRevisionItem returnVal = new ContentRevisionItem();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentRevisionSeqId") != null) {
			returnVal.setContentRevisionSeqId((String) fields.get("contentRevisionSeqId"));
}

		if(fields.get("itemContentId") != null) {
			returnVal.setItemContentId((String) fields.get("itemContentId"));
}

		if(fields.get("oldDataResourceId") != null) {
			returnVal.setOldDataResourceId((String) fields.get("oldDataResourceId"));
}

		if(fields.get("newDataResourceId") != null) {
			returnVal.setNewDataResourceId((String) fields.get("newDataResourceId"));
}


		return returnVal;
 } 
	public static ContentRevisionItem mapstrstr(Map<String, String> fields) throws Exception {

		ContentRevisionItem returnVal = new ContentRevisionItem();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentRevisionSeqId") != null) {
			returnVal.setContentRevisionSeqId((String) fields.get("contentRevisionSeqId"));
}

		if(fields.get("itemContentId") != null) {
			returnVal.setItemContentId((String) fields.get("itemContentId"));
}

		if(fields.get("oldDataResourceId") != null) {
			returnVal.setOldDataResourceId((String) fields.get("oldDataResourceId"));
}

		if(fields.get("newDataResourceId") != null) {
			returnVal.setNewDataResourceId((String) fields.get("newDataResourceId"));
}


		return returnVal;
 } 
	public static ContentRevisionItem map(GenericValue val) {

ContentRevisionItem returnVal = new ContentRevisionItem();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setContentRevisionSeqId(val.getString("contentRevisionSeqId"));
		returnVal.setItemContentId(val.getString("itemContentId"));
		returnVal.setOldDataResourceId(val.getString("oldDataResourceId"));
		returnVal.setNewDataResourceId(val.getString("newDataResourceId"));


return returnVal;

}

public static ContentRevisionItem map(HttpServletRequest request) throws Exception {

		ContentRevisionItem returnVal = new ContentRevisionItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("contentRevisionSeqId"))  {
returnVal.setContentRevisionSeqId(request.getParameter("contentRevisionSeqId"));
}
		if(paramMap.containsKey("itemContentId"))  {
returnVal.setItemContentId(request.getParameter("itemContentId"));
}
		if(paramMap.containsKey("oldDataResourceId"))  {
returnVal.setOldDataResourceId(request.getParameter("oldDataResourceId"));
}
		if(paramMap.containsKey("newDataResourceId"))  {
returnVal.setNewDataResourceId(request.getParameter("newDataResourceId"));
}
return returnVal;

}
}
