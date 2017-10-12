package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.event.FixedAssetProductTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.mapper.FixedAssetProductTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.model.FixedAssetProductType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetProductType extends Command {

private FixedAssetProductType elementToBeAdded;
public AddFixedAssetProductType(FixedAssetProductType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetProductType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFixedAssetProductTypeId(delegator.getNextSeqId("FixedAssetProductType"));
GenericValue newValue = delegator.makeValue("FixedAssetProductType", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetProductTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetProductTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
