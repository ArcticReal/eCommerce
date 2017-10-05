package com.skytala.eCommerce.domain.contentApproval.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contentApproval.event.ContentApprovalAdded;
import com.skytala.eCommerce.domain.contentApproval.mapper.ContentApprovalMapper;
import com.skytala.eCommerce.domain.contentApproval.model.ContentApproval;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentApproval extends Command {

private ContentApproval elementToBeAdded;
public AddContentApproval(ContentApproval elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentApproval addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentApprovalId(delegator.getNextSeqId("ContentApproval"));
GenericValue newValue = delegator.makeValue("ContentApproval", elementToBeAdded.mapAttributeField());
addedElement = ContentApprovalMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentApprovalAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
