package com.benjamin.smarterp.controller;

import com.benjamin.smarterp.domain.Request;
import com.benjamin.smarterp.domain.ResultStatus;
import com.benjamin.smarterp.domain.entity.ChatMessage;
import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.Team;
import com.benjamin.smarterp.repository.jpa.ChatMessageRepository;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.benjamin.smarterp.service.CommonService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("chat")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final PersonnelRepository personnelRepository;
    private final CommonService commonService;

    public ChatController(ChatMessageRepository chatMessageRepository, PersonnelRepository personnelRepository, CommonService commonService) {
        this.chatMessageRepository = chatMessageRepository;
        this.personnelRepository = personnelRepository;
        this.commonService = commonService;
    }

    /**
     * 获取联系人
     */
    @GetMapping("contacts")
    public List<Contact> contacts() {
        Personnel personnel = this.commonService.authenticationConvert();
        log.debug("获取当前登录对象UserInfo信息{}",personnel);
        List<Personnel> userInfoList = this.personnelRepository.findAll((root, query, criteriaBuilder) -> {
            Predicate[] predicates = new Predicate[2];
            predicates[0] = criteriaBuilder.isTrue(root.get("robot"));
            predicates[1] = root.get("teams").get("id").in(personnel.getTeams().stream().map(Team::getId).toList());
            return criteriaBuilder.or(predicates);
        });
        log.debug("成功加载联系人{}",userInfoList.size());
        List<Contact> contactList = new ArrayList<>();
        contactList.addAll(userInfoList.stream().map(userInfo1 -> new Contact("busy",userInfo1.getId().toString(),"CEO",userInfo1.getEmail(),userInfo1.getRealName(),LocalDateTime.now(),userInfo1.getAvatarUrl(),userInfo1.getPhone())).toList());
        return contactList;
    }

    /**
     * 创建聊天会话
     */
    @PostMapping("conversation")
    public ResultStatus<String> createConversation(Request.CreateConversation createConversation){
        //TODO 创建聊天会话
        return ResultStatus.success("e99f09a7-dd88-49d5-b1c8-1daf80c2d7b2");
    }

    @GetMapping("conversation/{conversationId}")
    public Conversation conversation(@PathVariable("conversationId") String conversationId) throws InterruptedException {
        Thread.sleep(2000);
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("a4b3488f-3217-4502-be98-caf19afa5099","e99f09a7-dd88-49d5-b1c8-1daf80c2d7b2","She eagerly opened the gift, her eyes sparkling with excitement.","text",LocalDateTime.now()));
        messageList.add(new Message("32c5112f-2602-4796-918c-a925374cba20","8864c717-587d-472a-929a-8e5f298024da-0","The old oak tree stood tall and majestic, its branches swaying gently in the breeze.","text",LocalDateTime.now()));
        return new Conversation(conversationId,messageList);
    }

    /**
     * 发送消息
     */
    @PostMapping("message")
    public ResultStatus<String> sendMessage(@RequestBody MessageRequest messageRequest, @AuthenticationPrincipal Authentication authentication){
        ChatMessage chatMessage = new ChatMessage();
        log.info(authentication.getPrincipal().getClass().toString());
        chatMessage.setRoomId(messageRequest.id);
        chatMessage.setContent(messageRequest.message);
        chatMessage.setSendTime(LocalDateTime.now());
        this.chatMessageRepository.save(chatMessage);
        return ResultStatus.success(chatMessage.getId().toString());
    }

    public record CreateConversationResponse(){}

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

    public record MessageRequest(@NotNull String id, @NotNull String message){}
}
