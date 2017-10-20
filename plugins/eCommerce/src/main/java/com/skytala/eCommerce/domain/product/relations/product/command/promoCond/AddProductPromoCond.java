package com.skytala.eCommerce.domain.product.relations.product.command.promoCond;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCond.ProductPromoCondAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCond.ProductPromoCondMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCond.ProductPromoCond;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoCond extends Command {

private ProductPromoCond elementToBeAdded;
public AddProductPromoCond(ProductPromoCond elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoCond addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPromoCond", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoCondMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoCondAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
