package com.skytala.eCommerce.domain.product.relations.vendorProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.event.VendorProductAdded;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.mapper.VendorProductMapper;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.model.VendorProduct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddVendorProduct extends Command {

private VendorProduct elementToBeAdded;
public AddVendorProduct(VendorProduct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

VendorProduct addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("VendorProduct", elementToBeAdded.mapAttributeField());
addedElement = VendorProductMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new VendorProductAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
