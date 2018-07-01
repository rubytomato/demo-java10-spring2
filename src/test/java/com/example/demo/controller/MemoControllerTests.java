package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MemoController.class)
// @WebMvcTest should also auto-configure Spring Data web integration #9028
// https://github.com/spring-projects/spring-boot/issues/9028
@Import(SpringDataWebAutoConfiguration.class)
public class MemoControllerTests {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MemoService memoService;

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

  @Test
  public void getMemo() throws Exception {
    var expected = Memo.of(1L, "test title", "test description");
    var expectedJson = objectMapper.writeValueAsString(expected);
    Mockito.when(memoService.findById(anyLong()))
           .thenReturn(Optional.ofNullable(expected));

    RequestBuilder builder = MockMvcRequestBuilders.get("/memo/{id}", 1L)
                                                   .accept(MediaType.APPLICATION_JSON_UTF8);

    MvcResult result = mvc.perform(builder)
                          .andExpect(status().isOk())
                          .andExpect(content().contentType(contentType))
                          .andExpect(jsonPath("$").isNotEmpty())
                          .andExpect(jsonPath("$.title").value(expected.getTitle()))
                          .andExpect(jsonPath("$.description").value(expected.getDescription()))
                          .andExpect(jsonPath("$.done").value(expected.getDone()))
                          .andDo(print())
                          .andReturn();

    assertThat(result.getResponse()
                     .getContentAsString()).isEqualTo(expectedJson);
  }

  @Test
  public void getList() throws Exception {
    List<Memo> list = List.of(Memo.of(1L, "test title1", "test description1"), Memo.of(2L, "test title2", "test description2"), Memo.of(3L, "test title3", "test description3"));
    Pageable page = PageRequest.of(0, 5);
    Page<Memo> expected = new PageImpl<>(list, page, 3);
    Mockito.when(memoService.findAll(eq(page)))
           .thenReturn(expected);

    RequestBuilder builder = MockMvcRequestBuilders.get("/memo/list")
                                                   .param("page", "0")
                                                   .param("size", "5")
                                                   .accept(MediaType.APPLICATION_JSON_UTF8);

    MvcResult result = mvc.perform(builder)
                          .andExpect(status().isOk())
                          .andExpect(content().contentType(contentType))
                          .andDo(print())
                          .andReturn();

    System.out.println(result.getResponse()
                             .getContentAsString());
  }

}
