package com.skytala.eCommerce.domain.product.relations.productStoreGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreGroup.event.ProductStoreGroupAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreGroup.mapper.ProductStoreGroupMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreGroup.model.ProductStoreGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreGroup extends Command {

private ProductStoreGroup elementToBeAdded;
public AddProductStoreGroup(ProductStoreGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductStoreGroupId(delegator.getNextSeqId("ProductStoreGroup"));
GenericValue newValue = delegator.makeValue("ProductStoreGroup", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
