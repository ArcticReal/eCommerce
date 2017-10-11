package com.skytala.eCommerce.domain.party.relations.agreementItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreementItem.event.AgreementItemUpdated;
import com.skytala.eCommerce.domain.party.relations.agreementItem.model.AgreementItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementItem extends Command {

private AgreementItem elementToBeUpdated;

public UpdateAgreementItem(AgreementItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementItem.class);
}
success = false;
}
Event resultingEvent = new AgreementItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
