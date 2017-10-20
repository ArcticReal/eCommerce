package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.ident;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident.FixedAssetIdentAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.ident.FixedAssetIdentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.ident.FixedAssetIdent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetIdent extends Command {

private FixedAssetIdent elementToBeAdded;
public AddFixedAssetIdent(FixedAssetIdent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetIdent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetIdent", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetIdentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetIdentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
