package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.meter;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter.FixedAssetMeterAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.meter.FixedAssetMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.meter.FixedAssetMeter;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetMeter extends Command {

private FixedAssetMeter elementToBeAdded;
public AddFixedAssetMeter(FixedAssetMeter elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetMeter addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetMeter", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetMeterMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetMeterAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
