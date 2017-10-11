
package com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.event.PostalAddressBoundaryFound;
import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.mapper.PostalAddressBoundaryMapper;
import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.model.PostalAddressBoundary;


public class FindAllPostalAddressBoundarys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PostalAddressBoundary> returnVal = new ArrayList<PostalAddressBoundary>();
try{
List<GenericValue> results = delegator.findAll("PostalAddressBoundary", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PostalAddressBoundaryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PostalAddressBoundaryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
