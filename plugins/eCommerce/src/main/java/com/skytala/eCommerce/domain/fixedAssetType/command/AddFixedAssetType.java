package com.skytala.eCommerce.domain.fixedAssetType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.fixedAssetType.event.FixedAssetTypeAdded;
import com.skytala.eCommerce.domain.fixedAssetType.mapper.FixedAssetTypeMapper;
import com.skytala.eCommerce.domain.fixedAssetType.model.FixedAssetType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetType extends Command {

private FixedAssetType elementToBeAdded;
public AddFixedAssetType(FixedAssetType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFixedAssetTypeId(delegator.getNextSeqId("FixedAssetType"));
GenericValue newValue = delegator.makeValue("FixedAssetType", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
