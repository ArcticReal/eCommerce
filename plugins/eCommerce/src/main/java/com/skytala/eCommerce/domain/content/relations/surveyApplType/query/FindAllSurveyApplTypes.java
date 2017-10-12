
package com.skytala.eCommerce.domain.content.relations.surveyApplType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.surveyApplType.event.SurveyApplTypeFound;
import com.skytala.eCommerce.domain.content.relations.surveyApplType.mapper.SurveyApplTypeMapper;
import com.skytala.eCommerce.domain.content.relations.surveyApplType.model.SurveyApplType;


public class FindAllSurveyApplTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyApplType> returnVal = new ArrayList<SurveyApplType>();
try{
List<GenericValue> results = delegator.findAll("SurveyApplType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyApplTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyApplTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
