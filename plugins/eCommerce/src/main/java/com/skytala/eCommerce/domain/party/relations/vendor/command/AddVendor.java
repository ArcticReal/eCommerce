package com.skytala.eCommerce.domain.party.relations.vendor.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.vendor.event.VendorAdded;
import com.skytala.eCommerce.domain.party.relations.vendor.mapper.VendorMapper;
import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddVendor extends Command {

private Vendor elementToBeAdded;
public AddVendor(Vendor elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Vendor addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("Vendor", elementToBeAdded.mapAttributeField());
addedElement = VendorMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new VendorAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
