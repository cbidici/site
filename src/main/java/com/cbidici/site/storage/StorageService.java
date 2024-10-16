package com.cbidici.site.storage;

import com.cbidici.site.storage.config.StorageProperties;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

  private final Path rootLocation;

  public StorageService(StorageProperties properties) {
    if(properties.getLocation().trim().isEmpty()){
      throw new StorageException("File upload location can not be Empty.");
    }
    this.rootLocation = Paths.get(properties.getLocation());
    init();
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void store(MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file.");
      }
      Path destinationFile = this.rootLocation.resolve(
              Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
          .normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        // This is a security check
        throw new StorageException(
            "Cannot store file outside current directory.");
      }

      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile);
      }
    }
    catch (IOException e) {
      throw new StorageException("Failed to store file.", e);
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void delete(String fileName) {
    if (!StringUtils.hasText(fileName)) {
      throw new StorageException("Failed to delete empty file name.");
    }
    Path targetFile = this.rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
    if (!targetFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
      // This is a security check
      throw new StorageException("Cannot delete file from outside current directory.");
    }

    if(!targetFile.toFile().exists()) {
      throw new StorageException("File does not exists to delete " + fileName);
    }
    if(!targetFile.toFile().delete()) {
      throw new StorageException("Could not delete file " + fileName);
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
          .filter(path -> !path.equals(this.rootLocation))
          .map(this.rootLocation::relativize);
    }
    catch (IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }
  }

  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  private void init() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }
}