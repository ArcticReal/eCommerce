package com.skytala.eCommerce.domain.content.relations.electronicText.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.electronicText.event.ElectronicTextUpdated;
import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateElectronicText extends Command {

private ElectronicText elementToBeUpdated;

public UpdateElectronicText(ElectronicText elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ElectronicText getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ElectronicText elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ElectronicText", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ElectronicText.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ElectronicText.class);
}
success = false;
}
Event resultingEvent = new ElectronicTextUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
