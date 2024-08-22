package zilldy.com.github.litoranea

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val praiaAdapter = PraiaAdapter()
        recyclerView.adapter = praiaAdapter

        val button = findViewById<Button>(R.id.imageButton)
        val editTextNome = findViewById<EditText>(R.id.nomePraia)
        val editTextCidade = findViewById<EditText>(R.id.cidade)
        val editTextEstado = findViewById<EditText>(R.id.estado)

        button.setOnClickListener {
            if (editTextNome.text.isEmpty() || editTextCidade.text.isEmpty() || editTextEstado.text.isEmpty()) {
                editTextNome.error = "Preencha um nome de praia"
                editTextCidade.error = "Preencha uma cidade"
                editTextEstado.error = "Preencha um estado"
                return@setOnClickListener
            }

            val praia = PraiaModel(
                nomePraia = editTextNome.text.toString(),
                cidade = editTextCidade.text.toString(),
                estado = editTextEstado.text.toString(),
                onRemove = {
                    praiaAdapter.removePraia(it)
                },
            )

            Log.d("Tag", "$praia")

            praiaAdapter.addPraia(praia)
            editTextNome.text.clear()
            editTextCidade.text.clear()
            editTextEstado.text.clear()
        }
    }
}
