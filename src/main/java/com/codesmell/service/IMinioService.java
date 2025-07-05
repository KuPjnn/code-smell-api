package com.codesmell.service;

import com.codesmell.domain.dto.FileDto;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.InputStream;
import java.util.Collection;

public interface IMinioService {

    Collection<FileDto> listObjects(String path);

    FileDto uploadObject(FileUpload file, String path);

    FileDto fileDetails(String path);

    InputStream downloadObject(String path);

    void deleteObject(String objectName);

}
