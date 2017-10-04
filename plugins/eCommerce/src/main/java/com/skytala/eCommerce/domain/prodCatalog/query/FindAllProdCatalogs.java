
package com.skytala.eCommerce.domain.prodCatalog.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.prodCatalog.event.ProdCatalogFound;
import com.skytala.eCommerce.domain.prodCatalog.mapper.ProdCatalogMapper;
import com.skytala.eCommerce.domain.prodCatalog.model.ProdCatalog;


public class FindAllProdCatalogs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdCatalog> returnVal = new ArrayList<ProdCatalog>();
try{
List<GenericValue> results = delegator.findAll("ProdCatalog", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProdCatalogMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProdCatalogFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
