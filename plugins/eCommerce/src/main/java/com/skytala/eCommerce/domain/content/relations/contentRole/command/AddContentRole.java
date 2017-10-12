package com.skytala.eCommerce.domain.content.relations.contentRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentRole.event.ContentRoleAdded;
import com.skytala.eCommerce.domain.content.relations.contentRole.mapper.ContentRoleMapper;
import com.skytala.eCommerce.domain.content.relations.contentRole.model.ContentRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentRole extends Command {

private ContentRole elementToBeAdded;
public AddContentRole(ContentRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("ContentRole"));
GenericValue newValue = delegator.makeValue("ContentRole", elementToBeAdded.mapAttributeField());
addedElement = ContentRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
