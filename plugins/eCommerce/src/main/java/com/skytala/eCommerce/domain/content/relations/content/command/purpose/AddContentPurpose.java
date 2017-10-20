package com.skytala.eCommerce.domain.content.relations.content.command.purpose;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.purpose.ContentPurposeAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purpose.ContentPurposeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentPurpose extends Command {

private ContentPurpose elementToBeAdded;
public AddContentPurpose(ContentPurpose elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentPurpose addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContentPurpose", elementToBeAdded.mapAttributeField());
addedElement = ContentPurposeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentPurposeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
