package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.mapper.FixedAssetStdCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetStdCost extends Command {

private FixedAssetStdCost elementToBeAdded;
public AddFixedAssetStdCost(FixedAssetStdCost elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetStdCost addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetStdCost", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetStdCostMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetStdCostAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
