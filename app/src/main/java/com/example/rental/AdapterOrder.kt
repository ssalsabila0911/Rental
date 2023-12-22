import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rental.OrderModel
import com.example.rental.R

class AdapterOrder(private val listOrder: List<OrderModel>) :
    RecyclerView.Adapter<AdapterOrder.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textIdRental: TextView
        val textNoPlat: TextView
        val textTanggal: TextView
        val textLama: TextView
        val textTotal: TextView
        val context = itemView.context

        init {
            textIdRental = itemView.findViewById(R.id.tvIdRental)
            textNoPlat = itemView.findViewById(R.id.tvNoplat)
            textTanggal = itemView.findViewById(R.id.tvTglSewa)
            textLama = itemView.findViewById(R.id.tvLamaSewa)
            textTotal = itemView.findViewById(R.id.tvTotalBayar)
        }

        fun bind(data: OrderModel) {
            val id: Int = data.id
            val noplat: String = data.noplat
            val tanggal: String = data.tanggal
            val lama: String = data.lama
            val total: Int = data.totalharga

            textIdRental.text = id.toString()
            textNoPlat.text = noplat
            textTanggal.text = tanggal
            textLama.text = lama
            textTotal.text = total.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOrder[position])
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }
}
