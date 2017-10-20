package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excWeek;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek.TechDataCalendarExcWeekUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excWeek.TechDataCalendarExcWeek;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTechDataCalendarExcWeek extends Command {

private TechDataCalendarExcWeek elementToBeUpdated;

public UpdateTechDataCalendarExcWeek(TechDataCalendarExcWeek elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TechDataCalendarExcWeek getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TechDataCalendarExcWeek elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TechDataCalendarExcWeek", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TechDataCalendarExcWeek.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TechDataCalendarExcWeek.class);
}
success = false;
}
Event resultingEvent = new TechDataCalendarExcWeekUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
