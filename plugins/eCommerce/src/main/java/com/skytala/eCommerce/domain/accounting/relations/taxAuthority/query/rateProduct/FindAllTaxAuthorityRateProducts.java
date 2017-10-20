
package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query.rateProduct;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct.TaxAuthorityRateProductFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.rateProduct.TaxAuthorityRateProductMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct.TaxAuthorityRateProduct;


public class FindAllTaxAuthorityRateProducts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthorityRateProduct> returnVal = new ArrayList<TaxAuthorityRateProduct>();
try{
List<GenericValue> results = delegator.findAll("TaxAuthorityRateProduct", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TaxAuthorityRateProductMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TaxAuthorityRateProductFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
