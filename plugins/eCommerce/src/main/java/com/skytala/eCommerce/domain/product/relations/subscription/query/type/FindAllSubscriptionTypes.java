
package com.skytala.eCommerce.domain.product.relations.subscription.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscription.event.type.SubscriptionTypeFound;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.type.SubscriptionTypeMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.type.SubscriptionType;


public class FindAllSubscriptionTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionType> returnVal = new ArrayList<SubscriptionType>();
try{
List<GenericValue> results = delegator.findAll("SubscriptionType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
