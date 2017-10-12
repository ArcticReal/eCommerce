package com.skytala.eCommerce.domain.accounting.relations.deductionType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.deductionType.event.DeductionTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.deductionType.model.DeductionType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDeductionType extends Command {

private DeductionType elementToBeUpdated;

public UpdateDeductionType(DeductionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DeductionType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DeductionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DeductionType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DeductionType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DeductionType.class);
}
success = false;
}
Event resultingEvent = new DeductionTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
