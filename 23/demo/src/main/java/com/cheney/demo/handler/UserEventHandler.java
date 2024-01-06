package com.cheney.demo.handler;

import com.cheney.demo.modle.User;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
    @HandleBeforeCreate
    public void handleBeforeCreate(User user) {
        System.out.println("handleBeforeCreate user");
    }
}
