package fr.sii.survival.core.listener.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;

public abstract class AbstractSingleGameActionListener implements ActionListener {

	private Game game;
	
	public AbstractSingleGameActionListener(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void lifeUpdated(Game game, Player player, UpdateLife action) {
		if(this.game.equals(game)) {
			lifeUpdated(player, action);
		}
	}

	@Override
	public void positionChanged(Game game, Player player, ChangePosition action) {
		if(this.game.equals(game)) {
			positionChanged(player, action);
		}
	}

	@Override
	public void imageMoved(Game game, MoveImage action) {
		if(this.game.equals(game)) {
			imageMoved(action);
		}
	}

	@Override
	public void stateChanged(Game game, Player player, ChangeStates action) {
		if(this.game.equals(game)) {
			stateChanged(player, action);
		}
	}

	protected abstract void lifeUpdated(Player player, UpdateLife action);

	protected abstract void positionChanged(Player player, ChangePosition action);

	protected abstract void imageMoved(MoveImage action);

	protected abstract void stateChanged(Player player, ChangeStates action);
}
