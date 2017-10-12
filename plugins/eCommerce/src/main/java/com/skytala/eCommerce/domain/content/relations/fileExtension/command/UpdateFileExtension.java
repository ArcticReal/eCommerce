package com.skytala.eCommerce.domain.content.relations.fileExtension.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.fileExtension.event.FileExtensionUpdated;
import com.skytala.eCommerce.domain.content.relations.fileExtension.model.FileExtension;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFileExtension extends Command {

private FileExtension elementToBeUpdated;

public UpdateFileExtension(FileExtension elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FileExtension getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FileExtension elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FileExtension", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FileExtension.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FileExtension.class);
}
success = false;
}
Event resultingEvent = new FileExtensionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
