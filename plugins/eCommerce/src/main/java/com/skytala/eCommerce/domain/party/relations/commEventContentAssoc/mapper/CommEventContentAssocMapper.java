package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;

public class CommEventContentAssocMapper  {


	public static Map<String, Object> map(CommEventContentAssoc commeventcontentassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(commeventcontentassoc.getContentId() != null ){
			returnVal.put("contentId",commeventcontentassoc.getContentId());
}

		if(commeventcontentassoc.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",commeventcontentassoc.getCommunicationEventId());
}

		if(commeventcontentassoc.getCommContentAssocTypeId() != null ){
			returnVal.put("commContentAssocTypeId",commeventcontentassoc.getCommContentAssocTypeId());
}

		if(commeventcontentassoc.getFromDate() != null ){
			returnVal.put("fromDate",commeventcontentassoc.getFromDate());
}

		if(commeventcontentassoc.getThruDate() != null ){
			returnVal.put("thruDate",commeventcontentassoc.getThruDate());
}

		if(commeventcontentassoc.getSequenceNum() != null ){
			returnVal.put("sequenceNum",commeventcontentassoc.getSequenceNum());
}

		return returnVal;
}


	public static CommEventContentAssoc map(Map<String, Object> fields) {

		CommEventContentAssoc returnVal = new CommEventContentAssoc();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("commContentAssocTypeId") != null) {
			returnVal.setCommContentAssocTypeId((String) fields.get("commContentAssocTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static CommEventContentAssoc mapstrstr(Map<String, String> fields) throws Exception {

		CommEventContentAssoc returnVal = new CommEventContentAssoc();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("commContentAssocTypeId") != null) {
			returnVal.setCommContentAssocTypeId((String) fields.get("commContentAssocTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static CommEventContentAssoc map(GenericValue val) {

CommEventContentAssoc returnVal = new CommEventContentAssoc();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setCommContentAssocTypeId(val.getString("commContentAssocTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static CommEventContentAssoc map(HttpServletRequest request) throws Exception {

		CommEventContentAssoc returnVal = new CommEventContentAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
		if(paramMap.containsKey("commContentAssocTypeId"))  {
returnVal.setCommContentAssocTypeId(request.getParameter("commContentAssocTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
