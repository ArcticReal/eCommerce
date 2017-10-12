package com.skytala.eCommerce.domain.content.relations.contentAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentAssoc.event.ContentAssocAdded;
import com.skytala.eCommerce.domain.content.relations.contentAssoc.mapper.ContentAssocMapper;
import com.skytala.eCommerce.domain.content.relations.contentAssoc.model.ContentAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentAssoc extends Command {

private ContentAssoc elementToBeAdded;
public AddContentAssoc(ContentAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContentAssoc", elementToBeAdded.mapAttributeField());
addedElement = ContentAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
