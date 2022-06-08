package com.lemint.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HelloResponseDto {

  private final String name;
  private final int amount;
}
