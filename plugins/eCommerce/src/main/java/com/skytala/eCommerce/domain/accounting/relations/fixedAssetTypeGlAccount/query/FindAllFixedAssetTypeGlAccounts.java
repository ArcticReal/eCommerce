
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.event.FixedAssetTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.mapper.FixedAssetTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.model.FixedAssetTypeGlAccount;


public class FindAllFixedAssetTypeGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetTypeGlAccount> returnVal = new ArrayList<FixedAssetTypeGlAccount>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetTypeGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetTypeGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetTypeGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
