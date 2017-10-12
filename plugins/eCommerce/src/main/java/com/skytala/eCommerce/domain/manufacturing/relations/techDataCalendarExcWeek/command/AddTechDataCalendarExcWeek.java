package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event.TechDataCalendarExcWeekAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.mapper.TechDataCalendarExcWeekMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model.TechDataCalendarExcWeek;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTechDataCalendarExcWeek extends Command {

private TechDataCalendarExcWeek elementToBeAdded;
public AddTechDataCalendarExcWeek(TechDataCalendarExcWeek elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TechDataCalendarExcWeek addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TechDataCalendarExcWeek", elementToBeAdded.mapAttributeField());
addedElement = TechDataCalendarExcWeekMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TechDataCalendarExcWeekAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
