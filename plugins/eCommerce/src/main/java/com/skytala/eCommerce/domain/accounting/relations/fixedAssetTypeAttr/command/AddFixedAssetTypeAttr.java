package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.event.FixedAssetTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.mapper.FixedAssetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.model.FixedAssetTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetTypeAttr extends Command {

private FixedAssetTypeAttr elementToBeAdded;
public AddFixedAssetTypeAttr(FixedAssetTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
