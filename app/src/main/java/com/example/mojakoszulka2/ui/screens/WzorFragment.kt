package com.example.mojakoszulka2.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mojakoszulka2.R
import com.example.mojakoszulka2.viewmodel.OrderViewModel

class WzorFragment : Fragment() {
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                WzorScreen(
                    viewModel = viewModel,
                    onNavigateNext = {
                        findNavController().navigate(R.id.action_wzor_to_dane)
                    }
                )
            }
        }
    }
} 