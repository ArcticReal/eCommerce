package com.skytala.eCommerce.domain.product.relations.product.command.glAccount;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.glAccount.ProductGlAccountAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.glAccount.ProductGlAccountMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.glAccount.ProductGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductGlAccount extends Command {

private ProductGlAccount elementToBeAdded;
public AddProductGlAccount(ProductGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductGlAccount", elementToBeAdded.mapAttributeField());
addedElement = ProductGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
