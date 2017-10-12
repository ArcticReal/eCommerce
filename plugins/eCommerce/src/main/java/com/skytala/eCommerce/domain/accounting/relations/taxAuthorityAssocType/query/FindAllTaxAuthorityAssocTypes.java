
package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssocType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssocType.event.TaxAuthorityAssocTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssocType.mapper.TaxAuthorityAssocTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssocType.model.TaxAuthorityAssocType;


public class FindAllTaxAuthorityAssocTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthorityAssocType> returnVal = new ArrayList<TaxAuthorityAssocType>();
try{
List<GenericValue> results = delegator.findAll("TaxAuthorityAssocType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TaxAuthorityAssocTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TaxAuthorityAssocTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
