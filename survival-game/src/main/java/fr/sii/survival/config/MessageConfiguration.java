package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.controller.MessageController;
import fr.sii.survival.core.listener.message.MessageListenerManager;
import fr.sii.survival.core.listener.message.SimpleMessageListenerManager;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.message.SimpleMessageService;

@Configuration
public class MessageConfiguration {
	public static final String MESSAGE_PUBLISH_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX+"/message";

	@Autowired
	MessageController messageController;
	
	@PostConstruct
	public void init() {
		messageService().addMessageListener(messageController);
	}
	
	@Bean
	public MessageService messageService() {
		return new SimpleMessageService(messageListenerManager());
	}

	@Bean
	public MessageListenerManager messageListenerManager() {
		return new SimpleMessageListenerManager();
	}
}
