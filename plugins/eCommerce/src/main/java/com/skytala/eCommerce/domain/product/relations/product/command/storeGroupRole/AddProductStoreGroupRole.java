package com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRole;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole.ProductStoreGroupRoleAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRole.ProductStoreGroupRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRole.ProductStoreGroupRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreGroupRole extends Command {

private ProductStoreGroupRole elementToBeAdded;
public AddProductStoreGroupRole(ProductStoreGroupRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreGroupRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("ProductStoreGroupRole"));
GenericValue newValue = delegator.makeValue("ProductStoreGroupRole", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreGroupRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreGroupRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
