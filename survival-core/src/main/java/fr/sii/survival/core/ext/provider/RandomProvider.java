package fr.sii.survival.core.ext.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.registry.ExtensionRegistry;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.extension.ExtensionService;

/**
 * Select only one enemy from the list of available enemies
 * 
 * @author aurelien
 *
 */
public class RandomProvider extends AbstractProvider {

	private ExtensionRegistry registry;

	public RandomProvider(ActionService actionService, ExtensionService extensionService, ExtensionRegistry registry) {
		super(actionService, extensionService);
		this.registry = registry;
	}

	@Override
	public List<EnemyExtension> getEnemies(Game game) throws ExtensionInitializationException {
		List<Class<? extends EnemyExtension>> classes = registry.getEnemyExtensions();
		if(classes.size()>0) {
			int idx = (int) Math.floor(classes.size() * Math.random());
			Class<? extends EnemyExtension> clazz = classes.get(idx);
			EnemyExtension ext = instantiate(clazz);
			return Arrays.asList(ext);
		} else {
			return new ArrayList<>(0);
		}
	}

}