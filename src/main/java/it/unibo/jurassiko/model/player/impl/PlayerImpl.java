package it.unibo.jurassiko.model.player.impl;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

/**
 * Implementation of the interface {@link Player}.
 */
public class PlayerImpl implements Player, Cloneable {

    private final GameColor color;
    private final Objective objective;
    private final Set<Territory> territories;
    private final Set<Ocean> oceans;
    private final Set<Territory> totalTerritories = new TerritoryFactoryImpl().createTerritories();
    private static final Pair<String, Integer> NORD_AMERICA = new Pair<>("Nord America", 3);
    private static final Pair<String, Integer> GONDWANA_OCCIDENTALE = new Pair<>("Gondwana Occidentale", 5);
    private static final Pair<String, Integer> GONDWANA_ORIENTALE = new Pair<>("Gondwana Orientale", 3);
    private static final Pair<String, Integer> EUROASIA = new Pair<>("Eurasia", 6);

    /**
     * Constructor for the player.
     * 
     * @param color       player's color
     * @param objective   player's objective
     * @param territories player's owned territories
     * @param oceans      player's owned oceans
     */
    public PlayerImpl(final GameColor color,
            final Objective objective,
            final Set<Territory> territories,
            final Set<Ocean> oceans) {
        this.color = color;
        Objects.requireNonNull(objective);
        this.objective = objective.getClone();
        this.territories = new HashSet<>(Objects.requireNonNull(territories));
        this.oceans = new HashSet<>(Objects.requireNonNull(oceans));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameColor getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective getObjective() {
        return objective.getClone();
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
    public int getBonusGroundDino() {
        int result = 0;
        result += isSubSetTerritory(
                new Pair<Set<Territory>, Integer>(getContinent(NORD_AMERICA.x()), NORD_AMERICA.y()));
        result += isSubSetTerritory(
                new Pair<Set<Territory>, Integer>(getContinent(GONDWANA_OCCIDENTALE.x()), GONDWANA_OCCIDENTALE.y()));
        result += isSubSetTerritory(
                new Pair<Set<Territory>, Integer>(getContinent(GONDWANA_ORIENTALE.x()), GONDWANA_ORIENTALE.y()));
        result += isSubSetTerritory(
                new Pair<Set<Territory>, Integer>(getContinent(EUROASIA.x()), EUROASIA.y()));
        return (territories.size() / 2) + result;
    }

    private int isSubSetTerritory(final Pair<Set<Territory>, Integer> set) {
        int result = 0;
        final Set<String> temp = territories.stream().map(t -> t.getName().toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());
        if (temp.containsAll(set.x().stream()
                .map(t -> t.getName().toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet()))) {
            result = result + set.y();
        }
        return result;
    }

    private Set<Territory> getContinent(final String name) {
        return totalTerritories.stream()
                .filter(e -> e.getContinent().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBonusWaterDino() {
        return 1;
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
