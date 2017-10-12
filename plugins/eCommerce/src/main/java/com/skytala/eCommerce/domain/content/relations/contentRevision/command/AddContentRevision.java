package com.skytala.eCommerce.domain.content.relations.contentRevision.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentRevision.event.ContentRevisionAdded;
import com.skytala.eCommerce.domain.content.relations.contentRevision.mapper.ContentRevisionMapper;
import com.skytala.eCommerce.domain.content.relations.contentRevision.model.ContentRevision;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentRevision extends Command {

private ContentRevision elementToBeAdded;
public AddContentRevision(ContentRevision elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentRevision addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentRevisionSeqId(delegator.getNextSeqId("ContentRevision"));
GenericValue newValue = delegator.makeValue("ContentRevision", elementToBeAdded.mapAttributeField());
addedElement = ContentRevisionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentRevisionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
