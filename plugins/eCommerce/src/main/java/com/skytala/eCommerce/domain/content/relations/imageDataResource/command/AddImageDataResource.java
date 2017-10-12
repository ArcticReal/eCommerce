package com.skytala.eCommerce.domain.content.relations.imageDataResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.imageDataResource.event.ImageDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.imageDataResource.mapper.ImageDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.imageDataResource.model.ImageDataResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddImageDataResource extends Command {

private ImageDataResource elementToBeAdded;
public AddImageDataResource(ImageDataResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ImageDataResource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ImageDataResource", elementToBeAdded.mapAttributeField());
addedElement = ImageDataResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ImageDataResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
