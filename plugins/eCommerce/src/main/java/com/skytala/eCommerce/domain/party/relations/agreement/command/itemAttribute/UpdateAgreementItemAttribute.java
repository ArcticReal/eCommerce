package com.skytala.eCommerce.domain.party.relations.agreement.command.itemAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute.AgreementItemAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemAttribute.AgreementItemAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementItemAttribute extends Command {

private AgreementItemAttribute elementToBeUpdated;

public UpdateAgreementItemAttribute(AgreementItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementItemAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementItemAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementItemAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementItemAttribute.class);
}
success = false;
}
Event resultingEvent = new AgreementItemAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
