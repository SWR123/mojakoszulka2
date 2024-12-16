package fm.mrc.mojakoszulka2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fm.mrc.mojakoszulka2.viewmodel.OrderViewModel

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
                    onNavigateNext = {
                        findNavController().navigate(
                            WzorFragmentDirections.actionWzorToDane()
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun WzorScreen(onNavigateNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Implementacja UI dla wyboru wzoru koszulki
        Button(
            onClick = onNavigateNext,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dalej")
        }
    }
} 