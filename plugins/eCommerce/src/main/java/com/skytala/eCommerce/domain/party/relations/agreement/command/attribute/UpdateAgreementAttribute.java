package com.skytala.eCommerce.domain.party.relations.agreement.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.attribute.AgreementAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementAttribute extends Command {

private AgreementAttribute elementToBeUpdated;

public UpdateAgreementAttribute(AgreementAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementAttribute.class);
}
success = false;
}
Event resultingEvent = new AgreementAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
