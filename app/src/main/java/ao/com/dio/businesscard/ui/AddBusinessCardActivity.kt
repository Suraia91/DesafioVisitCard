package ao.com.dio.businesscard.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import ao.com.dio.businesscard.App
import ao.com.dio.businesscard.R
import ao.com.dio.businesscard.data.BusinessCard
import ao.com.dio.businesscard.data.Colors
import ao.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {
    var cor: String = ""
    private val list = ArrayList<Colors>()


    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListener()
        getDataSpinner(this)
    }

    private fun insertListener() {
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.btnConfirm.setOnClickListener {

            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                tel = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                fundoPersonalizado = cor
            )
            if (binding.tilNome.editText?.text.toString().isEmpty()||binding.tilEmpresa.editText?.text.toString().isEmpty()||
                binding.tilEmail.editText?.text.toString().isEmpty()||binding.tilEmpresa.editText?.text.toString().isEmpty()){
                Toast.makeText(this, "Dados nulo", Toast.LENGTH_SHORT).show()
            }else{
                mainViewModel.insert(businessCard)
                Toast.makeText(this, "Cartao adicionado com Sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    private fun getDataSpinner(context: Context){
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, list)
        list.add(Colors(cor = "Amarelo", cod = "#FFEB3B"))
        list.add(Colors(cor = "Laranja", cod = "#FF5722"))
        list.add(Colors(cor = "Preto", cod = "#FF000000"))
        list.add(Colors(cor = "Roxo", cod = "#FF3700B3"))
        list.add(Colors(cor = "Verde", cod = "#4CAF50"))
        list.add(Colors(cor = "Vermelho", cod = "#F44336"))

        binding.spColor.adapter= adapter
        binding.spColor.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                cor = (parent?.selectedItem as Colors).cod
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

}