package bit.com.inpho.postutile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.cloud.Identity;
import com.google.cloud.Policy;
import com.google.cloud.storage.StorageRoles;
import java.util.HashMap;

import java.util.Map;
@Service
public class UploadObjectMyPage {

	@Autowired
	private fileUploadService fie;
	
	public String storageUploadObject(String projectId, String bucketName, String objectName, String filePath)
			throws IOException {
		System.out.println(filePath);
//		try {
//			hashTag=googleObj.detectLabels(filePath);
//		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		String extName= objectName.substring(objectName.lastIndexOf("."), objectName.length());
		System.out.println("extName" + extName);
		
		fie.genSaveFileName(extName);
		String saveFileName = fie.genSaveFileName(extName);
		
		System.out.println("saveFilename" + saveFileName);
		
		 Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		    Policy originalPolicy = storage.getIamPolicy(bucketName);
		    storage.setIamPolicy(
		        bucketName,
		        originalPolicy
		            .toBuilder()
		            .addIdentity(StorageRoles.objectViewer(), Identity.allUsers()) // All users can view
		            .build());
		    
		    System.out.println("Bucket " + bucketName + " all public files");
		String str = new String(filePath);
		storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, saveFileName);
		
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, Files.readAllBytes(Paths.get(str)));
		System.out.println("realFile " + filePath + " uploaded to bucket " + bucketName + " as " + saveFileName);

		
		 storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		    Map<String, String> newMetadata = new HashMap<>();
		    newMetadata.put("ContentType", "image/png");
		    Blob blob = storage.get(bucketName, saveFileName);

		    blob.toBuilder().setMetadata(newMetadata).build().update();

		    System.out.println(
		        "Updated custom metadata for object " + saveFileName + " in bucket " + bucketName);

		    return saveFileName;
	}

}
