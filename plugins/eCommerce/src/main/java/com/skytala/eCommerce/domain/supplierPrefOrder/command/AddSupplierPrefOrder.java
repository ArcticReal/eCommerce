package com.skytala.eCommerce.domain.supplierPrefOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.supplierPrefOrder.event.SupplierPrefOrderAdded;
import com.skytala.eCommerce.domain.supplierPrefOrder.mapper.SupplierPrefOrderMapper;
import com.skytala.eCommerce.domain.supplierPrefOrder.model.SupplierPrefOrder;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSupplierPrefOrder extends Command {

private SupplierPrefOrder elementToBeAdded;
public AddSupplierPrefOrder(SupplierPrefOrder elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SupplierPrefOrder addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSupplierPrefOrderId(delegator.getNextSeqId("SupplierPrefOrder"));
GenericValue newValue = delegator.makeValue("SupplierPrefOrder", elementToBeAdded.mapAttributeField());
addedElement = SupplierPrefOrderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SupplierPrefOrderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
