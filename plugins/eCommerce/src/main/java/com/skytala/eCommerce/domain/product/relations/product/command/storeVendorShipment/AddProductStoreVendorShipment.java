package com.skytala.eCommerce.domain.product.relations.product.command.storeVendorShipment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment.ProductStoreVendorShipmentAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeVendorShipment.ProductStoreVendorShipmentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorShipment.ProductStoreVendorShipment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreVendorShipment extends Command {

private ProductStoreVendorShipment elementToBeAdded;
public AddProductStoreVendorShipment(ProductStoreVendorShipment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreVendorShipment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreVendorShipment", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreVendorShipmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreVendorShipmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
