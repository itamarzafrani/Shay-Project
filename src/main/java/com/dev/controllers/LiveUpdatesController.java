package com.dev.controllers;

import com.dev.objects.User;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

import static com.dev.utils.Constants.*;

@Controller
public class LiveUpdatesController {

    @Autowired
    private Persist persist;

    private List<SseEmitter> emitterList = new ArrayList<>();
    private Map<String , SseEmitter> emitterMap = new HashMap<>();

//    @PostConstruct
//    public void init() {
//        new Thread(() -> {
//            while (true) {
//                for (SseEmitter sseEmitter : emitterList) {
//                    try {
//                        sseEmitter.send(new Date().toString());
//                    } catch (Exception e) {
//
//                    }
//                    try {
//                        Thread.sleep(SECOND);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }

    @RequestMapping (value = "/sse-handler", method = RequestMethod.GET)
    public SseEmitter handle (String token, int recipientId) {
        User user = persist.getUserByToken(token);
        SseEmitter sseEmitter = null;
        if (user != null) {
            sseEmitter = new SseEmitter(10L * MINUTE);
            String key = createKey(user.getId(), recipientId);
            this.emitterMap.put(key, sseEmitter);
        }
        return sseEmitter;
    }

    private String createKey (int senderId , int recipientId) {
        return String.format("%d_%d",senderId,recipientId);
    }

    public void sendStartTypingEvent (int senderId, int recipientId) {
        String key = createKey(recipientId, senderId);
        SseEmitter conversationEmitter = this.emitterMap.get(key);
        if (conversationEmitter != null) {
            try {
                conversationEmitter.send(EVENT_TYPING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
