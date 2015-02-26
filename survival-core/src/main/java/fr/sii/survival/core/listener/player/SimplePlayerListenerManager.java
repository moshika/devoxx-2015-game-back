package fr.sii.survival.core.listener.player;

import java.util.HashMap;
import java.util.Map;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionListenerException;
import fr.sii.survival.core.exception.PlayerListenerException;
import fr.sii.survival.core.service.error.ErrorService;
import fr.sii.survival.core.service.extension.ExtensionService;

public class SimplePlayerListenerManager implements PlayerListenerManager {
	private Map<String, PlayerListener> listeners;
	
	private ErrorService errorService;
	
	private ExtensionService extensionService;

	public SimplePlayerListenerManager(ErrorService errorService, ExtensionService extensionService) {
		this(new HashMap<>(), errorService, extensionService);
	}
	
	public SimplePlayerListenerManager(Map<String, PlayerListener> listeners, ErrorService errorService, ExtensionService extensionService) {
		super();
		this.listeners = listeners;
		this.errorService = errorService;
		this.extensionService = extensionService;
	}

	@Override
	public void addPlayerListener(PlayerListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Player listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removePlayerListener(PlayerListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}

	@Override
	public void triggerDead(Player player) {
		for(PlayerListener listener : listeners.values()) {
			try {
				listener.dead(player);
			} catch(Throwable e) {
				errorService.addError(new PlayerListenerException("failed to trigger dead event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerRevived(Player player) {
		for(PlayerListener listener : listeners.values()) {
			try {
				listener.revived(player);
			} catch(Throwable e) {
				errorService.addError(new PlayerListenerException("failed to trigger revived event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerHit(Player player, int damage) {
		for(PlayerListener listener : listeners.values()) {
			try {
				listener.hit(player, damage);
			} catch(Throwable e) {
				errorService.addError(new ActionListenerException("failed to trigger hit event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerHealed(Player player, int amount) {
		for(PlayerListener listener : listeners.values()) {
			try {
				listener.healed(player, amount);
			} catch(Throwable e) {
				errorService.addError(new PlayerListenerException("failed to trigger healed event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}
	
	private String getKey(PlayerListener listener) {
		return listener.getClass().toString();
	}
}
