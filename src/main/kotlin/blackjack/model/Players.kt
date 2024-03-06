package blackjack.model

class Players(private val names: Set<String>, deck: Deck = Deck()) {
    private val players: List<Player> = names.map { Player(it, PlayerCards(deck)) }

    init {
        require(names.size in 1..6) { "1명 이상 6명 이하의 플레이어만 가능합니다." }
        require(names.size == names.toSet().size) { "중복된 이름은 플레이어로 등록할 수 없습니다." }
    }

    companion object {
        fun playerNamesOf(names: List<String>): Players {
            validateDuplicateNames(names)
            return Players(names.toSet())
        }

        private fun validateDuplicateNames(numbers: List<String>) {
            require(numbers.distinct().size == numbers.size) {
                "중복된 이름은 플레이어로 등록할 수 없습니다."
            }
        }
    }
}