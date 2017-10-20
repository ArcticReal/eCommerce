package com.skytala.eCommerce.domain.content.relations.dataResource.command.video;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.video.VideoDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.video.VideoDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.video.VideoDataResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddVideoDataResource extends Command {

private VideoDataResource elementToBeAdded;
public AddVideoDataResource(VideoDataResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

VideoDataResource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("VideoDataResource", elementToBeAdded.mapAttributeField());
addedElement = VideoDataResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new VideoDataResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
