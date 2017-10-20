package com.skytala.eCommerce.domain.party.relations.agreement.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.type.AgreementType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementType extends Command {

private AgreementType elementToBeUpdated;

public UpdateAgreementType(AgreementType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementType.class);
}
success = false;
}
Event resultingEvent = new AgreementTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
