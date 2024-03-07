package blackjack.controller

import blackjack.model.participant.Dealer
import blackjack.model.deck.Deck
import blackjack.model.participant.Player
import blackjack.view.IsAddCardInputView
import blackjack.view.OutputView
import blackjack.view.PlayersInputView

class BlackjackController(
    private val playersInputView: PlayersInputView = PlayersInputView(),
    private val isAddCardInputView: IsAddCardInputView = IsAddCardInputView(),
    private val outputView: OutputView = OutputView(),
) {
    private val deck = Deck()

    fun play() {
        val dealer = Dealer(deck)
        val players = playersInputView.readPlayerNames(deck)
        outputView.printInitCard(dealer, players)

        players.gamePlayers.forEach { player ->
            playerTurn(player)
        }

        dealerTurn(dealer)

        outputView.printCardResult(dealer, players)
        outputView.printGameResult(dealer.gameResult(players.gamePlayers))
    }

    private tailrec fun playerTurn(player: Player) {
        if (player.isBust()) {
            outputView.printBustMessage()
            return
        }
        if (player.addCard(isAddCardInputView.readIsAddCard(player.name))) {
            outputView.printPlayerCard(player)
            playerTurn(player)
        }
    }

    private tailrec fun dealerTurn(dealer: Dealer) {
        if (dealer.addCard()) {
            outputView.printDealerAddCard()
            dealerTurn(dealer)
        }
    }
}
