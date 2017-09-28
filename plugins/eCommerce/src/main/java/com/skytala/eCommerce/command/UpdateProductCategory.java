package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductCategory;
import com.skytala.eCommerce.event.ProductCategoryUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class UpdateProductCategory implements Command {

private ProductCategory elementToBeUpdated;

public UpdateProductCategory(ProductCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public void execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}
Broker.instance().publish(new ProductCategoryUpdated(success));
}
}
