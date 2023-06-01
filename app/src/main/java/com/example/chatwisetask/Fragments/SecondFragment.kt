package com.example.chatwisetask.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwisetask.API.Resource
import com.example.chatwisetask.Activities.MainActivity
import com.example.chatwisetask.Adapters.ImageAdapter
import com.example.chatwisetask.R
import com.example.chatwisetask.UI.ImageViewModel
import com.example.chatwisetask.databinding.FragmentSecondBinding
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSecondBinding? = null
    private val binding get()=_binding!!
    private lateinit var viewModel: ImageViewModel
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            viewModel=(activity as MainActivity).viewModel
            _binding= FragmentSecondBinding.inflate(inflater, container, false)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        showProgressBar()
        viewModel.images.observe(viewLifecycleOwner, Observer{
            response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let{
                        adapter.differ.submitList(it)
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let{
                        Toast.makeText(requireContext(), "An Error Occured, error: ${it}", Toast.LENGTH_SHORT).show()
                        Log.w("check", " Error: ${it}")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        binding.imageRcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    binding.imageTv.visibility=View.GONE
                } else {
                    binding.imageTv.visibility=View.VISIBLE
                }
            }
        })
    }

    private fun showProgressBar(){
        binding.progressBar.visibility=View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility=View.GONE
    }

    private fun setUpRecyclerView(){
        adapter= ImageAdapter()
        binding.imageRcv.adapter=adapter
        binding.imageRcv.layoutManager=LinearLayoutManager(requireContext())
    }
}