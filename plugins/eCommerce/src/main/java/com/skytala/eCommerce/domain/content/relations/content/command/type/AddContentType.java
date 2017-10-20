package com.skytala.eCommerce.domain.content.relations.content.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.type.ContentTypeAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.type.ContentTypeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.type.ContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentType extends Command {

private ContentType elementToBeAdded;
public AddContentType(ContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentTypeId(delegator.getNextSeqId("ContentType"));
GenericValue newValue = delegator.makeValue("ContentType", elementToBeAdded.mapAttributeField());
addedElement = ContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
