package com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.event.SubscriptionAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.mapper.SubscriptionAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.model.SubscriptionAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscriptionAttribute extends Command {

private SubscriptionAttribute elementToBeAdded;
public AddSubscriptionAttribute(SubscriptionAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SubscriptionAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SubscriptionAttribute", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
