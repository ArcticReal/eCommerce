package com.skytala.eCommerce.domain.product.relations.product.command.supplierFeature;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.supplierFeature.SupplierProductFeatureAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.supplierFeature.SupplierProductFeatureMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.supplierFeature.SupplierProductFeature;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSupplierProductFeature extends Command {

private SupplierProductFeature elementToBeAdded;
public AddSupplierProductFeature(SupplierProductFeature elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SupplierProductFeature addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SupplierProductFeature", elementToBeAdded.mapAttributeField());
addedElement = SupplierProductFeatureMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SupplierProductFeatureAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
