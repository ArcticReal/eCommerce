package com.skytala.eCommerce.domain.content.relations.contentMetaData.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.event.ContentMetaDataUpdated;
import com.skytala.eCommerce.domain.content.relations.contentMetaData.model.ContentMetaData;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentMetaData extends Command {

private ContentMetaData elementToBeUpdated;

public UpdateContentMetaData(ContentMetaData elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentMetaData getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentMetaData elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentMetaData", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentMetaData.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentMetaData.class);
}
success = false;
}
Event resultingEvent = new ContentMetaDataUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
