
package com.skytala.eCommerce.domain.product.relations.prodCatalog.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role.ProdCatalogRoleFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.role.ProdCatalogRoleMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;


public class FindAllProdCatalogRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdCatalogRole> returnVal = new ArrayList<ProdCatalogRole>();
try{
List<GenericValue> results = delegator.findAll("ProdCatalogRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProdCatalogRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProdCatalogRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
