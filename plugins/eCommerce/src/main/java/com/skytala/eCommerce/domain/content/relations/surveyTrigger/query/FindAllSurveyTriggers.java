
package com.skytala.eCommerce.domain.content.relations.surveyTrigger.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.surveyTrigger.event.SurveyTriggerFound;
import com.skytala.eCommerce.domain.content.relations.surveyTrigger.mapper.SurveyTriggerMapper;
import com.skytala.eCommerce.domain.content.relations.surveyTrigger.model.SurveyTrigger;


public class FindAllSurveyTriggers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyTrigger> returnVal = new ArrayList<SurveyTrigger>();
try{
List<GenericValue> results = delegator.findAll("SurveyTrigger", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyTriggerMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyTriggerFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
