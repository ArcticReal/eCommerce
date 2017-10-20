package com.skytala.eCommerce.domain.product.relations.product.command.price;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.price.ProductPriceAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.price.ProductPriceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPrice extends Command {

private ProductPrice elementToBeAdded;
public AddProductPrice(ProductPrice elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPrice addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPrice", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
