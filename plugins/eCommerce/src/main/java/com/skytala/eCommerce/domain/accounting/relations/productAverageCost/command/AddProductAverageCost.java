package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.ProductAverageCostAdded;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.mapper.ProductAverageCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductAverageCost extends Command {

private ProductAverageCost elementToBeAdded;
public AddProductAverageCost(ProductAverageCost elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductAverageCost addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductAverageCost", elementToBeAdded.mapAttributeField());
addedElement = ProductAverageCostMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductAverageCostAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
