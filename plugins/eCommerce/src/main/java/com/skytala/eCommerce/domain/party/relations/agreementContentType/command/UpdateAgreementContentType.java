package com.skytala.eCommerce.domain.party.relations.agreementContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.event.AgreementContentTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.model.AgreementContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementContentType extends Command {

private AgreementContentType elementToBeUpdated;

public UpdateAgreementContentType(AgreementContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementContentType.class);
}
success = false;
}
Event resultingEvent = new AgreementContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}