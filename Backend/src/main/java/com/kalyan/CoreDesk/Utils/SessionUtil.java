package com.kalyan.CoreDesk.Utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SessionUtil {
    public static Long getSessionUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }
        return userId;
    }

    public static void verifyOwnership(Long urlUserId, HttpSession session) {
        Long sessionUserId = getSessionUserId(session);
        if (!sessionUserId.equals(urlUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }
}
