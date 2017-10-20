package com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.reportingStruct;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct.EmplPositionReportingStructUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.reportingStruct.EmplPositionReportingStruct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplPositionReportingStruct extends Command {

private EmplPositionReportingStruct elementToBeUpdated;

public UpdateEmplPositionReportingStruct(EmplPositionReportingStruct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplPositionReportingStruct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplPositionReportingStruct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplPositionReportingStruct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplPositionReportingStruct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplPositionReportingStruct.class);
}
success = false;
}
Event resultingEvent = new EmplPositionReportingStructUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
