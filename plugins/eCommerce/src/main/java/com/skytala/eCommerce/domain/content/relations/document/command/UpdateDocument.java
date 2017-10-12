package com.skytala.eCommerce.domain.content.relations.document.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.document.event.DocumentUpdated;
import com.skytala.eCommerce.domain.content.relations.document.model.Document;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDocument extends Command {

private Document elementToBeUpdated;

public UpdateDocument(Document elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Document getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Document elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Document", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Document.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Document.class);
}
success = false;
}
Event resultingEvent = new DocumentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
