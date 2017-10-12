package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.event.ContentSearchConstraintAdded;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.mapper.ContentSearchConstraintMapper;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentSearchConstraint extends Command {

private ContentSearchConstraint elementToBeAdded;
public AddContentSearchConstraint(ContentSearchConstraint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentSearchConstraint addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConstraintSeqId(delegator.getNextSeqId("ContentSearchConstraint"));
GenericValue newValue = delegator.makeValue("ContentSearchConstraint", elementToBeAdded.mapAttributeField());
addedElement = ContentSearchConstraintMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentSearchConstraintAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
