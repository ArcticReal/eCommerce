package com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.event.ProductStoreVendorPaymentAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.mapper.ProductStoreVendorPaymentMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.model.ProductStoreVendorPayment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreVendorPayment extends Command {

private ProductStoreVendorPayment elementToBeAdded;
public AddProductStoreVendorPayment(ProductStoreVendorPayment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreVendorPayment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreVendorPayment", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreVendorPaymentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreVendorPaymentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
