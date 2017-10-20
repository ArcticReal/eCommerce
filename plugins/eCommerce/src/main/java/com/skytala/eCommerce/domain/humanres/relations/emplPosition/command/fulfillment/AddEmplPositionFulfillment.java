package com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.fulfillment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment.EmplPositionFulfillmentAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.fulfillment.EmplPositionFulfillmentMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.fulfillment.EmplPositionFulfillment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPositionFulfillment extends Command {

private EmplPositionFulfillment elementToBeAdded;
public AddEmplPositionFulfillment(EmplPositionFulfillment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPositionFulfillment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("EmplPositionFulfillment", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionFulfillmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionFulfillmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
