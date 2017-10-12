
package com.skytala.eCommerce.domain.content.relations.surveyMultiResp.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.surveyMultiResp.event.SurveyMultiRespFound;
import com.skytala.eCommerce.domain.content.relations.surveyMultiResp.mapper.SurveyMultiRespMapper;
import com.skytala.eCommerce.domain.content.relations.surveyMultiResp.model.SurveyMultiResp;


public class FindAllSurveyMultiResps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyMultiResp> returnVal = new ArrayList<SurveyMultiResp>();
try{
List<GenericValue> results = delegator.findAll("SurveyMultiResp", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyMultiRespMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyMultiRespFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
