package com.skytala.eCommerce.domain.content.relations.videoDataResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.event.VideoDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.model.VideoDataResource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateVideoDataResource extends Command {

private VideoDataResource elementToBeUpdated;

public UpdateVideoDataResource(VideoDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public VideoDataResource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(VideoDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("VideoDataResource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(VideoDataResource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(VideoDataResource.class);
}
success = false;
}
Event resultingEvent = new VideoDataResourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
