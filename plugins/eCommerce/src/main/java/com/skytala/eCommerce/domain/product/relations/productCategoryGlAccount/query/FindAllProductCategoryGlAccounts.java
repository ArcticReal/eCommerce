
package com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.event.ProductCategoryGlAccountFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.mapper.ProductCategoryGlAccountMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.model.ProductCategoryGlAccount;


public class FindAllProductCategoryGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryGlAccount> returnVal = new ArrayList<ProductCategoryGlAccount>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
