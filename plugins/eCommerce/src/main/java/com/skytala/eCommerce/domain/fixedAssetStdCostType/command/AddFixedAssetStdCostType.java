package com.skytala.eCommerce.domain.fixedAssetStdCostType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.event.FixedAssetStdCostTypeAdded;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.mapper.FixedAssetStdCostTypeMapper;
import com.skytala.eCommerce.domain.fixedAssetStdCostType.model.FixedAssetStdCostType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetStdCostType extends Command {

private FixedAssetStdCostType elementToBeAdded;
public AddFixedAssetStdCostType(FixedAssetStdCostType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetStdCostType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFixedAssetStdCostTypeId(delegator.getNextSeqId("FixedAssetStdCostType"));
GenericValue newValue = delegator.makeValue("FixedAssetStdCostType", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetStdCostTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetStdCostTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
