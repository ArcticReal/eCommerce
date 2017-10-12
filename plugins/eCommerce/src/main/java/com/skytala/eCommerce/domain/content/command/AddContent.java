package com.skytala.eCommerce.domain.content.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.event.ContentAdded;
import com.skytala.eCommerce.domain.content.mapper.ContentMapper;
import com.skytala.eCommerce.domain.content.model.Content;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContent extends Command {

private Content elementToBeAdded;
public AddContent(Content elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Content addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentId(delegator.getNextSeqId("Content"));
GenericValue newValue = delegator.makeValue("Content", elementToBeAdded.mapAttributeField());
addedElement = ContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
