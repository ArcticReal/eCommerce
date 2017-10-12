package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event.ProductManufacturingRuleAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.mapper.ProductManufacturingRuleMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model.ProductManufacturingRule;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductManufacturingRule extends Command {

private ProductManufacturingRule elementToBeAdded;
public AddProductManufacturingRule(ProductManufacturingRule elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductManufacturingRule addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRuleId(delegator.getNextSeqId("ProductManufacturingRule"));
GenericValue newValue = delegator.makeValue("ProductManufacturingRule", elementToBeAdded.mapAttributeField());
addedElement = ProductManufacturingRuleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductManufacturingRuleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
