package fr.sii.survival.controller;

import static fr.sii.survival.config.AnimationConfiguration.ANIMATION_MAPPING_PREFIX;
import static fr.sii.survival.config.WebSocketConfig.SERVER_PUBLISH_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.service.animation.AnimationService;

@RestController
@RequestMapping("animation")
public class AnimationController {
	@Autowired
	AnimationService animationService;
	
	@MessageMapping(ANIMATION_MAPPING_PREFIX+"/all")
	@SendToUser(SERVER_PUBLISH_PREFIX+"/animation/all")
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable("animations")
	public List<Animation> all() {
		return animationService.getAvailableAnimations();
	}

}
