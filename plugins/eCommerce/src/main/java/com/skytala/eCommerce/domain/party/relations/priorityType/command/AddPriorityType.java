package com.skytala.eCommerce.domain.party.relations.priorityType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.priorityType.event.PriorityTypeAdded;
import com.skytala.eCommerce.domain.party.relations.priorityType.mapper.PriorityTypeMapper;
import com.skytala.eCommerce.domain.party.relations.priorityType.model.PriorityType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPriorityType extends Command {

private PriorityType elementToBeAdded;
public AddPriorityType(PriorityType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PriorityType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPriorityTypeId(delegator.getNextSeqId("PriorityType"));
GenericValue newValue = delegator.makeValue("PriorityType", elementToBeAdded.mapAttributeField());
addedElement = PriorityTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PriorityTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
