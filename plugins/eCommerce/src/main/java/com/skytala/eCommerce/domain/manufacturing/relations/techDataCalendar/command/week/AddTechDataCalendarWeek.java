package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.week;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week.TechDataCalendarWeekAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.week.TechDataCalendarWeekMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week.TechDataCalendarWeek;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTechDataCalendarWeek extends Command {

private TechDataCalendarWeek elementToBeAdded;
public AddTechDataCalendarWeek(TechDataCalendarWeek elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TechDataCalendarWeek addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCalendarWeekId(delegator.getNextSeqId("TechDataCalendarWeek"));
GenericValue newValue = delegator.makeValue("TechDataCalendarWeek", elementToBeAdded.mapAttributeField());
addedElement = TechDataCalendarWeekMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TechDataCalendarWeekAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
