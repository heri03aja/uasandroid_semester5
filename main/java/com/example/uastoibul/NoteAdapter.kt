package com.example.uastoibul

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.uastoibul.model.Note

class NoteAdapter(
    private val listItems: ArrayList<Note>,
    private val listener: NoteListener
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
        var textViewNilai = itemView.findViewById<TextView>(R.id.text_view_hargabrg)
        var textViewKeterangan = itemView.findViewById<TextView>(R.id.text_view_diskon)
        var textViewJumlahsks = itemView.findViewById<TextView>(R.id.text_view_hasil)
        var textViewHargasks = itemView.findViewById<TextView>(R.id.text_view_keterangan)
        var textViewTotal = itemView.findViewById<TextView>(R.id.text_view_total)
    }

    interface NoteListener{
        fun OnItemClicked(note: Note)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewTitle.text = "NPM: ${item.title}"
        holder.textViewBody.text = "Nama: ${item.body}"
        holder.textViewNilai.text = "Nilai: ${item.nilai}"
        holder.textViewKeterangan.text = "Keterangan: ${item.keterangan}"
        holder.textViewJumlahsks.text = "Jumlah SKS: ${item.jumlahsks}"
        holder.textViewHargasks.text = "Harga SKS: Rp. ${item.hargasks}"

        val jumlahsks = item.jumlahsks.toDoubleOrNull() ?: 0.0

        val hargasks = item.hargasks.toDoubleOrNull() ?: 0.0
        val result = jumlahsks * hargasks

        holder.textViewTotal.text = "Total: Rp. $result"

        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}