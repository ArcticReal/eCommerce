package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.event.MarketingCampaignNoteAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.mapper.MarketingCampaignNoteMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.model.MarketingCampaignNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMarketingCampaignNote extends Command {

private MarketingCampaignNote elementToBeAdded;
public AddMarketingCampaignNote(MarketingCampaignNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MarketingCampaignNote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("MarketingCampaignNote", elementToBeAdded.mapAttributeField());
addedElement = MarketingCampaignNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MarketingCampaignNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
