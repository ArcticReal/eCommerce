package com.skytala.eCommerce.domain.product.relations.costComponent.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute.CostComponentAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.attribute.CostComponentAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute.CostComponentAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCostComponentAttribute extends Command {

private CostComponentAttribute elementToBeAdded;
public AddCostComponentAttribute(CostComponentAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CostComponentAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CostComponentAttribute", elementToBeAdded.mapAttributeField());
addedElement = CostComponentAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CostComponentAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
