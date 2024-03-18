package blackjack.state

import blackjack.model.participant.Player

class Hit(private val player: Player) : State {
    override fun decisionState(): State {
        player.addCard()
        return if (player.isBust()) Bust(player) else Running(player)
    }
}