package br.com.fiap.helloworld

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // Craindo um objeto de texto
        val texto = TextView(this)
        texto.text = "Hello Kotlin"

        //Definindo o conteúdo da tela
        setContentView(texto)
    }
}