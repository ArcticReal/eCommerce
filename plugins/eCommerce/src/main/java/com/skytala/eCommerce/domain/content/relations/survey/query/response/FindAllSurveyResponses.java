
package com.skytala.eCommerce.domain.content.relations.survey.query.response;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.survey.event.response.SurveyResponseFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.response.SurveyResponseMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.response.SurveyResponse;


public class FindAllSurveyResponses extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyResponse> returnVal = new ArrayList<SurveyResponse>();
try{
List<GenericValue> results = delegator.findAll("SurveyResponse", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyResponseMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyResponseFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
