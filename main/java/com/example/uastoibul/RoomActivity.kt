package com.example.uastoibul

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RemoteViews.RemoteCollectionItems
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uastoibul.databinding.ActivityMainBinding
import com.example.uastoibul.db.NoteRoomDatabase
import com.example.uastoibul.model.Note
import com.example.uastoibul.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Cek apakah data sudah dimasukkan sebelumnya
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDataInserted = sharedPreferences.getBoolean("isDataInserted", false)

        if (!isDataInserted) {
            // Jika belum dimasukkan, maka masukkan data dan simpan status ke shared preferences
            addDummyData()
            sharedPreferences.edit().putBoolean("isDataInserted", true).apply()
        }

        // Menampilkan data dari database ke dalam RecyclerView
        getNotesDate()

        binding.floatingAdd.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    private fun addDummyData() {
        val database = NoteRoomDatabase.getDatabase(application)
        val dao = database.getNoteDao()

        // Membuat 50 data dan memasukkannya ke dalam database
        // Data dummy
        val dummyDataList = listOf(
            Note(
                title = "2169700028",
                body = "Firman Tegar",
                nilai = "100",
                keterangan = "Lulus",
                jumlahsks = "21",
                hargasks = "20000"
            ),
            Note(
                title = "2169700012",
                body = "Ratu Nurul Fauziah",
                nilai = "100",
                keterangan = "Lulus",
                jumlahsks = "21",
                hargasks = "20000"
            ),
            Note(
                title = "2169700018",
                body = "Meli Ai Hayati Rahmah",
                nilai = "100",
                keterangan = "Lulus",
                jumlahsks = "21",
                hargasks = "20000"
            ),
            Note(
                title = "2169700024",
                body = "Harys Hakim Kurniawan",
                nilai = "100",
                keterangan = "Lulus",
                jumlahsks = "21",
                hargasks = "20000"
            ),
            Note(
                title = "2169700022",
                body = "Toibul Khoiri",
                nilai = "100",
                keterangan = "Lulus",
                jumlahsks = "21",
                hargasks = "20000"
            ),
        )

        // Menambahkan data ke dalam database
        for (dummyNote in dummyDataList) {
            dao.insert(dummyNote)
        }
    }


    private fun getNotesDate() {
        val database = NoteRoomDatabase.getDatabase(application)
        val dao = database.getNoteDao()
        val listItems = arrayListOf<Note>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
    }

    private fun setupRecyclerView(listItems: ArrayList<Note>) {
        binding.recycleViewMain.apply {
            adapter = NoteAdapter(listItems, object : NoteAdapter.NoteListener {
                override fun OnItemClicked(note: Note) {
                    val intent = Intent(this@RoomActivity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_NOTE_EXTRA, note)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@RoomActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getNotesDate()
    }
}