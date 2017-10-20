package com.skytala.eCommerce.domain.content.relations.document.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.document.event.attribute.DocumentAttributeUpdated;
import com.skytala.eCommerce.domain.content.relations.document.model.attribute.DocumentAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDocumentAttribute extends Command {

private DocumentAttribute elementToBeUpdated;

public UpdateDocumentAttribute(DocumentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DocumentAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DocumentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DocumentAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DocumentAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DocumentAttribute.class);
}
success = false;
}
Event resultingEvent = new DocumentAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
