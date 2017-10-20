package com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.reportingStruct;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct.EmplPositionReportingStructAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.reportingStruct.EmplPositionReportingStructMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.reportingStruct.EmplPositionReportingStruct;
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
