package com.example.moviesappv2.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesappv2.databinding.FragmentSearchFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SearchFilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchFilterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchFilterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}