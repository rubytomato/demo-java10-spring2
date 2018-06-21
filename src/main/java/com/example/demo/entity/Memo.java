package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "memo")
@Data
@Builder
public class Memo implements Serializable {

  private static final long serialVersionUID = -4050542590331251852L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "done", nullable = false)
  private Boolean done;
  @Column(name = "updated", nullable = false)
  private LocalDateTime updated;

  public static Memo of(String title, String description) {
    return Memo.of(null, title, description);
  }

  public static Memo of(Long id, String title, String description) {
    return Memo.builder().id(id).title(title).description(description).done(false).updated(LocalDateTime.now()).build();
  }

  @PrePersist
  private void prePersist() {
    done = false;
    updated = LocalDateTime.now();
  }

  @PreUpdate
  private void preUpdate() {
    updated = LocalDateTime.now();
  }

}
