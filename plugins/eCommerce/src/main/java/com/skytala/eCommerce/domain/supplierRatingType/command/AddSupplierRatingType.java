package com.skytala.eCommerce.domain.supplierRatingType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.supplierRatingType.event.SupplierRatingTypeAdded;
import com.skytala.eCommerce.domain.supplierRatingType.mapper.SupplierRatingTypeMapper;
import com.skytala.eCommerce.domain.supplierRatingType.model.SupplierRatingType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSupplierRatingType extends Command {

private SupplierRatingType elementToBeAdded;
public AddSupplierRatingType(SupplierRatingType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SupplierRatingType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSupplierRatingTypeId(delegator.getNextSeqId("SupplierRatingType"));
GenericValue newValue = delegator.makeValue("SupplierRatingType", elementToBeAdded.mapAttributeField());
addedElement = SupplierRatingTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SupplierRatingTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
