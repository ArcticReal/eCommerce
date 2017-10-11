package com.skytala.eCommerce.domain.product.relations.costComponent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.CostComponentAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.CostComponentMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.CostComponent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCostComponent extends Command {

private CostComponent elementToBeAdded;
public AddCostComponent(CostComponent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CostComponent addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCostComponentId(delegator.getNextSeqId("CostComponent"));
GenericValue newValue = delegator.makeValue("CostComponent", elementToBeAdded.mapAttributeField());
addedElement = CostComponentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CostComponentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
