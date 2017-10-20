package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.geoPoint;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.geoPoint.FixedAssetGeoPointAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.geoPoint.FixedAssetGeoPointMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.geoPoint.FixedAssetGeoPoint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetGeoPoint extends Command {

private FixedAssetGeoPoint elementToBeAdded;
public AddFixedAssetGeoPoint(FixedAssetGeoPoint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetGeoPoint addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetGeoPoint", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetGeoPointMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetGeoPointAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
