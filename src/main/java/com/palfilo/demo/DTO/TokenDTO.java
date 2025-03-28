package com.palfilo.demo.DTO;

import com.palfilo.demo.models.Users;

public record TokenDTO(
        Integer accountId,
        String token
) {
    public TokenDTO(Users account, String token){
        this(account.getUserId(), token);
    }
}
