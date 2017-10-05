package com.skytala.eCommerce.domain.documentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.documentType.event.DocumentTypeUpdated;
import com.skytala.eCommerce.domain.documentType.model.DocumentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDocumentType extends Command {

private DocumentType elementToBeUpdated;

public UpdateDocumentType(DocumentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DocumentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DocumentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DocumentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DocumentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DocumentType.class);
}
success = false;
}
Event resultingEvent = new DocumentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
