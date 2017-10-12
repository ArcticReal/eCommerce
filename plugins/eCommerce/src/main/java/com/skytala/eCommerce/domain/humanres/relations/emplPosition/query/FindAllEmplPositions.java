
package com.skytala.eCommerce.domain.humanres.relations.emplPosition.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.EmplPositionFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.EmplPositionMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.EmplPosition;


public class FindAllEmplPositions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPosition> returnVal = new ArrayList<EmplPosition>();
try{
List<GenericValue> results = delegator.findAll("EmplPosition", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplPositionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplPositionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
