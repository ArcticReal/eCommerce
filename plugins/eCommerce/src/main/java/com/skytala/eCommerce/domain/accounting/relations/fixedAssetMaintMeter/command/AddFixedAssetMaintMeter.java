package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.event.FixedAssetMaintMeterAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.mapper.FixedAssetMaintMeterMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.model.FixedAssetMaintMeter;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetMaintMeter extends Command {

private FixedAssetMaintMeter elementToBeAdded;
public AddFixedAssetMaintMeter(FixedAssetMaintMeter elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetMaintMeter addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMaintHistSeqId(delegator.getNextSeqId("FixedAssetMaintMeter"));
GenericValue newValue = delegator.makeValue("FixedAssetMaintMeter", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetMaintMeterMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetMaintMeterAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
