package com.skytala.eCommerce.domain.content.relations.content.mapper.revision;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.model.revision.ContentRevision;

public class ContentRevisionMapper  {


	public static Map<String, Object> map(ContentRevision contentrevision) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentrevision.getContentId() != null ){
			returnVal.put("contentId",contentrevision.getContentId());
}

		if(contentrevision.getContentRevisionSeqId() != null ){
			returnVal.put("contentRevisionSeqId",contentrevision.getContentRevisionSeqId());
}

		if(contentrevision.getCommittedByPartyId() != null ){
			returnVal.put("committedByPartyId",contentrevision.getCommittedByPartyId());
}

		if(contentrevision.getComments() != null ){
			returnVal.put("comments",contentrevision.getComments());
}

		return returnVal;
}


	public static ContentRevision map(Map<String, Object> fields) {

		ContentRevision returnVal = new ContentRevision();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentRevisionSeqId") != null) {
			returnVal.setContentRevisionSeqId((String) fields.get("contentRevisionSeqId"));
}

		if(fields.get("committedByPartyId") != null) {
			returnVal.setCommittedByPartyId((String) fields.get("committedByPartyId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ContentRevision mapstrstr(Map<String, String> fields) throws Exception {

		ContentRevision returnVal = new ContentRevision();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("contentRevisionSeqId") != null) {
			returnVal.setContentRevisionSeqId((String) fields.get("contentRevisionSeqId"));
}

		if(fields.get("committedByPartyId") != null) {
			returnVal.setCommittedByPartyId((String) fields.get("committedByPartyId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ContentRevision map(GenericValue val) {

ContentRevision returnVal = new ContentRevision();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setContentRevisionSeqId(val.getString("contentRevisionSeqId"));
		returnVal.setCommittedByPartyId(val.getString("committedByPartyId"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static ContentRevision map(HttpServletRequest request) throws Exception {

		ContentRevision returnVal = new ContentRevision();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("contentRevisionSeqId"))  {
returnVal.setContentRevisionSeqId(request.getParameter("contentRevisionSeqId"));
}
		if(paramMap.containsKey("committedByPartyId"))  {
returnVal.setCommittedByPartyId(request.getParameter("committedByPartyId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
