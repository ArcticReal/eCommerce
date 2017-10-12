package com.skytala.eCommerce.domain.content.relations.metaDataPredicate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event.MetaDataPredicateAdded;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.mapper.MetaDataPredicateMapper;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.model.MetaDataPredicate;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMetaDataPredicate extends Command {

private MetaDataPredicate elementToBeAdded;
public AddMetaDataPredicate(MetaDataPredicate elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MetaDataPredicate addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMetaDataPredicateId(delegator.getNextSeqId("MetaDataPredicate"));
GenericValue newValue = delegator.makeValue("MetaDataPredicate", elementToBeAdded.mapAttributeField());
addedElement = MetaDataPredicateMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MetaDataPredicateAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
