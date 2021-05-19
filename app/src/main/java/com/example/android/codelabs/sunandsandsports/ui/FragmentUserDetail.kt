package com.example.android.codelabs.sunandsandsports.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.android.codelabs.sunandsandsports.R
import com.example.android.codelabs.sunandsandsports.databinding.FragmentUserDetailBinding
import com.example.android.codelabs.sunandsandsports.model.UserResult
import com.example.android.codelabs.sunandsandsports.utils.API_DATE_FORMAT
import com.example.android.codelabs.sunandsandsports.utils.LOCAL_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

class FragmentUserDetail : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         binding=DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? MainActivity)?.changeToolbar("",false)
    }

    private fun initializeViews() {

      setData()
        binding.imgProfile.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setData() {
        val myUser:UserResult? =arguments?.getParcelable<UserResult>("myUser")
        myUser.let {
            it?.apply {
                name?.let {
                    it.apply {
                        binding.txtName.text="${title} ${first} ${last}"
                    }
                }
                binding.txtGender.text=gender.toString()
                binding.txtEmail.text=email.toString()
                binding.txtPhone.text=phone.toString()
                binding.txtCellNo.text=cell.toString()

                dob?.let {
                    it.apply {
                        //1945-07-16T22:57:08.337Z

                        date?.let {
                            val apiFormat = SimpleDateFormat(
                                API_DATE_FORMAT, Locale.US)
                            val apiDate:Date=apiFormat.parse(it)
                            val displayDateFormat=SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.US)
                            binding.txtDob.text=displayDateFormat.format(apiDate)
                            binding.txtAge.text=age.toString()
                        }
                        picture?.let {
                            Glide.with(this@FragmentUserDetail).load(it.large).placeholder(R.drawable.ic_placeholder).
                            error(R.drawable.ic_placeholder).into(binding.imgProfile)
                        }
                    }
                }

            }
        }
    }
}