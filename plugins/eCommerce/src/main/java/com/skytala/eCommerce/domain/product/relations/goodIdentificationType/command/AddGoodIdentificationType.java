package com.skytala.eCommerce.domain.product.relations.goodIdentificationType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.goodIdentificationType.event.GoodIdentificationTypeAdded;
import com.skytala.eCommerce.domain.product.relations.goodIdentificationType.mapper.GoodIdentificationTypeMapper;
import com.skytala.eCommerce.domain.product.relations.goodIdentificationType.model.GoodIdentificationType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGoodIdentificationType extends Command {

private GoodIdentificationType elementToBeAdded;
public AddGoodIdentificationType(GoodIdentificationType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GoodIdentificationType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGoodIdentificationTypeId(delegator.getNextSeqId("GoodIdentificationType"));
GenericValue newValue = delegator.makeValue("GoodIdentificationType", elementToBeAdded.mapAttributeField());
addedElement = GoodIdentificationTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GoodIdentificationTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
