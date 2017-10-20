package com.skytala.eCommerce.domain.product.relations.product.command.config;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigProductAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.config.ProductConfigProductMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfigProduct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductConfigProduct extends Command {

private ProductConfigProduct elementToBeAdded;
public AddProductConfigProduct(ProductConfigProduct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductConfigProduct addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConfigOptionId(delegator.getNextSeqId("ProductConfigProduct"));
GenericValue newValue = delegator.makeValue("ProductConfigProduct", elementToBeAdded.mapAttributeField());
addedElement = ProductConfigProductMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductConfigProductAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
