package com.kalyan.CoreDesk.Utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SessionUtil {
    public static Long getSessionUserId(HttpSession session) {
        System.out.println("Checking session ID: " + session.getId());
        Object userIdObj = session.getAttribute("userId");
        System.out.println("UserId in session: " + userIdObj);
        if (userIdObj == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }
        if (userIdObj instanceof Number) {
            return ((Number) userIdObj).longValue();
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid userId format in session");
    }

    public static void verifyOwnership(Long urlUserId, HttpSession session) {
        Long sessionUserId = getSessionUserId(session);
        if (!sessionUserId.equals(urlUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }
}
