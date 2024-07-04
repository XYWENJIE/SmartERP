package com.benjamin.smarterp.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("chat")
public class ChatController {

    /**
     * 获取联系人
     */
    @GetMapping("contacts")
    public List<Contact> contacts() throws InterruptedException {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact("busy","e99f09a7-dd38-49d5-b1c8-1daf80c2d7b1","CEO","nannie.abernathy70@yahoo.com","Jayvion Simon",LocalDateTime.now(),"https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp","+1 202-555-0143"));
        contactList.add(new Contact("busy","e99f09a7-dd28-49d5-b1c8-1daf80c3d7b1","CEO","nannie.abernathy70@yahoo.com","Jayvion Simon",LocalDateTime.now(),"https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp","+1 202-555-0143"));
        contactList.add(new Contact("busy","e99f09a7-dd81-49d5-b1c8-1daf80c4d7b1","CEO","nannie.abernathy70@yahoo.com","Jayvion Simon",LocalDateTime.now(),"https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp","+1 202-555-0143"));
        contactList.add(new Contact("busy","e99f09a7-dd288-49d5-b1c8-1daf80c5d7b1","CEO","nannie.abernathy70@yahoo.com","Jayvion Simon",LocalDateTime.now(),"https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp","+1 202-555-0143"));
        contactList.add(new Contact("busy","e99f09a7-dd388-49d5-b1c8-1daf80c6d7b1","CEO","nannie.abernathy70@yahoo.com","Jayvion Simon",LocalDateTime.now(),"https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp","+1 202-555-0143"));
        contactList.add(new Contact("busy","e99f09a7-d488-49d5-b1c8-1daf80c7d7b1","CEO","nannie.abernathy70@yahoo.com","Jayvion Simon",LocalDateTime.now(),"https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp","+1 202-555-0143"));
        contactList.add(new Contact("busy","e99f09a7-dd818-49d5-b1c8-1daf80c8d7b1","AI","nannie.abernathy70@yahoo.com","ChatGML",LocalDateTime.now(),"http://localhost:8080/a821c0ab-f8a0-5284-a714-679e578992f5_0.png","+1 202-555-0143"));
        return contactList;
    }

    @GetMapping("conversation/{conversationId}")
    public Conversation conversation(@PathVariable("conversationId") String conversationId){
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("a4b3488f-3217-4502-be98-caf19afa5099","e99f09a7-dd88-49d5-b1c8-1daf80c2d7b2","She eagerly opened the gift, her eyes sparkling with excitement.","text",LocalDateTime.now()));
        return new Conversation(conversationId,messageList);
    }

    public record Contact(@JsonProperty("status") String status,
                          @JsonProperty("id") String id,
                          @JsonProperty("role") String role,
                          @JsonProperty("email") String email,
                          @JsonProperty("name") String name,
                          @JsonProperty("lastActivity")LocalDateTime lastActivity,
                          @JsonProperty("avatarUrl") String avatarUrl,
                          @JsonProperty("phoneNumber") String phoneNumber){}

    public record Conversation(@JsonProperty("id") String id,
                               @JsonProperty("messages") List<Message> messages){}

    public record Message(@JsonProperty("id") String id,
                          @JsonProperty("senderId") String senderId,
                          @JsonProperty("body") String body,
                          @JsonProperty("contentType") String contentType,
                          @JsonProperty("createAt") LocalDateTime createAt){}
}
