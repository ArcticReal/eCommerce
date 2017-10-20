package com.skytala.eCommerce.domain.content.relations.dataResource.command.audio;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.audio.AudioDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.audio.AudioDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.audio.AudioDataResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAudioDataResource extends Command {

private AudioDataResource elementToBeAdded;
public AddAudioDataResource(AudioDataResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AudioDataResource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("AudioDataResource", elementToBeAdded.mapAttributeField());
addedElement = AudioDataResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AudioDataResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
