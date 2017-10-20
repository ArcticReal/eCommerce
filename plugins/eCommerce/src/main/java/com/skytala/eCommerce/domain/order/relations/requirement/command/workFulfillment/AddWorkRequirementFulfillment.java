package com.skytala.eCommerce.domain.order.relations.requirement.command.workFulfillment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment.WorkRequirementFulfillmentAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.workFulfillment.WorkRequirementFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.workFulfillment.WorkRequirementFulfillment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkRequirementFulfillment extends Command {

private WorkRequirementFulfillment elementToBeAdded;
public AddWorkRequirementFulfillment(WorkRequirementFulfillment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkRequirementFulfillment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkRequirementFulfillment", elementToBeAdded.mapAttributeField());
addedElement = WorkRequirementFulfillmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkRequirementFulfillmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
