package com.skytala.eCommerce.domain.product.relations.prodCatalog.query.categoryType;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.categoryType.ProdCatalogCategoryTypeFound;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.categoryType.ProdCatalogCategoryTypeMapper;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType.ProdCatalogCategoryType;

public class FindProdCatalogCategoryTypesBy extends Query {


Map<String, String> filter;
public FindProdCatalogCategoryTypesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProdCatalogCategoryType> foundProdCatalogCategoryTypes = new ArrayList<ProdCatalogCategoryType>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("prodCatalogCategoryTypeId")) { 
 GenericValue foundElement = delegator.findOne("ProdCatalogCategoryType", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProdCatalogCategoryType.class); 
 } 
}else { 
 buf = delegator.findAll("ProdCatalogCategoryType", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProdCatalogCategoryTypes.add(ProdCatalogCategoryTypeMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ProdCatalogCategoryTypeFound(foundProdCatalogCategoryTypes);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public boolean applysToFilter(GenericValue val) {

Iterator<String> iterator = filter.keySet().iterator();

while(iterator.hasNext()) {

String key = iterator.next();

if(val.get(key) == null) {
return false;
}

if((val.get(key).toString()).contains(filter.get(key))) {
}else {
return false;
}
}
return true;
}
public void setFilter(Map<String, String> newFilter) {
this.filter = newFilter;
}
}
