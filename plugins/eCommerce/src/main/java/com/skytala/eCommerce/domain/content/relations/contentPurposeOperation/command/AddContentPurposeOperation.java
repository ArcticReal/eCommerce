package com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.event.ContentPurposeOperationAdded;
import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.mapper.ContentPurposeOperationMapper;
import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.model.ContentPurposeOperation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentPurposeOperation extends Command {

private ContentPurposeOperation elementToBeAdded;
public AddContentPurposeOperation(ContentPurposeOperation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentPurposeOperation addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContentPurposeOperation", elementToBeAdded.mapAttributeField());
addedElement = ContentPurposeOperationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentPurposeOperationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
