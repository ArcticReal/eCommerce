package com.skytala.eCommerce.domain.party.relations.agreement.command.term;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.term.AgreementTermUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.term.AgreementTerm;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementTerm extends Command {

private AgreementTerm elementToBeUpdated;

public UpdateAgreementTerm(AgreementTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementTerm getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementTerm", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementTerm.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementTerm.class);
}
success = false;
}
Event resultingEvent = new AgreementTermUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
