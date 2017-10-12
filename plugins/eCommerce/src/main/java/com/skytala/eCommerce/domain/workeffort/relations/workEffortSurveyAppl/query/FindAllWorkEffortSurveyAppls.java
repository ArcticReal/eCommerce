
package com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.event.WorkEffortSurveyApplFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.mapper.WorkEffortSurveyApplMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSurveyAppl.model.WorkEffortSurveyAppl;


public class FindAllWorkEffortSurveyAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortSurveyAppl> returnVal = new ArrayList<WorkEffortSurveyAppl>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortSurveyAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortSurveyApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortSurveyApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
