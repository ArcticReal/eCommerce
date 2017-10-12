package com.skytala.eCommerce.domain.humanres.relations.emplLeave.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.EmplLeaveAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.EmplLeaveMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplLeave extends Command {

private EmplLeave elementToBeAdded;
public AddEmplLeave(EmplLeave elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplLeave addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("EmplLeave", elementToBeAdded.mapAttributeField());
addedElement = EmplLeaveMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplLeaveAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
