package com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRollup;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRollup.ProductStoreGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreGroupRollup extends Command {

private ProductStoreGroupRollup elementToBeAdded;
public AddProductStoreGroupRollup(ProductStoreGroupRollup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreGroupRollup addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreGroupRollup", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreGroupRollupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreGroupRollupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
