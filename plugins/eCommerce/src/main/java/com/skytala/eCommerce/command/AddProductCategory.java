package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductCategory;
import com.skytala.eCommerce.event.ProductCategoryAdded;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class AddProductCategory implements Command {

private ProductCategory elementToBeAdded;
public AddProductCategory(ProductCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public void execute(){


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
}
}
