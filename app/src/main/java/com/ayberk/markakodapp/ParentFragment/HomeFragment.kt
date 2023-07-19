package com.ayberk.markakodapp.ParentFragment

import ImageAdapter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ayberk.markakodapp.Adapter.DataAdapter
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.ViewModel.DataViewModel
import com.ayberk.markakodapp.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DataViewModel by viewModels()
    private lateinit var adapterr: DataAdapter
    private lateinit var adapterrr: ImageAdapter
    private var isBackPressed = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        val imageList = listOf(
            R.drawable.development,
            R.drawable.developer,
            R.drawable.software,
            R.drawable.smartphone
        )
        val nameList = listOf(
            "Telefon",
            "Bilgisayar",
            "Televizyon",
            "Tablet"
        )
        val prices = listOf(
            "5.000 TL",
            "12.000 TL",
            "14.000 TL",
            "7.000 TL"
        )

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val adapter = ImageAdapter(requireContext(), imageList, nameList, prices)
        recyclerView.adapter = adapter
        initRecycler()
        fetchData()

        viewModel.getDataLiveData().observe(viewLifecycleOwner, Observer { t ->
            t?.let {
                adapterr.setList(listOf(t))
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }

        val requestOptions = RequestOptions()
            .centerCrop() // Ölçekleme tipi

        val imageUrlList = listOf(
            "https://images.unsplash.com/photo-1621318164984-b06589834c91?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
        )

        val titleList = listOf(
            "Bitkiler",
            "Manzara",
            "Doğa",
            "Uçak"

        )
        val imageList = ArrayList<SlideModel>()

        for (i in imageUrlList.indices) {
            val imageUrl = imageUrlList[i]
            val title = titleList[i]

            Glide.with(requireContext())
                .load(imageUrl)
                .apply(requestOptions)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        val slideModel = SlideModel(imageUrl, title, ScaleTypes.FIT)
                        imageList.add(slideModel)
                        if (imageList.size == imageUrlList.size) {
                            binding.imgSlider.setImageList(imageList, ScaleTypes.FIT)
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }
    }

    private fun initRecycler() {

        adapterr = DataAdapter()
        binding.rcylerbaseData.adapter = adapterr
        val layoutManager = LinearLayoutManager(context)
        binding.rcylerbaseData.layoutManager = layoutManager

    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.Main).async {
            viewModel.loadData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}