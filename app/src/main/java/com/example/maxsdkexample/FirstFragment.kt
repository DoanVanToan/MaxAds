package com.example.maxsdkexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.max_ads.interf.InterstitialContentCallback
import com.example.max_ads.max.MaxInterstitialAdvertisement
import com.example.maxsdkexample.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val ads by lazy {
        MaxInterstitialAdvertisement("AD_UNIT")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ads.load(requireActivity(), null)

        binding.buttonFirst.setOnClickListener {
            if (ads.isLoaded) {
                ads.show(requireActivity(), object :
                    InterstitialContentCallback {
                    override fun onAdFailedToShowFullScreenContent(error: String?) {
                        openNextFragment()
                    }
                    override fun onAdShowedFullScreenContent() {
                        // Log last show time!
                    }
                    override fun onAdDismissedFullScreenContent() {
                        openNextFragment()
                    }

                })
            } else {
                openNextFragment()
            }
        }
    }

    private fun openNextFragment() {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}