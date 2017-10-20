package com.skytala.eCommerce.domain.party.relations.agreement.command.content;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.agreement.event.content.AgreementContentUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.model.content.AgreementContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAgreementContent extends Command {

private AgreementContent elementToBeUpdated;

public UpdateAgreementContent(AgreementContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AgreementContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AgreementContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AgreementContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AgreementContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AgreementContent.class);
}
success = false;
}
Event resultingEvent = new AgreementContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
