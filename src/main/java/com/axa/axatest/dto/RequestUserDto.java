package com.axa.axatest.dto;

import jakarta.annotation.Nonnull;

public record RequestUserDto(Long userId, @Nonnull String userName, @Nonnull String password) {
}
