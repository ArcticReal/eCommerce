
package com.skytala.eCommerce.domain.party.relations.priorityType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.priorityType.event.PriorityTypeFound;
import com.skytala.eCommerce.domain.party.relations.priorityType.mapper.PriorityTypeMapper;
import com.skytala.eCommerce.domain.party.relations.priorityType.model.PriorityType;


public class FindAllPriorityTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PriorityType> returnVal = new ArrayList<PriorityType>();
try{
List<GenericValue> results = delegator.findAll("PriorityType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PriorityTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PriorityTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
