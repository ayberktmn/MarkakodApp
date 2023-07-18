package com.ayberk.markakodapp.ParentFragment

import FavoriteAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.ayberk.markakodapp.Models.RoomBase
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.Room.DataDao
import com.ayberk.markakodapp.Room.RoomDatabase
import com.ayberk.markakodapp.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {

    private lateinit var db: RoomDatabase
    private lateinit var adventDao: DataDao
    private lateinit var adapter: FavoriteAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(
            R.drawable.development,
            R.drawable.developer,
            R.drawable.software,
            R.drawable.smartphone
        )

        db = Room.databaseBuilder(
            requireContext().applicationContext,
            RoomDatabase::class.java, "RoomBase"
        )
            .allowMainThreadQueries()
            .build()
        adventDao = db.dataDao()

        val recyclerViewAdapter = FavoriteAdapter(adventDao.getAll() as ArrayList<RoomBase>, imageList)
        adapter = recyclerViewAdapter
        binding.rcylerFavorite.adapter = adapter
        binding.rcylerFavorite.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        updateEmptyState()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateEmptyState() {
        if (adapter.itemCount == 0) {
            binding.animationEmpty.playAnimation()
            binding.animationEmpty.visibility = View.VISIBLE
            Toast.makeText(requireContext(), "Favori Öğe Bulunamadı", Toast.LENGTH_SHORT).show()
        } else {
            binding.animationEmpty.cancelAnimation()
            binding.animationEmpty.visibility = View.INVISIBLE
        }
    }
}
