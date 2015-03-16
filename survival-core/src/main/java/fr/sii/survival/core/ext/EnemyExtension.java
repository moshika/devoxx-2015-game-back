package fr.sii.survival.core.ext;

import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.domain.player.SimpleEnemy;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.service.action.ActionService;

/**
 * Base class for enemy extensions
 * 
 * @author aurelien
 *
 */
public abstract class EnemyExtension {
	/**
	 * The service used to execute actions
	 */
	protected ActionService actionService;

	/**
	 * The enemy the extension is managing
	 */
	protected Enemy enemy;

	protected EnemyExtension(Enemy enemy) {
		super();
		this.enemy = enemy;
	}

	protected EnemyExtension(String name, String avatar, int life) {
		this(new SimpleEnemy(name, avatar, life));
	}

	/**
	 * Initialize the extension
	 */
	public abstract void init();

	/**
	 * Run the extension
	 * 
	 * @param context
	 *            the context of the game (game, players, board)
	 * @throws GameException
	 *             when the extension failed to run
	 */
	public abstract void run(GameContext context) throws GameException;

	public Enemy getEnemy() {
		return enemy;
	}

	protected ActionService getActionService() {
		return actionService;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
}
