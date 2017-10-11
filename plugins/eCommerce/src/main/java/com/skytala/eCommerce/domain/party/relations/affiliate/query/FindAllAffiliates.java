
package com.skytala.eCommerce.domain.party.relations.affiliate.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.affiliate.event.AffiliateFound;
import com.skytala.eCommerce.domain.party.relations.affiliate.mapper.AffiliateMapper;
import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;


public class FindAllAffiliates extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Affiliate> returnVal = new ArrayList<Affiliate>();
try{
List<GenericValue> results = delegator.findAll("Affiliate", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AffiliateMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AffiliateFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
