package io.doubleloop.drivenpush.adapter;

import io.doubleloop.drivenpush.domain.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @PostMapping("")
  public ResponseEntity<Void> postMessage(@Valid @RequestBody PostMessageRequest request) {
    service.postMessage(request.asCommand());
    return ResponseEntity.noContent().build();
  }
}
