
package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.typeGlAccount;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount.FixedAssetTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeGlAccount.FixedAssetTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;


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
