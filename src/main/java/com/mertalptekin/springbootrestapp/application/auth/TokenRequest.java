package com.mertalptekin.springbootrestapp.application.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenRequest(String username, String password) {
}
