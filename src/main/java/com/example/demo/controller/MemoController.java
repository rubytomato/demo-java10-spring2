package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;

@RestController
@RequestMapping(path = "memo")
public class MemoController {

  private final MemoService memoService;
  private final static String SUCCESS = "success";

  public MemoController(MemoService service) {
    memoService = service;
  }

  @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Memo> id(@PathVariable(value = "id") Long id) {
    Optional<Memo> memo = memoService.findById(id);
    return memo.map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound()
                                              .build());
  }

  @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<Memo>> list(Pageable page) {
    Page<Memo> memoPage = memoService.findAll(page);
    return ResponseEntity.ok(memoPage.getContent());
  }

  @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String store(@RequestBody Memo memo) {
    memoService.store(memo);
    return SUCCESS;
  }

  @PatchMapping(produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String update(@RequestBody Memo memo) {
    memoService.update(memo);
    return SUCCESS;
  }

  @DeleteMapping(path = "{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  public String remove(@PathVariable(value = "id") Long id) {
    memoService.removeById(id);
    return SUCCESS;
  }

}
