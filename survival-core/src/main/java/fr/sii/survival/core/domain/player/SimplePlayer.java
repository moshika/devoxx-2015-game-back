package fr.sii.survival.core.domain.player;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.image.Image;

public class SimplePlayer implements Player {
	/**
	 * Counter used to generate a unique id
	 */
	private static int counter;

	/**
	 * The unique id of the wizard
	 */
	private String id;

	/**
	 * The life information
	 */
	private Life life;

	/**
	 * The current states of the wizard
	 */
	private States states;

	/**
	 * The player information
	 */
	private PlayerInfo playerInfo;

	/**
	 * Creates a wizard with 1000 points of life (current and maximum)
	 * 
	 * @param info
	 *            the player information (name and avatar)
	 */
	public SimplePlayer(PlayerInfo info) {
		this(info, 1000);
	}

	/**
	 * Creates a wizard with 1000 points of life (current and maximum)
	 * 
	 * 
	 * @param name
	 *            the name of the player
	 * @param avatar
	 *            the avatar for the player
	 */
	public SimplePlayer(String name, Image avatar) {
		this(new PlayerInfo(name, avatar));
	}

	/**
	 * Creates a wizard with the provided points of life (current and maximum)
	 * 
	 * @param info
	 *            the player information (name and avatar)
	 * @param life
	 *            the points of life of the wizard (current and maximum)
	 */
	public SimplePlayer(PlayerInfo info, int life) {
		this(info, new SimpleLife(life));
	}

	/**
	 * Creates a wizard with the provided points of life (current and maximum)
	 * 
	 * @param name
	 *            the name of the player
	 * @param avatar
	 *            the avatar for the player
	 * @param life
	 *            the points of life of the wizard (current and maximum)
	 */
	public SimplePlayer(String name, Image avatar, int life) {
		this(new PlayerInfo(name, avatar), life);
	}

	/**
	 * Creates a wizard with the provided maximum points of life and the actual
	 * wizard points of life
	 * 
	 * @param name
	 *            the name of the player
	 * @param avatar
	 *            the avatar for the player
	 * @param life
	 *            the actual points of life
	 * @param max
	 *            the maximum points of life
	 */
	public SimplePlayer(String name, Image avatar, int life, int max) {
		this(new PlayerInfo(name, avatar), life, max);
	}

	/**
	 * Creates a wizard with the provided maximum points of life and the actual
	 * wizard points of life
	 * 
	 * @param info
	 *            the player information (name and avatar)
	 * @param life
	 *            the actual points of life
	 * @param max
	 *            the maximum points of life
	 */
	public SimplePlayer(PlayerInfo info, int life, int max) {
		this(info, new SimpleLife(life, max));
	}

	/**
	 * Creates a wizard with provided life
	 * 
	 * @param info
	 *            the player information (name and avatar)
	 * @param life
	 *            the life of the wizard
	 */
	public SimplePlayer(PlayerInfo info, Life life) {
		this(info, life, new States());
	}

	/**
	 * Creates a wizard with provided life and applied states
	 * 
	 * @param name
	 *            the name of the player
	 * @param avatar
	 *            the avatar for the player
	 * @param life
	 *            the life of the wizard
	 * @param states
	 *            the states to apply on
	 */
	public SimplePlayer(String name, Image avatar, Life life, States states) {
		this(new PlayerInfo(name, avatar), life, states);
	}

	/**
	 * Creates a wizard with provided life and applied states
	 * 
	 * @param info
	 *            the player information (name and avatar)
	 * @param life
	 *            the life of the wizard
	 * @param states
	 *            the states to apply on
	 */
	public SimplePlayer(PlayerInfo info, Life life, States states) {
		super();
		id = getClass().getSimpleName() + "-" + (counter++);
		this.playerInfo = info;
		this.life = life;
		this.states = states;
	}

	@Override
	public Life getLife() {
		return life;
	}

	@Override
	public States getStates() {
		return states;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof SimplePlayer)) {
			return false;
		}
		SimplePlayer other = (SimplePlayer) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{").append(getId()).append(playerInfo).append(life).append(states).append("}");
		return builder.toString();
	}
}
