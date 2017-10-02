package com.skytala.eCommerce.domain.productCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productCategory.event.ProductCategoryAdded;
import com.skytala.eCommerce.domain.productCategory.model.ProductCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddProductCategory extends Command {

private ProductCategory elementToBeAdded;
public AddProductCategory(ProductCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try {
elementToBeAdded.setProductCategoryId(delegator.getNextSeqId("ProductCategory"));
GenericValue newValue = delegator.makeValue("ProductCategory", elementToBeAdded.mapAttributeField());
delegator.create(newValue);
success = true;
} catch(GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}

Broker.instance().publish(new ProductCategoryAdded(success));
return null;
}
}
