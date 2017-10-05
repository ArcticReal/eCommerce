package com.skytala.eCommerce.domain.deduction.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.deduction.event.DeductionUpdated;
import com.skytala.eCommerce.domain.deduction.model.Deduction;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDeduction extends Command {

private Deduction elementToBeUpdated;

public UpdateDeduction(Deduction elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Deduction getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Deduction elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Deduction", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Deduction.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Deduction.class);
}
success = false;
}
Event resultingEvent = new DeductionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
