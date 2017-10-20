package com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.reasonType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType.EmplLeaveReasonTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.reasonType.EmplLeaveReasonTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.reasonType.EmplLeaveReasonType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplLeaveReasonType extends Command {

private EmplLeaveReasonType elementToBeAdded;
public AddEmplLeaveReasonType(EmplLeaveReasonType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplLeaveReasonType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setEmplLeaveReasonTypeId(delegator.getNextSeqId("EmplLeaveReasonType"));
GenericValue newValue = delegator.makeValue("EmplLeaveReasonType", elementToBeAdded.mapAttributeField());
addedElement = EmplLeaveReasonTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplLeaveReasonTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
