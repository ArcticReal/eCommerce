package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.creditCardType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType.CreditCardTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.creditCardType.CreditCardTypeGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCreditCardTypeGlAccount extends Command {

private CreditCardTypeGlAccount elementToBeUpdated;

public UpdateCreditCardTypeGlAccount(CreditCardTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CreditCardTypeGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CreditCardTypeGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CreditCardTypeGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CreditCardTypeGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CreditCardTypeGlAccount.class);
}
success = false;
}
Event resultingEvent = new CreditCardTypeGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
