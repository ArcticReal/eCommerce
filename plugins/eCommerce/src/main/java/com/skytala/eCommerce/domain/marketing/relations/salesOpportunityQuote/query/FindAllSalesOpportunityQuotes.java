
package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.event.SalesOpportunityQuoteFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.mapper.SalesOpportunityQuoteMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.model.SalesOpportunityQuote;


public class FindAllSalesOpportunityQuotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunityQuote> returnVal = new ArrayList<SalesOpportunityQuote>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunityQuote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityQuoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityQuoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
