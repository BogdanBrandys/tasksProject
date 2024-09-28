package com.crud.tasks.domain;

import lombok.*;

import java.util.Optional;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@ToString
public class Mail {
    @NonNull
    private final String mailTo;
    @NonNull
    private final String subject;
    @NonNull
    private final String message;
    @Builder.Default
    private Optional<String> toCC = Optional.empty();

    public boolean hasCc() {
        return toCC.isPresent();
    }

}