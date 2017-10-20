package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.icalData;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.icalData.WorkEffortIcalDataUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.icalData.WorkEffortIcalData;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortIcalData extends Command {

private WorkEffortIcalData elementToBeUpdated;

public UpdateWorkEffortIcalData(WorkEffortIcalData elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortIcalData getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortIcalData elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortIcalData", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortIcalData.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortIcalData.class);
}
success = false;
}
Event resultingEvent = new WorkEffortIcalDataUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
