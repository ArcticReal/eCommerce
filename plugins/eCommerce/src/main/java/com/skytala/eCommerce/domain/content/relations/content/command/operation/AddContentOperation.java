package com.skytala.eCommerce.domain.content.relations.content.command.operation;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.operation.ContentOperationAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.operation.ContentOperationMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.operation.ContentOperation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentOperation extends Command {

private ContentOperation elementToBeAdded;
public AddContentOperation(ContentOperation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentOperation addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentOperationId(delegator.getNextSeqId("ContentOperation"));
GenericValue newValue = delegator.makeValue("ContentOperation", elementToBeAdded.mapAttributeField());
addedElement = ContentOperationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentOperationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
