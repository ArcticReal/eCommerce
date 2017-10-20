package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.identType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.identType.FixedAssetIdentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.identType.FixedAssetIdentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.identType.FixedAssetIdentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetIdentType extends Command {

private FixedAssetIdentType elementToBeAdded;
public AddFixedAssetIdentType(FixedAssetIdentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetIdentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFixedAssetIdentTypeId(delegator.getNextSeqId("FixedAssetIdentType"));
GenericValue newValue = delegator.makeValue("FixedAssetIdentType", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetIdentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetIdentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
