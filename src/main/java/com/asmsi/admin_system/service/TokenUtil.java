package com.asmsi.admin_system.service;

import java.util.UUID;

public class TokenUtil {

    public static String generateResetToken() {
        return UUID.randomUUID().toString();
    }

}
