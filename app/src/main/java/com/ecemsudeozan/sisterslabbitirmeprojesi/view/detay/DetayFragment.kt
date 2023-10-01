package com.ecemsudeozan.sisterslabbitirmeprojesi.view.detay

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecemsudeozan.sisterslabbitirmeprojesi.databinding.FragmentDetayBinding
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.RecipeX
import com.google.android.material.snackbar.Snackbar

class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private val viewModel: DetayViewModel by viewModels()
    private val args: DetayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Gelen Id", args.id.toString())

        binding.detayEkranToolbar.geriImageView.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.guncelleButton.setOnClickListener {
            val baslik = binding.DetayEkraniTarifAdi.text.toString()
            val detayi = binding.detayEkranYemekTarifi.text.toString()

            //apiye yollama
            viewModel.guncelleme(RecipeX(detayi, args.id, baslik))

            findNavController().popBackStack()
        }

        viewModel.detaylar(args.id)
        observe()
    }

    private fun observe() {
        viewModel.detaylarLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.DetayEkraniTarifAdi.setText(it.name)
                binding.detayEkranYemekTarifi.setText(it.description)

            } else {
                Snackbar.make(requireView(), "Liste boş geldi.", Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.guncellemeLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                Snackbar.make(requireView(), "Güncelleme başarılı", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(requireView(), "Güncelleme başarısız", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}