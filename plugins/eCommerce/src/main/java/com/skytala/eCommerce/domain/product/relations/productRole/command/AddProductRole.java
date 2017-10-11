package com.skytala.eCommerce.domain.product.relations.productRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productRole.event.ProductRoleAdded;
import com.skytala.eCommerce.domain.product.relations.productRole.mapper.ProductRoleMapper;
import com.skytala.eCommerce.domain.product.relations.productRole.model.ProductRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductRole extends Command {

private ProductRole elementToBeAdded;
public AddProductRole(ProductRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductRole", elementToBeAdded.mapAttributeField());
addedElement = ProductRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
