package com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type.EmplLeaveTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.type.EmplLeaveTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.type.EmplLeaveType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplLeaveType extends Command {

private EmplLeaveType elementToBeAdded;
public AddEmplLeaveType(EmplLeaveType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplLeaveType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setLeaveTypeId(delegator.getNextSeqId("EmplLeaveType"));
GenericValue newValue = delegator.makeValue("EmplLeaveType", elementToBeAdded.mapAttributeField());
addedElement = EmplLeaveTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplLeaveTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
