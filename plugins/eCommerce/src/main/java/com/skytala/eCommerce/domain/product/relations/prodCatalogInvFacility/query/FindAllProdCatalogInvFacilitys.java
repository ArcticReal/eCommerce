
package com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.event.ProdCatalogInvFacilityFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.mapper.ProdCatalogInvFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.model.ProdCatalogInvFacility;


public class FindAllProdCatalogInvFacilitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdCatalogInvFacility> returnVal = new ArrayList<ProdCatalogInvFacility>();
try{
List<GenericValue> results = delegator.findAll("ProdCatalogInvFacility", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProdCatalogInvFacilityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProdCatalogInvFacilityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
