package com.ecemsudeozan.sisterslabbitirmeprojesi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ecemsudeozan.sisterslabbitirmeprojesi.databinding.AnasayfaCardViewBinding
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.Recipe
import com.ecemsudeozan.sisterslabbitirmeprojesi.view.anasayfa.AnasayfaFragmentDirections

class YemekTarifiAdapter() :
    RecyclerView.Adapter<YemekTarifiAdapter.YemekTarifiViewHolder>() {

    private val yemekTarifiList = ArrayList<Recipe>()

    inner class YemekTarifiViewHolder(private val binding: AnasayfaCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tarif: Recipe) {
            with(binding) {
                tarifBaslik.text = tarif.name
                itemView.setOnClickListener {
                    val action =
                        AnasayfaFragmentDirections.actionAnasayfaFragmentToDetayFragment(tarif.id)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekTarifiViewHolder {
        val binding =
            AnasayfaCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YemekTarifiViewHolder(binding)
    }

    override fun getItemCount(): Int = yemekTarifiList.size

    override fun onBindViewHolder(holder: YemekTarifiViewHolder, position: Int) {
        val tarif = yemekTarifiList[position]
        holder.bind(tarif)
    }

    fun yemekListesiGuncelle(guncelYemekTarifListesi: List<Recipe>) {
        yemekTarifiList.clear()
        yemekTarifiList.addAll(guncelYemekTarifListesi)
        notifyDataSetChanged()
    }
}