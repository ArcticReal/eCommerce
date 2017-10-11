package com.skytala.eCommerce.domain.product.relations.goodIdentification.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.GoodIdentificationAdded;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper.GoodIdentificationMapper;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGoodIdentification extends Command {

private GoodIdentification elementToBeAdded;
public AddGoodIdentification(GoodIdentification elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GoodIdentification addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GoodIdentification", elementToBeAdded.mapAttributeField());
addedElement = GoodIdentificationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GoodIdentificationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
