package com.skytala.eCommerce.domain.accounting.relations.rateAmount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.event.RateAmountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRateAmount extends Command {

private RateAmount elementToBeUpdated;

public UpdateRateAmount(RateAmount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RateAmount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RateAmount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RateAmount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RateAmount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RateAmount.class);
}
success = false;
}
Event resultingEvent = new RateAmountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
