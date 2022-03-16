package ao.com.dio.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ao.com.dio.businesscard.data.BusinessCard
import ao.com.dio.businesscard.databinding.ItemBusinessBinding

class BusinessCardAdapter:ListAdapter<BusinessCard,BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare:(View)->Unit={}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val infater=LayoutInflater.from(parent.context)
        val binding=ItemBusinessBinding.inflate(infater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(
        private val binding: ItemBusinessBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(item:BusinessCard){
            binding.tvNome.text=item.nome
            binding.tvTelefone.text=item.tel
            binding.tvEmail.text=item.email
            binding.tvNomeEmpresa.text=item.empresa
            binding.cvContent.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.cvContent.setOnClickListener{
                listenerShare(it)
            }
        }
    }
}
class DiffCallback: DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard)=oldItem==newItem

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard)=oldItem.id==newItem.id

}