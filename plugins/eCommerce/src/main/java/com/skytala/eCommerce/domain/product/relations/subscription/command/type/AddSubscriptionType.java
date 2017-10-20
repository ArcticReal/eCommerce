package com.skytala.eCommerce.domain.product.relations.subscription.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscription.event.type.SubscriptionTypeAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.type.SubscriptionTypeMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.type.SubscriptionType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscriptionType extends Command {

private SubscriptionType elementToBeAdded;
public AddSubscriptionType(SubscriptionType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SubscriptionType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSubscriptionTypeId(delegator.getNextSeqId("SubscriptionType"));
GenericValue newValue = delegator.makeValue("SubscriptionType", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
