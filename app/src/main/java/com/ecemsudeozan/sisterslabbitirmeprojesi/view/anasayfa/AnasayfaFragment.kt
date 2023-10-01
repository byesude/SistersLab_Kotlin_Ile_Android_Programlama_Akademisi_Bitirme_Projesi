package com.ecemsudeozan.sisterslabbitirmeprojesi.view.anasayfa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecemsudeozan.sisterslabbitirmeprojesi.R
import com.ecemsudeozan.sisterslabbitirmeprojesi.adapter.YemekTarifiAdapter
import com.ecemsudeozan.sisterslabbitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.google.android.material.snackbar.Snackbar

class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener{
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var adapter: YemekTarifiAdapter
    private val viewModel : AnasayfaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarAnasayfa.title = "  Anasayfa"
        binding.toolbarAnasayfa.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbarAnasayfa.setLogo(R.drawable.anasayfaicon)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.actionAra)
                val searchView = item.actionView as SearchView

                searchView.setOnQueryTextListener(this@AnasayfaFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_anasayfaFragment_to_ekleFragment)
        }

        binding.anasayfaRV.setHasFixedSize(true)
        binding.anasayfaRV.layoutManager = LinearLayoutManager(requireContext())

        adapter=YemekTarifiAdapter()
        binding.anasayfaRV.adapter = adapter
        viewModel.tumTarifler()
        observe()

    }

    private fun observe (){
        viewModel.tumTariflerLiveData.observe(viewLifecycleOwner){
            if (it != null){
                adapter.yemekListesiGuncelle(it)
            }
            else{
                Snackbar.make(requireView(), "Liste boş geldi.",Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.aramaLiveData.observe(viewLifecycleOwner){
            if(it != null){
                adapter.yemekListesiGuncelle(it)
            }else {
                Snackbar.make(requireView(), "Liste boş geldi.",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.arama(query)

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.arama(newText)

        return true
    }
}