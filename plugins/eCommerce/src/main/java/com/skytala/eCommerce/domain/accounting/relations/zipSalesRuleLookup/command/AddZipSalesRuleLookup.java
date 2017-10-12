package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event.ZipSalesRuleLookupAdded;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.mapper.ZipSalesRuleLookupMapper;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddZipSalesRuleLookup extends Command {

private ZipSalesRuleLookup elementToBeAdded;
public AddZipSalesRuleLookup(ZipSalesRuleLookup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ZipSalesRuleLookup addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ZipSalesRuleLookup", elementToBeAdded.mapAttributeField());
addedElement = ZipSalesRuleLookupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ZipSalesRuleLookupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
