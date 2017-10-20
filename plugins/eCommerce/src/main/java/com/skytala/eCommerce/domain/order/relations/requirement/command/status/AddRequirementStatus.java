package com.skytala.eCommerce.domain.order.relations.requirement.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.event.status.RequirementStatusAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.status.RequirementStatusMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.status.RequirementStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRequirementStatus extends Command {

private RequirementStatus elementToBeAdded;
public AddRequirementStatus(RequirementStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RequirementStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("RequirementStatus", elementToBeAdded.mapAttributeField());
addedElement = RequirementStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RequirementStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
