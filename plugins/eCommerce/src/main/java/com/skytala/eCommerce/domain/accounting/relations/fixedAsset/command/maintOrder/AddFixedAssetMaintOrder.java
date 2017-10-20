package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintOrder;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder.FixedAssetMaintOrderAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maintOrder.FixedAssetMaintOrderMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintOrder.FixedAssetMaintOrder;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetMaintOrder extends Command {

private FixedAssetMaintOrder elementToBeAdded;
public AddFixedAssetMaintOrder(FixedAssetMaintOrder elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetMaintOrder addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetMaintOrder", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetMaintOrderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetMaintOrderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
