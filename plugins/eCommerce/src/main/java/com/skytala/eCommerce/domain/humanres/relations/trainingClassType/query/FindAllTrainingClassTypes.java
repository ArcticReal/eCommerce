
package com.skytala.eCommerce.domain.humanres.relations.trainingClassType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.event.TrainingClassTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.mapper.TrainingClassTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.model.TrainingClassType;


public class FindAllTrainingClassTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrainingClassType> returnVal = new ArrayList<TrainingClassType>();
try{
List<GenericValue> results = delegator.findAll("TrainingClassType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TrainingClassTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TrainingClassTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
