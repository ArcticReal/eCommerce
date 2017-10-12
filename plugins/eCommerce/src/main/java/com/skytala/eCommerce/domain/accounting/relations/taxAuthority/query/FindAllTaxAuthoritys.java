
package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.TaxAuthorityMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;


public class FindAllTaxAuthoritys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthority> returnVal = new ArrayList<TaxAuthority>();
try{
List<GenericValue> results = delegator.findAll("TaxAuthority", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TaxAuthorityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TaxAuthorityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
