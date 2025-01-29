
package com.inavi.backend.user.service;

import com.inavi.backend.user.model.User;

public interface ValidationService {

    void validateUser(User user);

    void validateEmail(String email);
}

