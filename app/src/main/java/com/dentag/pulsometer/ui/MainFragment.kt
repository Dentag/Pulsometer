package com.dentag.pulsometer.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dentag.pulsometer.databinding.FragmentMainBinding
import com.dentag.pulsometer.ui.list.MeasuresAdapter
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var measuresAdapter: MeasuresAdapter? = null

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        initFAB()
    }

    private fun initFAB() {
        binding.mainFAB.setOnClickListener {
            mainViewModel.addMeasures()
        }
    }

    private fun initRV() {
        measuresAdapter = MeasuresAdapter()
        with(binding.mainRV) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = measuresAdapter
        }
        mainViewModel.measuresLiveData.observe(viewLifecycleOwner) {
            measuresAdapter?.data = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        measuresAdapter = null
    }
}