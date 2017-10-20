
package com.skytala.eCommerce.domain.product.relations.prodCatalog.query.category;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category.ProdCatalogCategoryFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.category.ProdCatalogCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.category.ProdCatalogCategory;


public class FindAllProdCatalogCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdCatalogCategory> returnVal = new ArrayList<ProdCatalogCategory>();
try{
List<GenericValue> results = delegator.findAll("ProdCatalogCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProdCatalogCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProdCatalogCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
