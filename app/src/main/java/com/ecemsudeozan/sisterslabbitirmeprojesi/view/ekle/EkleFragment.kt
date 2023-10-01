package com.ecemsudeozan.sisterslabbitirmeprojesi.view.ekle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecemsudeozan.sisterslabbitirmeprojesi.databinding.FragmentEkleBinding
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.EkleIstek
import com.google.android.material.snackbar.Snackbar

class EkleFragment : Fragment() {
    private lateinit var binding: FragmentEkleBinding
    private val viewModel: EkleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEkleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ekleEkranToolbar.geriImageView.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ekleButton.setOnClickListener {
            val baslik = binding.tarifAdi.text.toString()
            val detayi = binding.yemekTarifi.text.toString()

            //apiye yollama
            viewModel.ekleme(EkleIstek(baslik, detayi))

            observe()
        }
    }

    fun observe() {
        viewModel.eklemeLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                Snackbar.make(requireView(), "Tarif başarıyla kaydedildi", Snackbar.LENGTH_SHORT)
                    .show()
                findNavController().popBackStack()
            } else {
                Snackbar.make(
                    requireView(),
                    "Tarifi eklerken bir hata oluştu",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
