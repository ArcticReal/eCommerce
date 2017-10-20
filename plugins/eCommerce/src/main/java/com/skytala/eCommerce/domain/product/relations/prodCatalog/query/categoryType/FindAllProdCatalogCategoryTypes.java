
package com.skytala.eCommerce.domain.product.relations.prodCatalog.query.categoryType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.categoryType.ProdCatalogCategoryTypeFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.categoryType.ProdCatalogCategoryTypeMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType.ProdCatalogCategoryType;


public class FindAllProdCatalogCategoryTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdCatalogCategoryType> returnVal = new ArrayList<ProdCatalogCategoryType>();
try{
List<GenericValue> results = delegator.findAll("ProdCatalogCategoryType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProdCatalogCategoryTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProdCatalogCategoryTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
