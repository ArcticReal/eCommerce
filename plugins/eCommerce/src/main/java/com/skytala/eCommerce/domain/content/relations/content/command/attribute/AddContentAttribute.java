package com.skytala.eCommerce.domain.content.relations.content.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.attribute.ContentAttributeAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.attribute.ContentAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.attribute.ContentAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentAttribute extends Command {

private ContentAttribute elementToBeAdded;
public AddContentAttribute(ContentAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContentAttribute", elementToBeAdded.mapAttributeField());
addedElement = ContentAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
