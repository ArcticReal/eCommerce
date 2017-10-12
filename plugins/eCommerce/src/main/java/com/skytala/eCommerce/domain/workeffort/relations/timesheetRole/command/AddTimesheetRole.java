package com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.event.TimesheetRoleAdded;
import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.mapper.TimesheetRoleMapper;
import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.model.TimesheetRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTimesheetRole extends Command {

private TimesheetRole elementToBeAdded;
public AddTimesheetRole(TimesheetRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TimesheetRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TimesheetRole", elementToBeAdded.mapAttributeField());
addedElement = TimesheetRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TimesheetRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
