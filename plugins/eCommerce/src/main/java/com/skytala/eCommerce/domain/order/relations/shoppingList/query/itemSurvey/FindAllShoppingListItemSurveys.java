
package com.skytala.eCommerce.domain.order.relations.shoppingList.query.itemSurvey;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey.ShoppingListItemSurveyFound;
import com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.itemSurvey.ShoppingListItemSurveyMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.itemSurvey.ShoppingListItemSurvey;


public class FindAllShoppingListItemSurveys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShoppingListItemSurvey> returnVal = new ArrayList<ShoppingListItemSurvey>();
try{
List<GenericValue> results = delegator.findAll("ShoppingListItemSurvey", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShoppingListItemSurveyMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShoppingListItemSurveyFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
