
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.taxAuthority;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.taxAuthority.TaxAuthorityGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.taxAuthority.TaxAuthorityGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.taxAuthority.TaxAuthorityGlAccount;


public class FindAllTaxAuthorityGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthorityGlAccount> returnVal = new ArrayList<TaxAuthorityGlAccount>();
try{
List<GenericValue> results = delegator.findAll("TaxAuthorityGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TaxAuthorityGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TaxAuthorityGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
