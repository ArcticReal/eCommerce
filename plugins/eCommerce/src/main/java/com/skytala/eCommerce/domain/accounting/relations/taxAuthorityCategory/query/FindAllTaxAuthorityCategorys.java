
package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.event.TaxAuthorityCategoryFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.mapper.TaxAuthorityCategoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.model.TaxAuthorityCategory;


public class FindAllTaxAuthorityCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthorityCategory> returnVal = new ArrayList<TaxAuthorityCategory>();
try{
List<GenericValue> results = delegator.findAll("TaxAuthorityCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TaxAuthorityCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TaxAuthorityCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
