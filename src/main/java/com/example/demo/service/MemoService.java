package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Memo;

public interface MemoService {
  Optional<Memo> findById(Long id);

  Page<Memo> findAll(Pageable page);

  void store(Memo memo);

  void update(Memo memo);

  void done(Long id);

  void removeById(Long id);
}
