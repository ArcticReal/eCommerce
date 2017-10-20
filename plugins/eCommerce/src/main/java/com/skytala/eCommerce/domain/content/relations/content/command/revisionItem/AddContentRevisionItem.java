package com.skytala.eCommerce.domain.content.relations.content.command.revisionItem;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.revisionItem.ContentRevisionItemAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.revisionItem.ContentRevisionItemMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.revisionItem.ContentRevisionItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentRevisionItem extends Command {

private ContentRevisionItem elementToBeAdded;
public AddContentRevisionItem(ContentRevisionItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentRevisionItem addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContentRevisionItem", elementToBeAdded.mapAttributeField());
addedElement = ContentRevisionItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentRevisionItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
