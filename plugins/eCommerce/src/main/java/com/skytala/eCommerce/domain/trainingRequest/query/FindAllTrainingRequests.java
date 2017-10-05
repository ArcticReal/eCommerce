
package com.skytala.eCommerce.domain.trainingRequest.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.trainingRequest.event.TrainingRequestFound;
import com.skytala.eCommerce.domain.trainingRequest.mapper.TrainingRequestMapper;
import com.skytala.eCommerce.domain.trainingRequest.model.TrainingRequest;


public class FindAllTrainingRequests extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrainingRequest> returnVal = new ArrayList<TrainingRequest>();
try{
List<GenericValue> results = delegator.findAll("TrainingRequest", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TrainingRequestMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TrainingRequestFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
