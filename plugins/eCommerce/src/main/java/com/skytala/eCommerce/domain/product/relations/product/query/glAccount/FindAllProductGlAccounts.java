
package com.skytala.eCommerce.domain.product.relations.product.query.glAccount;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.glAccount.ProductGlAccountFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.glAccount.ProductGlAccountMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.glAccount.ProductGlAccount;


public class FindAllProductGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductGlAccount> returnVal = new ArrayList<ProductGlAccount>();
try{
List<GenericValue> results = delegator.findAll("ProductGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
