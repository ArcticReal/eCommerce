package com.skytala.eCommerce.domain.agreementItemType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.agreementItemType.event.AgreementItemTypeUpdated;
import com.skytala.eCommerce.domain.agreementItemType.model.AgreementItemType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementItemType extends Command {

private AgreementItemType elementToBeUpdated;

public UpdateAgreementItemType(AgreementItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementItemType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementItemType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementItemType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementItemType.class);
}
success = false;
}
Event resultingEvent = new AgreementItemTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
