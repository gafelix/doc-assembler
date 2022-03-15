package br.com.looplex.docassembler.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DocumentTreeDto {

    private String tree;
    private String strategy;

}