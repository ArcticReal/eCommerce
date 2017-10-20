package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.week;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week.TechDataCalendarWeekUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week.TechDataCalendarWeek;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTechDataCalendarWeek extends Command {

private TechDataCalendarWeek elementToBeUpdated;

public UpdateTechDataCalendarWeek(TechDataCalendarWeek elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TechDataCalendarWeek getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TechDataCalendarWeek elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TechDataCalendarWeek", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TechDataCalendarWeek.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TechDataCalendarWeek.class);
}
success = false;
}
Event resultingEvent = new TechDataCalendarWeekUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
