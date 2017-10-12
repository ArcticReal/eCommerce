package com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.event.EmplPositionReportingStructAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.mapper.EmplPositionReportingStructMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.model.EmplPositionReportingStruct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmplPositionReportingStruct extends Command {

private EmplPositionReportingStruct elementToBeAdded;
public AddEmplPositionReportingStruct(EmplPositionReportingStruct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmplPositionReportingStruct addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("EmplPositionReportingStruct", elementToBeAdded.mapAttributeField());
addedElement = EmplPositionReportingStructMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmplPositionReportingStructAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
