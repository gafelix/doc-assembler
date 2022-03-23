package br.com.looplex.docassembler.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LeafDocument extends Document {

    @NonNull
    @NotNull
    private String text;

}