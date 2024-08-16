package com.benjamin.smarterp.controller;

import com.benjamin.smarterp.domain.Request;
import com.benjamin.smarterp.domain.ResultStatus;
import com.benjamin.smarterp.domain.entity.*;
import com.benjamin.smarterp.repository.jpa.*;
import com.benjamin.smarterp.service.CommonService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("chat")
public class ChatController {
	
	private Logger log = LoggerFactory.getLogger(ChatController.class);

    private final ChatMessageRepository chatMessageRepository;
    private final PersonnelRepository personnelRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipationRepository conversationParticipationRepository;
    private final ConversationMessageRepository conversationMessageRepository;
    private final NotificationRepository notificationRepository;
    private final CommonService commonService;

    public ChatController(ChatMessageRepository chatMessageRepository,
                          PersonnelRepository personnelRepository, ConversationRepository conversationRepository, ConversationParticipationRepository conversationParticipationRepository, ConversationMessageRepository conversationMessageRepository, NotificationRepository notificationRepository, CommonService commonService) {
        this.chatMessageRepository = chatMessageRepository;
        this.personnelRepository = personnelRepository;
        this.conversationRepository = conversationRepository;
        this.conversationParticipationRepository = conversationParticipationRepository;
        this.conversationMessageRepository = conversationMessageRepository;
        this.notificationRepository = notificationRepository;
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
            return criteriaBuilder.and(criteriaBuilder.notEqual(root.get("id"),personnel.getId()),criteriaBuilder.or(predicates));
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
    public ResultStatus<String> createConversation(@RequestBody @Valid Request.CreateConversation createConversation){
        log.info("创建聊天会话{}",createConversation);
        Conversation conversation = new Conversation();
        conversation.setId(UUID.randomUUID().toString());
        conversation.setConversationName(createConversation.contacts().toString());
        conversationRepository.save(conversation);
        Personnel currentPersonnel = commonService.authenticationConvert();
        ConversationParticipation currentParticipation = new ConversationParticipation();
        currentParticipation.setPersonnel(currentPersonnel);
        currentParticipation.setConversation(conversation);
        this.conversationParticipationRepository.save(currentParticipation);
        ConversationMessage currentConvMessage = new ConversationMessage();
        currentConvMessage.setId(UUID.randomUUID().toString());
        currentConvMessage.setMessage(createConversation.message());
        currentConvMessage.setPersonnel(currentPersonnel);
        currentConvMessage.setConversation(conversation);
        currentConvMessage.setCreateTime(LocalDateTime.now());
        this.conversationMessageRepository.save(currentConvMessage);

        Optional<Personnel> optionalPersonnel = this.personnelRepository.findById(createConversation.contacts().get(0));
        if(optionalPersonnel.isPresent()){
            Personnel personnel = optionalPersonnel.get();
            ConversationParticipation conversationPersonnelParticipation = new ConversationParticipation();
            conversationPersonnelParticipation.setPersonnel(personnel);
            conversationPersonnelParticipation.setConversation(conversation);
            this.conversationParticipationRepository.save(conversationPersonnelParticipation);
            log.info("保存聊天谈话参与人信息,并更新通知聊天信息");
            Optional<Notification> optional = this.notificationRepository.findBySenderUserIdAndType(currentPersonnel.getUserLogin().getId(), Notification.Type.CHAT);
            if(optional.isPresent()){
                Notification notification = optional.get();
                //this.conversationMessageRepository
                notification.setContent("您有{}条未读消息");
                this.notificationRepository.save(notification);
            }else{
            	Notification notification = new Notification();
            	notification.setSenderUser(currentPersonnel.getUserLogin());
            	notification.setReceiverUser(personnel.getUserLogin());
            	notification.setType(Notification.Type.CHAT);
            	notification.setStatus(Notification.Status.UNREAD);
            	notification.setContent("您有1条未读消息");
                this.notificationRepository.save(notification);
            }
            if(personnel.getRobot()){
                log.info("机器人聊天");
                ConversationMessage conversationMessage = new ConversationMessage();
                conversationMessage.setConversation(conversation);
                conversationMessage.setPersonnel(personnel);
                conversationMessage.setMessage("你好");
            }
        }
        return ResultStatus.success(conversation.getId());
    }

    @GetMapping("conversation/{conversationId}")
    public ConversationResponse conversation(@PathVariable("conversationId") String conversationId) throws InterruptedException {
//        List<Message> messageList = new ArrayList<>();
//        messageList.add(new Message("a4b3488f-3217-4502-be98-caf19afa5099","e99f09a7-dd88-49d5-b1c8-1daf80c2d7b2","She eagerly opened the gift, her eyes sparkling with excitement.","text",LocalDateTime.now()));
//        messageList.add(new Message("32c5112f-2602-4796-918c-a925374cba20","8864c717-587d-472a-929a-8e5f298024da-0","The old oak tree stood tall and majestic, its branches swaying gently in the breeze.","text",LocalDateTime.now()));
//        return new ConversationResponse(conversationId,messageList);
        Optional<Conversation> optional = this.conversationRepository.findById(conversationId);
        if(optional.isPresent()){
            Conversation conversation = optional.get();
            List<Message> messageList = conversation.getConversationMessages().stream().map(message -> new Message(message.getId(),message.getPersonnel().getId().toString(),message.getMessage(),"text",message.getCreateTime())).toList();
            return new ConversationResponse(conversation.getId(),messageList);
        }
        return null;
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

    public record ConversationResponse(@JsonProperty("id") String id,
                               @JsonProperty("messages") List<Message> messages){}

    public record Message(@JsonProperty("id") String id,
                          @JsonProperty("senderId") String senderId,
                          @JsonProperty("body") String body,
                          @JsonProperty("contentType") String contentType,
                          @JsonProperty("createAt") LocalDateTime createAt){}

    public record MessageRequest(@NotNull String id, @NotNull String message){}
}
