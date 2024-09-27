package zilldy.com.github.cryptomonitor

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import zilldy.com.github.cryptomonitor.state.ScreenState
import zilldy.com.github.cryptomonitor.viewmodel.CryptoViewModel
import zilldy.com.github.cryptomonitor.viewmodel.CryptoViewModelFactory
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val viewModel: CryptoViewModel by viewModels { CryptoViewModelFactory() }
    private val textViewBitcoin: TextView by lazy { findViewById(R.id.textViewBitcoin) }
    private val textViewDate: TextView by lazy { findViewById(R.id.textViewDate) }
    private val buttonRefresh: Button by lazy { findViewById(R.id.buttonRefresh) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encontra a barra de ferramentas pelo seu ID e a define como a barra de ação para esta activity.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Define o título da barra de ação.
        supportActionBar?.title = "Lista de Compras"


        viewModel.tickerLiveData.observe(this) { state: ScreenState ->
            when (state) {
                is ScreenState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    buttonRefresh.visibility = View.GONE
                }

                is ScreenState.Success -> {
                    progressBar.visibility = View.GONE
                    buttonRefresh.visibility = View.VISIBLE
                    textViewBitcoin.text =
                        NumberFormat.getCurrencyInstance(Locale("pt", "BR")).let {
                            it.format(state.data.last.toBigDecimal())
                        }
                    textViewDate.text = SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date())
                }

                is ScreenState.Error -> {
                    progressBar.visibility = View.GONE
                    buttonRefresh.visibility = View.VISIBLE
                    Toast.makeText(
                        this, "Ocorreu um erro", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        buttonRefresh.setOnClickListener {
            viewModel.refresh()
        }
    }
}