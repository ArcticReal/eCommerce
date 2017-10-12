package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.TechDataCalendarAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.TechDataCalendarMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTechDataCalendar extends Command {

private TechDataCalendar elementToBeAdded;
public AddTechDataCalendar(TechDataCalendar elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TechDataCalendar addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCalendarId(delegator.getNextSeqId("TechDataCalendar"));
GenericValue newValue = delegator.makeValue("TechDataCalendar", elementToBeAdded.mapAttributeField());
addedElement = TechDataCalendarMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TechDataCalendarAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
