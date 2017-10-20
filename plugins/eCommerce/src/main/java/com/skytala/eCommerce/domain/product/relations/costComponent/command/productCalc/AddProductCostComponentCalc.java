package com.skytala.eCommerce.domain.product.relations.costComponent.command.productCalc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc.ProductCostComponentCalcAdded;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.productCalc.ProductCostComponentCalcMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.productCalc.ProductCostComponentCalc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCostComponentCalc extends Command {

private ProductCostComponentCalc elementToBeAdded;
public AddProductCostComponentCalc(ProductCostComponentCalc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCostComponentCalc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCostComponentCalc", elementToBeAdded.mapAttributeField());
addedElement = ProductCostComponentCalcMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCostComponentCalcAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
