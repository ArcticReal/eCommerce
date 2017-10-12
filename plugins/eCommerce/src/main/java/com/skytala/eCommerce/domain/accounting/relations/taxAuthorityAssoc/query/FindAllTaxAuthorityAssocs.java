
package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.event.TaxAuthorityAssocFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.mapper.TaxAuthorityAssocMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.model.TaxAuthorityAssoc;


public class FindAllTaxAuthorityAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthorityAssoc> returnVal = new ArrayList<TaxAuthorityAssoc>();
try{
List<GenericValue> results = delegator.findAll("TaxAuthorityAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TaxAuthorityAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TaxAuthorityAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
