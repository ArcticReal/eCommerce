package com.skytala.eCommerce.domain.accounting.relations.creditCard.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.event.CreditCardUpdated;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCreditCard extends Command {

private CreditCard elementToBeUpdated;

public UpdateCreditCard(CreditCard elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CreditCard getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CreditCard elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CreditCard", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CreditCard.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CreditCard.class);
}
success = false;
}
Event resultingEvent = new CreditCardUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
