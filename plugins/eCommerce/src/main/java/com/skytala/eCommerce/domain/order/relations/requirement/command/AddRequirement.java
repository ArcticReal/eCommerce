package com.skytala.eCommerce.domain.order.relations.requirement.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.event.RequirementAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.RequirementMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.Requirement;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRequirement extends Command {

private Requirement elementToBeAdded;
public AddRequirement(Requirement elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Requirement addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRequirementId(delegator.getNextSeqId("Requirement"));
GenericValue newValue = delegator.makeValue("Requirement", elementToBeAdded.mapAttributeField());
addedElement = RequirementMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RequirementAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
