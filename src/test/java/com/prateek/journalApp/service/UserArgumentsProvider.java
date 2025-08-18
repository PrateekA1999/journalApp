package com.prateek.journalApp.service;

import com.prateek.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("Monu").password("Monu@123").roles(List.of("USER")).build()),
                Arguments.of(User.builder().userName("Sonu").password("Sonu@123").roles(List.of("USER")).build())
        );
    }
}
