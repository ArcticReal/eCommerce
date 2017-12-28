package com.skytala.eCommerce.domain.content.relations.dataResource.control.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.image.AddImageDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.image.DeleteImageDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.command.image.UpdateImageDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.image.ImageDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.image.ImageDataResourceDeleted;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.image.ImageDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.image.ImageDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.image.ImageDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.image.ImageDataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.image.FindImageDataResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/dataResource/imageDataResources")
public class ImageDataResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ImageDataResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ImageDataResource
	 * @return a List with the ImageDataResources
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ImageDataResource>> findImageDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindImageDataResourcesBy query = new FindImageDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ImageDataResource> imageDataResources =((ImageDataResourceFound) Scheduler.execute(query).data()).getImageDataResources();

		return ResponseEntity.ok().body(imageDataResources);

	}

	/**
	 * creates a new ImageDataResource entry in the ofbiz database
	 * 
	 * @param imageDataResourceToBeAdded
	 *            the ImageDataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ImageDataResource> createImageDataResource(@RequestBody ImageDataResource imageDataResourceToBeAdded) throws Exception {

		AddImageDataResource command = new AddImageDataResource(imageDataResourceToBeAdded);
		ImageDataResource imageDataResource = ((ImageDataResourceAdded) Scheduler.execute(command).data()).getAddedImageDataResource();
		
		if (imageDataResource != null) 
			return successful(imageDataResource);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ImageDataResource with the specific Id
	 * 
	 * @param imageDataResourceToBeUpdated
	 *            the ImageDataResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateImageDataResource(@RequestBody ImageDataResource imageDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		imageDataResourceToBeUpdated.setnull(null);

		UpdateImageDataResource command = new UpdateImageDataResource(imageDataResourceToBeUpdated);

		try {
			if(((ImageDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{imageDataResourceId}")
	public ResponseEntity<ImageDataResource> findById(@PathVariable String imageDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("imageDataResourceId", imageDataResourceId);
		try {

			List<ImageDataResource> foundImageDataResource = findImageDataResourcesBy(requestParams).getBody();
			if(foundImageDataResource.size()==1){				return successful(foundImageDataResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{imageDataResourceId}")
	public ResponseEntity<String> deleteImageDataResourceByIdUpdated(@PathVariable String imageDataResourceId) throws Exception {
		DeleteImageDataResource command = new DeleteImageDataResource(imageDataResourceId);

		try {
			if (((ImageDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
