package com.groweasy.paymentservice.shared.storage.service;

import java.io.InputStream;

public interface AzureBlobStorageService {
  String uploadFileToBlobStorage(String filename, InputStream inputStream, Integer length, String containerName);
}