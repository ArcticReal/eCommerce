package com.skytala.eCommerce.domain.timesheet.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.timesheet.event.TimesheetAdded;
import com.skytala.eCommerce.domain.timesheet.mapper.TimesheetMapper;
import com.skytala.eCommerce.domain.timesheet.model.Timesheet;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTimesheet extends Command {

private Timesheet elementToBeAdded;
public AddTimesheet(Timesheet elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Timesheet addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTimesheetId(delegator.getNextSeqId("Timesheet"));
GenericValue newValue = delegator.makeValue("Timesheet", elementToBeAdded.mapAttributeField());
addedElement = TimesheetMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TimesheetAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
