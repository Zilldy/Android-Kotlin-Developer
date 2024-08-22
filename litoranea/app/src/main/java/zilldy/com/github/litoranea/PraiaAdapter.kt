package zilldy.com.github.litoranea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PraiaAdapter {
    class PraiasAdapter : RecyclerView.Adapter<PraiasAdapter.PraiaViewHolder>() {

        private val praias = mutableListOf<PraiaModel>()

        fun addPraia(newPraia: PraiaModel) {
            praias.add(newPraia)
            notifyDataSetChanged()
        }

        fun removePraia(praia: PraiaModel) {
            praias.remove(praia)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PraiaViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.praia_layout, parent, false)

            return PraiaViewHolder(view)
        }

        override fun getItemCount(): Int = praias.size

        override fun onBindViewHolder(holder: PraiaViewHolder, position: Int) {
            val praia = praias[position]
            holder.bind(praia)
        }

        class PraiaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nomePraia = view.findViewById<TextView>(R.id.nomePraia)
            val button = view.findViewById<ImageButton>(R.id.imageButton)
            val cidade = view.findViewById<TextView>(R.id.cidade)
            val estado = view.findViewById<TextView>(R.id.estado)

            fun bind(praia: PraiaModel) {
                nomePraia.text = praia.nomePraia
                cidade.text = praia.cidade
                estado.text = praia.estado

                button.setOnClickListener {
                    praia.onRemove(praia)
                }

            }
        }
    }
}