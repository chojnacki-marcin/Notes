package com.Notes.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    public boolean isOwner(Authentication authentication, long noteID);
}
