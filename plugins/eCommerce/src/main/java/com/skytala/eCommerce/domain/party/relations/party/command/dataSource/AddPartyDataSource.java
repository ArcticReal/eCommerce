package com.skytala.eCommerce.domain.party.relations.party.command.dataSource;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.dataSource.PartyDataSourceAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.dataSource.PartyDataSourceMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.dataSource.PartyDataSource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyDataSource extends Command {

private PartyDataSource elementToBeAdded;
public AddPartyDataSource(PartyDataSource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyDataSource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyDataSource", elementToBeAdded.mapAttributeField());
addedElement = PartyDataSourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyDataSourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
