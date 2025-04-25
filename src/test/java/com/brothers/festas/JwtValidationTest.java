package com.brothers.festas;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

public class JwtValidationTest {

    @Test
    public void testValidateToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHaWxlIiwiaWF0IjoxNzQxMzE4NTE3LCJleHAiOjE3NDE0MDQ5MTd9.8klnbT4Z_VLdGoNIquwytYLyWEYQ9NDqSiiSfOZ1VVA";
        String secretKeyBase64 = "oM+xsuiZx5dLZqB2hq1kcVJPfaAadcFLnzxHbZA/AFc=";
        try {
            Jwts.parserBuilder().setSigningKey(java.util.Base64.getDecoder().decode(secretKeyBase64)).build().parseClaimsJws(token);
        } catch (Exception e) {
            System.err.println("Token validation failed: " + e.getMessage());
        }
    }
}
