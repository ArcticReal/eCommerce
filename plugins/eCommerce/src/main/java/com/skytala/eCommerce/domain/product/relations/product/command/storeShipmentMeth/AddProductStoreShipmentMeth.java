package com.skytala.eCommerce.domain.product.relations.product.command.storeShipmentMeth;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeShipmentMeth.ProductStoreShipmentMethMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreShipmentMeth extends Command {

private ProductStoreShipmentMeth elementToBeAdded;
public AddProductStoreShipmentMeth(ProductStoreShipmentMeth elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreShipmentMeth addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductStoreShipMethId(delegator.getNextSeqId("ProductStoreShipmentMeth"));
GenericValue newValue = delegator.makeValue("ProductStoreShipmentMeth", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreShipmentMethMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreShipmentMethAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
