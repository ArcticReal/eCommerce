
package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query.note;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note.MarketingCampaignNoteFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.note.MarketingCampaignNoteMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.note.MarketingCampaignNote;


public class FindAllMarketingCampaignNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MarketingCampaignNote> returnVal = new ArrayList<MarketingCampaignNote>();
try{
List<GenericValue> results = delegator.findAll("MarketingCampaignNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MarketingCampaignNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MarketingCampaignNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
