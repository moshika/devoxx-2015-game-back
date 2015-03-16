package fr.sii.survival.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.config.options.BoardOptions;
import fr.sii.survival.core.listener.board.BoardListenerManager;
import fr.sii.survival.core.listener.board.SimpleBoardListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.RandomCellProvider;
import fr.sii.survival.core.service.board.SimpleBoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

@Configuration
@Component
public class BoardConfiguration {
	public static final String BOARD_PUBLISH_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX+"/board";
	public static final String BOARD_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX+"/board";

	@Autowired
	MessageService errorService;
	
	@Autowired
	ExtensionService extensionService;

	@Autowired
	BoardOptions boardOptions;
	
	@Bean
	public BoardService boardService() {
		return new SimpleBoardService(boardOptions.getWidth(), boardOptions.getHeight(), cellProvider(), boardListenerManager());
	}

	@Bean
	public RandomCellProvider cellProvider() {
		return new RandomCellProvider();
	}

	@Bean
	public BoardListenerManager boardListenerManager() {
		return new SimpleBoardListenerManager(errorService, extensionService);
	}
}
