
package com.skytala.eCommerce.domain.product.relations.product.query.storeCatalog;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog.ProductStoreCatalogFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeCatalog.ProductStoreCatalogMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;


public class FindAllProductStoreCatalogs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreCatalog> returnVal = new ArrayList<ProductStoreCatalog>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreCatalog", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreCatalogMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreCatalogFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
