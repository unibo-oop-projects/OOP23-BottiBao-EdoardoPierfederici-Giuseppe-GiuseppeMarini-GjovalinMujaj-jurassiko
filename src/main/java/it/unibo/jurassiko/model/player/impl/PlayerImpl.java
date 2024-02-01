package it.unibo.jurassiko.model.player.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Implementation of the interface {@link Player}.
 */
public class PlayerImpl implements Player, Cloneable {

    private final Color color;
    private final Objective objective;
    private final Set<Territory> territories;
    private final Set<Ocean> oceans;
    private int bonusGroundDino;
    private int bonusWaterDino;

    /**
     * Constructor for the player.
     * 
     * @param color           player's color
     * @param objective       player's objective
     * @param territories     player's owned territories
     * @param oceans          player's owned oceans
     * @param bonusGroundDino player's bonus ground dino
     * @param bonusWaterDino  player's bonus water dino
     */
    public PlayerImpl(final Color color,
            final Objective objective,
            final Set<Territory> territories,
            final Set<Ocean> oceans,
            final int bonusGroundDino,
            final int bonusWaterDino) {
        this.color = color;
        this.objective = objective;
        this.territories = territories;
        this.oceans = oceans;
        this.bonusGroundDino = bonusGroundDino;
        this.bonusWaterDino = bonusWaterDino;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective getObjective() {
        return objective;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isObjectiveComplete() {
        return objective.isAchieved();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayerTerritory(final Territory territory) {
        territories.add(territory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlayerTerritory(final Territory territory) {
        if (territories.contains(territory)) {
            territories.remove(territory);
        }
    }

    /**
     * {@inheritDoc}
     */
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> getOwnedTerritories() {
        return Set.copyOf(territories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayerOcean(final Ocean ocean) {
        oceans.add(ocean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlayerOcean(final Ocean ocean) {
        if (oceans.contains(ocean)) {
            oceans.remove(ocean);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Ocean> getOwnedOceans() {
        return Set.copyOf(oceans);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBonusGroundDino(final int amount) {
        this.bonusGroundDino = amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBonusWaterDino(final int amount) {
        this.bonusWaterDino = amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBonusGroundDino() {
        return bonusGroundDino;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWaterDino() {
        return bonusWaterDino;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        final Logger logger = LoggerFactory.getLogger(PlayerImpl.class);
        try {
            return (Player) this.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("Cannot create a copy");
        }
        throw new IllegalStateException("Can't create a copy of the player");
    }

}
