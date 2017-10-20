
package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.rateType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateType.TaxAuthorityRateTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.rateType.TaxAuthorityRateTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateType.TaxAuthorityRateType;


public class FindAllTaxAuthorityRateTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthorityRateType> returnVal = new ArrayList<TaxAuthorityRateType>();
try{
List<GenericValue> results = delegator.findAll("TaxAuthorityRateType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TaxAuthorityRateTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TaxAuthorityRateTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
