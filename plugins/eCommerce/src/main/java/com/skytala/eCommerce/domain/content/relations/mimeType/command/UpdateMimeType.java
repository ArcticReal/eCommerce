package com.skytala.eCommerce.domain.content.relations.mimeType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.MimeTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.mimeType.model.MimeType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMimeType extends Command {

private MimeType elementToBeUpdated;

public UpdateMimeType(MimeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MimeType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MimeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MimeType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MimeType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MimeType.class);
}
success = false;
}
Event resultingEvent = new MimeTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
