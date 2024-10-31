package com.groweasy.paymentservice.shared.storage.service.impl;

import com.groweasy.paymentservice.shared.storage.domain.MyFile;
import com.groweasy.paymentservice.shared.storage.service.AzureBlobStorageService;
import com.groweasy.paymentservice.shared.storage.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MyFileServiceImpl implements MyFileService {
  @Autowired
  private AzureBlobStorageService azureBlobStorageService;

  public MyFileServiceImpl() {
  }

  public String uploadFile(MultipartFile file, String filename, String containerName) throws IOException {
    String azureUrl = this.azureBlobStorageService.uploadFileToBlobStorage(filename, file.getInputStream(), (int)file.getSize(), containerName);
    MyFile fileEntity = new MyFile();
    fileEntity.setFilename(filename);
    fileEntity.setAzureUrl(azureUrl);
    return fileEntity.getAzureUrl();
  }
}