package com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.responsibility;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.responsibility.EmplPositionResponsibilityMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.responsibility.EmplPositionResponsibility;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPositionResponsibility extends Command {

private EmplPositionResponsibility elementToBeAdded;
public AddEmplPositionResponsibility(EmplPositionResponsibility elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPositionResponsibility addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("EmplPositionResponsibility", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionResponsibilityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionResponsibilityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
