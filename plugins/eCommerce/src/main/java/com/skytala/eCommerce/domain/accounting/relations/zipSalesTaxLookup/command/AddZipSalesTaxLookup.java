package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event.ZipSalesTaxLookupAdded;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.mapper.ZipSalesTaxLookupMapper;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model.ZipSalesTaxLookup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddZipSalesTaxLookup extends Command {

private ZipSalesTaxLookup elementToBeAdded;
public AddZipSalesTaxLookup(ZipSalesTaxLookup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ZipSalesTaxLookup addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ZipSalesTaxLookup", elementToBeAdded.mapAttributeField());
addedElement = ZipSalesTaxLookupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ZipSalesTaxLookupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
