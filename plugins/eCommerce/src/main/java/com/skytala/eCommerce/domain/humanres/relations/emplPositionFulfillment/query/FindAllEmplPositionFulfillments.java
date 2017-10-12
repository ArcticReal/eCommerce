
package com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.event.EmplPositionFulfillmentFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.mapper.EmplPositionFulfillmentMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.model.EmplPositionFulfillment;


public class FindAllEmplPositionFulfillments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPositionFulfillment> returnVal = new ArrayList<EmplPositionFulfillment>();
try{
List<GenericValue> results = delegator.findAll("EmplPositionFulfillment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplPositionFulfillmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplPositionFulfillmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
