package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.costCalc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.costCalc.WorkEffortCostCalc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortCostCalc extends Command {

private WorkEffortCostCalc elementToBeUpdated;

public UpdateWorkEffortCostCalc(WorkEffortCostCalc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortCostCalc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortCostCalc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortCostCalc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortCostCalc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortCostCalc.class);
}
success = false;
}
Event resultingEvent = new WorkEffortCostCalcUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
