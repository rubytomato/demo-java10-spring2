package com.example.demo.service.impl;

import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemoServiceIntegrationTests {

  @Autowired
  private MemoService sut;

  @Transactional(readOnly = true)
  @Test
  public void findById() {
    var actual = sut.findById(1L).orElse(null);

    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isEqualTo(1L);
  }

  @Transactional(readOnly = true)
  @Test
  public void findAll() {
    Pageable page = PageRequest.of(0, 3);
    Page<Memo> actual = sut.findAll(page);

    assertThat(actual).isNotNull();
    assertThat(actual.getContent()).hasSize(3);
  }

}
