
package com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.event.SubscriptionCommEventFound;
import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.mapper.SubscriptionCommEventMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.model.SubscriptionCommEvent;


public class FindAllSubscriptionCommEvents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionCommEvent> returnVal = new ArrayList<SubscriptionCommEvent>();
try{
List<GenericValue> results = delegator.findAll("SubscriptionCommEvent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionCommEventMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionCommEventFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
