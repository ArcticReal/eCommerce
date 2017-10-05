package com.skytala.eCommerce.domain.timeEntry.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.timeEntry.event.TimeEntryAdded;
import com.skytala.eCommerce.domain.timeEntry.mapper.TimeEntryMapper;
import com.skytala.eCommerce.domain.timeEntry.model.TimeEntry;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTimeEntry extends Command {

private TimeEntry elementToBeAdded;
public AddTimeEntry(TimeEntry elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TimeEntry addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTimeEntryId(delegator.getNextSeqId("TimeEntry"));
GenericValue newValue = delegator.makeValue("TimeEntry", elementToBeAdded.mapAttributeField());
addedElement = TimeEntryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TimeEntryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
