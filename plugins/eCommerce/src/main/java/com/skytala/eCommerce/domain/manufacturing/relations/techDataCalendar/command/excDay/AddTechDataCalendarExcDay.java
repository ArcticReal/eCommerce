package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excDay;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay.TechDataCalendarExcDayAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.excDay.TechDataCalendarExcDayMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excDay.TechDataCalendarExcDay;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTechDataCalendarExcDay extends Command {

private TechDataCalendarExcDay elementToBeAdded;
public AddTechDataCalendarExcDay(TechDataCalendarExcDay elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TechDataCalendarExcDay addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TechDataCalendarExcDay", elementToBeAdded.mapAttributeField());
addedElement = TechDataCalendarExcDayMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TechDataCalendarExcDayAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
