package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.event.TechDataCalendarExcDayUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.model.TechDataCalendarExcDay;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTechDataCalendarExcDay extends Command {

private TechDataCalendarExcDay elementToBeUpdated;

public UpdateTechDataCalendarExcDay(TechDataCalendarExcDay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TechDataCalendarExcDay getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TechDataCalendarExcDay elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TechDataCalendarExcDay", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TechDataCalendarExcDay.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TechDataCalendarExcDay.class);
}
success = false;
}
Event resultingEvent = new TechDataCalendarExcDayUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
