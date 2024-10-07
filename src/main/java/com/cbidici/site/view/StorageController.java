package com.cbidici.site.view;

import com.cbidici.site.media.StorageFileNotFoundException;
import com.cbidici.site.media.StorageService;
import com.cbidici.site.view.data.FileResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class StorageController {

  private final StorageService storageService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/storage")
  public String getFiles(Model model) throws IOException {
    model.addAttribute("files", storageService.loadAll()
        .map(path -> new FileResponse(
            path.getFileName().toString(),
            MvcUriComponentsBuilder.fromMethodName(
                StorageController.class,
                "getFile",
                path.getFileName().toString()
            ).build().toUri().toString()
        ))
        .collect(Collectors.toList()));
    return "storage";
  }

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {

    try {
      Path file = storageService.load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\""
            )
            .body(resource);
      } else {
        throw new StorageFileNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/storage")
  public String upload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {
    storageService.store(file);
    return "redirect:/storage";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/storage/{filename:.+}/delete")
  public String deleteFile(@PathVariable String filename) {
    storageService.delete(filename);
    return "redirect:/storage";
  }

}
