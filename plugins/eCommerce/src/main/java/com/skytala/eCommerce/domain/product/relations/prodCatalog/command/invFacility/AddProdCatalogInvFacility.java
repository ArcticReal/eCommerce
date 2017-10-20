package com.skytala.eCommerce.domain.product.relations.prodCatalog.command.invFacility;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility.ProdCatalogInvFacilityAdded;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.invFacility.ProdCatalogInvFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.invFacility.ProdCatalogInvFacility;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProdCatalogInvFacility extends Command {

private ProdCatalogInvFacility elementToBeAdded;
public AddProdCatalogInvFacility(ProdCatalogInvFacility elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProdCatalogInvFacility addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProdCatalogInvFacility", elementToBeAdded.mapAttributeField());
addedElement = ProdCatalogInvFacilityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProdCatalogInvFacilityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
