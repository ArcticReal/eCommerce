package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type.ProductAverageCostTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.mapper.type.ProductAverageCostTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.type.ProductAverageCostType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductAverageCostType extends Command {

private ProductAverageCostType elementToBeAdded;
public AddProductAverageCostType(ProductAverageCostType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductAverageCostType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductAverageCostTypeId(delegator.getNextSeqId("ProductAverageCostType"));
GenericValue newValue = delegator.makeValue("ProductAverageCostType", elementToBeAdded.mapAttributeField());
addedElement = ProductAverageCostTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductAverageCostTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
