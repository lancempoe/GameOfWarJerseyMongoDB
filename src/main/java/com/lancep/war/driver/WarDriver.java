package com.lancep.war.driver;

import com.lancep.war.client.WarResults;
import com.lancep.war.orm.Card;
import com.lancep.war.orm.WarDeck;
import com.lancep.controller.errorhandling.WarException;
import com.lancep.war.assembler.WarAssembler;
import com.lancep.war.orm.War;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class WarDriver {

    private static final Logger logger = Logger.getLogger(WarDriver.class.getName());
    public static final int I_DECLARE_WAR_COUNT = 3;
    public static final int MAX_MOVE_COUNT = 20000;

    /**
     * @warning This will play through an entire game of war. As with the game of war,
     * be careful what params you pass in because this could be a never ending game.
     *
     * @return results of game of war
     */
    public WarResults play(int numberOfSuits, int numberOfRanks) {
        War war = WarAssembler.createWar(numberOfSuits, numberOfRanks);
        while(gameIsNotOver(war)) {
            draw(war);
        }

        if (gameEndsWithTie(war)) {
            logger.info("There are no winners in this war, only losers");
        } else {
            logger.info(String.format("Victory! Final Score: Player1 %s cards, Player 2 %s cards. %d cards drawn",
                    war.getPlayer1Deck().getSize(),
                    war.getPlayer2Deck().getSize(),
                    war.getTotalMoveCount()));
        }

        WarResults results = new WarResults();
        return getWarResults(war, results);
    }

    /**
     * Take 1 draw from each player until a player wins card(s) and return results
     */
    public WarResults draw(War war) {
        if (isNotValidWar(war)) {
            return null;
        }

        WarResults results = new WarResults();

        if (gameIsNotOver(war)) {
            draw(war, results);
        } else {
            logger.info(String.format(
                    "Game Is Already Over. Final Score: Player1 %s cards, Player 2 %s cards. %d cards drawn",
                    war.getPlayer1Deck().getSize(),
                    war.getPlayer2Deck().getSize(),
                    war.getTotalMoveCount()));
        }

        return getWarResults(war, results);
    }

    private void draw(War war, WarResults results) {
        Card player1Card = war.getPlayer1Deck().deal();
        results.addPlayer1DrawnCard(player1Card);

        Card player2Card = war.getPlayer2Deck().deal();
        results.addPlayer2DrawnCard(player2Card);

        boolean bothDealtACard = player1Card != null & player2Card != null;
        boolean shouldDeclareWar = bothDealtACard && player1Card.getRank() == player2Card.getRank();

        if (bothDealtACard) {
            incrementTotalMoveCount(war);
            if (shouldDeclareWar) {
                declareWar(war, results);
            }
        }

        if (!gameEndsWithTie(war)) {
            addDrawnCardsToWinnersDeck(war, results);
        }
    }

    private boolean gameIsNotOver(War war) {
        if (war.getTotalMoveCount() > MAX_MOVE_COUNT) {
            throw new WarException(Response.Status.REQUEST_TIMEOUT, "Game of war took took long. Most likely cause was a never ending loop of war.");
        }
        return war.getPlayer1Deck().getSize() > 0 && war.getPlayer2Deck().getSize() > 0;
    }

    private void addDrawnCardsToWinnersDeck(War war, WarResults results) {
        List<Card> winningPot = getWinningPot(results);
        WarDeck winnersDeck = applyPotToWinnerOfDrawDeck(war, results, winningPot.size());
        winnersDeck.addCards(winningPot);
    }

    private boolean gameEndsWithTie(War war) {
        return war.getPlayer1Deck().getSize() == 0 && war.getPlayer2Deck().getSize() == 0;
    }

    private WarDeck applyPotToWinnerOfDrawDeck(War war, WarResults results, int numberOfCards) {
        boolean player1wins;
        if (bothDealtSameNumberOfCards(results)) {
            Card lastPlayer1DrawnCard = results.getPlayer1DrawnCards().peekLast();
            Card lastPlayer2DrawnCard = results.getPlayer2DrawnCards().peekLast();
            boolean player1HasBetterCard = lastPlayer1DrawnCard.getRank() > lastPlayer2DrawnCard.getRank();
            player1wins = player1HasBetterCard;

        } else {
            boolean player1RanOutOfCards = results.getPlayer1DrawnCards().size() < results.getPlayer2DrawnCards().size();

            player1wins = player1RanOutOfCards;
        }

        if (player1wins) {
            logger.info(String.format("Player 1 wins %d cards", numberOfCards));
            return war.getPlayer1Deck();
        } else {
            logger.info(String.format("Player 2 wins %d cards", numberOfCards));
            return war.getPlayer2Deck();
        }
    }

    private List<Card> getWinningPot(WarResults results) {
        List <Card> pot = new ArrayList<>();
        pot.addAll(results.getPlayer1DrawnCards());
        pot.addAll(results.getPlayer2DrawnCards());
        return pot;
    }

    private void declareWar(War war, WarResults results) {
        logger.info("War is declared!");

        Stream.generate(() -> war.getPlayer1Deck().deal())
                .limit(I_DECLARE_WAR_COUNT)
                .forEach(results::addPlayer1DrawnCard);

        Stream.generate(() -> war.getPlayer2Deck().deal())
                .limit(I_DECLARE_WAR_COUNT)
                .forEach(results::addPlayer2DrawnCard);

        if (gameIsNotOver(war) && shouldDeclareWar(results)) {
            declareWar(war, results);
        }
    }

    private boolean shouldDeclareWar(WarResults results) {
        Card player1Card = results.getPlayer1DrawnCards().peekLast();
        Card player2Card = results.getPlayer2DrawnCards().peekLast();

        boolean bothHaveDealt = player1Card != null & player2Card != null;
        boolean shouldDeclareWar = bothHaveDealt && player1Card.getRank() == player2Card.getRank();

        return bothDealtSameNumberOfCards(results) && shouldDeclareWar;
    }

    private boolean bothDealtSameNumberOfCards(WarResults results) {
        return results.getPlayer1DrawnCards().size() == results.getPlayer2DrawnCards().size();
    }

    private WarResults getWarResults(War war, WarResults results) {
        results.setId(war.getId());
        results.setTotalMoveCount(war.getTotalMoveCount());
        results.setPlayer1DeckSize(war.getPlayer1Deck().getSize());
        results.setPlayer2DeckSize(war.getPlayer2Deck().getSize());
        return results;
    }

    private void incrementTotalMoveCount(War war) {
        int priorTotalMoveCount = war.getTotalMoveCount();
        int totalMoveCount = priorTotalMoveCount + 1;
        war.setTotalMoveCount(totalMoveCount);
    }

    private boolean isNotValidWar(War war) {
        return war == null || war.getPlayer1Deck() == null || war.getPlayer2Deck() == null;
    }
}
