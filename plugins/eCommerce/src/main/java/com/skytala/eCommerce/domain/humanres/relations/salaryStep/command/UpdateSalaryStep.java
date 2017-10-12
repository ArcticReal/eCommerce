package com.skytala.eCommerce.domain.humanres.relations.salaryStep.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.event.SalaryStepUpdated;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalaryStep extends Command {

private SalaryStep elementToBeUpdated;

public UpdateSalaryStep(SalaryStep elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalaryStep getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalaryStep elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalaryStep", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalaryStep.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalaryStep.class);
}
success = false;
}
Event resultingEvent = new SalaryStepUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
