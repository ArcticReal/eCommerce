package com.skytala.eCommerce.domain.productStore.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productStore.event.ProductStoreAdded;
import com.skytala.eCommerce.domain.productStore.mapper.ProductStoreMapper;
import com.skytala.eCommerce.domain.productStore.model.ProductStore;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStore extends Command {

private ProductStore elementToBeAdded;
public AddProductStore(ProductStore elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStore addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductStoreId(delegator.getNextSeqId("ProductStore"));
GenericValue newValue = delegator.makeValue("ProductStore", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
