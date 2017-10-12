package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.event.FixedAssetMaintAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.mapper.FixedAssetMaintMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.model.FixedAssetMaint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetMaint extends Command {

private FixedAssetMaint elementToBeAdded;
public AddFixedAssetMaint(FixedAssetMaint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetMaint addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMaintHistSeqId(delegator.getNextSeqId("FixedAssetMaint"));
GenericValue newValue = delegator.makeValue("FixedAssetMaint", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetMaintMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetMaintAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
