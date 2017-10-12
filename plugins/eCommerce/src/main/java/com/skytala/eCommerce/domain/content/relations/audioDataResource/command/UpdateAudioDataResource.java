package com.skytala.eCommerce.domain.content.relations.audioDataResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.audioDataResource.event.AudioDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.audioDataResource.model.AudioDataResource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAudioDataResource extends Command {

private AudioDataResource elementToBeUpdated;

public UpdateAudioDataResource(AudioDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AudioDataResource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AudioDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AudioDataResource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AudioDataResource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AudioDataResource.class);
}
success = false;
}
Event resultingEvent = new AudioDataResourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
