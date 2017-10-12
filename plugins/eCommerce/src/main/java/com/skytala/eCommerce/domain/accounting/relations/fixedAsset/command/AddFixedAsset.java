package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.FixedAssetAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.FixedAssetMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.FixedAsset;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAsset extends Command {

private FixedAsset elementToBeAdded;
public AddFixedAsset(FixedAsset elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAsset addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFixedAssetId(delegator.getNextSeqId("FixedAsset"));
GenericValue newValue = delegator.makeValue("FixedAsset", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
