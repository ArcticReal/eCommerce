package com.skytala.eCommerce.domain.product.relations.supplierProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.event.SupplierProductAdded;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.mapper.SupplierProductMapper;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.model.SupplierProduct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSupplierProduct extends Command {

private SupplierProduct elementToBeAdded;
public AddSupplierProduct(SupplierProduct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SupplierProduct addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SupplierProduct", elementToBeAdded.mapAttributeField());
addedElement = SupplierProductMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SupplierProductAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
