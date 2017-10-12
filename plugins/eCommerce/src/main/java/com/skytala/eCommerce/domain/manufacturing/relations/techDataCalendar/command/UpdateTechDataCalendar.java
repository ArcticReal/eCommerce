package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.TechDataCalendarUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTechDataCalendar extends Command {

private TechDataCalendar elementToBeUpdated;

public UpdateTechDataCalendar(TechDataCalendar elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TechDataCalendar getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TechDataCalendar elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TechDataCalendar", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TechDataCalendar.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TechDataCalendar.class);
}
success = false;
}
Event resultingEvent = new TechDataCalendarUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
