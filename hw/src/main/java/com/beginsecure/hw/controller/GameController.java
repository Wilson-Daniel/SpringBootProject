package com.beginsecure.hw.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private final Logger logger = LoggerFactory.getLogger(GameController.class);

    @GetMapping("/playTickTackToe")
    public String playGame(@RequestParam String playerMove) {
        try {
            String computerMove = generateRandomMove();
            String result = determineWinner(playerMove, computerMove);
            logger.info("Computer move: " + computerMove);
            logger.info("Player move: " + playerMove);
            logger.info("Result: " + result);
            return result;
        } catch (Exception e) {
            logger.info("An error occurred during the game: " + e.getMessage());
            return "Error occurred during the game";
        }
    }

    private String generateRandomMove() {
        try {
            String[] allMoves = {"Rock", "Paper", "Scissors"};
            int randomIndex = (int) (Math.random() * allMoves.length);
            return allMoves[randomIndex];
        } catch (Exception e) {
            logger.info("An error occurred while generating a random move: " + e.getMessage());
            throw e;
        }
    }

    private String determineWinner(String playerMove, String computerMove) {
        try {
            if (playerMove.equals(computerMove)) {
                return "It is a tie";
            } else if (
                    (playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                        (playerMove.equals("Scissors") && computerMove.equals("Paper")) ||
                            (playerMove.equals("Paper") && computerMove.equals("Rock"))
            ) {
                return "Player wins";
            } else {
                return "Computer wins";
            }
        } catch (Exception e) {
            logger.info("An error occurred while determining the winner: " + e.getMessage());
            throw e;
        }
    }
}
