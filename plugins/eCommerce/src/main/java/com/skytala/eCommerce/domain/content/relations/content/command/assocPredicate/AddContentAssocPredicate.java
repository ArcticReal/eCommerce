package com.skytala.eCommerce.domain.content.relations.content.command.assocPredicate;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate.ContentAssocPredicateAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assocPredicate.ContentAssocPredicateMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.assocPredicate.ContentAssocPredicate;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentAssocPredicate extends Command {

private ContentAssocPredicate elementToBeAdded;
public AddContentAssocPredicate(ContentAssocPredicate elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentAssocPredicate addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentAssocPredicateId(delegator.getNextSeqId("ContentAssocPredicate"));
GenericValue newValue = delegator.makeValue("ContentAssocPredicate", elementToBeAdded.mapAttributeField());
addedElement = ContentAssocPredicateMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentAssocPredicateAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
