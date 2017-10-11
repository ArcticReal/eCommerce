
package com.skytala.eCommerce.domain.party.relations.postalAddress.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.PostalAddressFound;
import com.skytala.eCommerce.domain.party.relations.postalAddress.mapper.PostalAddressMapper;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;


public class FindAllPostalAddresss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PostalAddress> returnVal = new ArrayList<PostalAddress>();
try{
List<GenericValue> results = delegator.findAll("PostalAddress", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PostalAddressMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PostalAddressFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
