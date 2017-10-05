package com.skytala.eCommerce.domain.productStoreGroupType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productStoreGroupType.event.ProductStoreGroupTypeAdded;
import com.skytala.eCommerce.domain.productStoreGroupType.mapper.ProductStoreGroupTypeMapper;
import com.skytala.eCommerce.domain.productStoreGroupType.model.ProductStoreGroupType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreGroupType extends Command {

private ProductStoreGroupType elementToBeAdded;
public AddProductStoreGroupType(ProductStoreGroupType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreGroupType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductStoreGroupTypeId(delegator.getNextSeqId("ProductStoreGroupType"));
GenericValue newValue = delegator.makeValue("ProductStoreGroupType", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreGroupTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreGroupTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
