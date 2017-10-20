
package com.skytala.eCommerce.domain.content.relations.survey.query.page;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.survey.event.page.SurveyPageFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.page.SurveyPageMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.page.SurveyPage;


public class FindAllSurveyPages extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyPage> returnVal = new ArrayList<SurveyPage>();
try{
List<GenericValue> results = delegator.findAll("SurveyPage", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyPageMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyPageFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
