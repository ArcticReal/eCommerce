
package com.skytala.eCommerce.domain.content.relations.survey.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.survey.event.SurveyFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.SurveyMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.Survey;


public class FindAllSurveys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Survey> returnVal = new ArrayList<Survey>();
try{
List<GenericValue> results = delegator.findAll("Survey", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
