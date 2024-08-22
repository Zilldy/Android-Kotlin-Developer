package zilldy.com.github.litoranea

class PraiaModel (
    val nomePraia: String,
    val cidade: String,
    val estado: String,
    val onRemove: (PraiaModel) -> Unit
)
