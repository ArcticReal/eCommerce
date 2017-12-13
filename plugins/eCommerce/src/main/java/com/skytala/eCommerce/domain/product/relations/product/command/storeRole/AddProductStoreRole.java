package com.skytala.eCommerce.domain.product.relations.product.command.storeRole;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeRole.ProductStoreRoleAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeRole.ProductStoreRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeRole.ProductStoreRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreRole extends Command {

private ProductStoreRole elementToBeAdded;
public AddProductStoreRole(ProductStoreRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("ProductStoreRole"));
GenericValue newValue = delegator.makeValue("ProductStoreRole", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
