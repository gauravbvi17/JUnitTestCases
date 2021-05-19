package com.example.android.codelabs.sunandsandsports.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codelabs.sunandsandsports.R
import com.example.android.codelabs.sunandsandsports.adapter.UsersAdapter
import com.example.android.codelabs.sunandsandsports.databinding.FragmentRandomUserListBinding
import com.example.android.codelabs.sunandsandsports.listener.ItemClickListener
import com.example.android.codelabs.sunandsandsports.model.UserResult
import com.example.android.codelabs.sunandsandsports.utils.RESULT_PER_PAGE
import com.example.android.codelabs.sunandsandsports.viewmodels.UserViewModel


open class FragmentRandomUserList : Fragment(), ItemClickListener {

    private var isScrolling: Boolean=false
    private lateinit var binding: FragmentRandomUserListBinding
    private lateinit var usersAdapter: UsersAdapter
    private val viewModel:UserViewModel by viewModels()
     lateinit var linearLayoutManager: GridLayoutManager
    private var userList:ArrayList<UserResult> = ArrayList()
    private var scrollOutItems: Int=0
    private var totalItems: Int=0
    private var currentItems: Int=0
    private var offset:Int=1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=DataBindingUtil.inflate(inflater, R.layout.fragment_random_user_list,container,false)
       return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        callUsersApi()
    }

    private fun callUsersApi() {
        viewModel.getUsers(offset, RESULT_PER_PAGE)
    }

    private fun setObservers() {

        viewModel.user.observe(this, Observer {
         //   Log.d(TAG, "setObservers: "+it.results?.get(0)?.email)
           userList.addAll( it.results as ArrayList)
            usersAdapter.updateList(userList)
        })

        viewModel.loading.observe(this, Observer { isDisplay->
            if(isDisplay)
            {
                binding.progressBar.visibility=View.VISIBLE
            }
            else
            {
                binding.progressBar.visibility=View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? MainActivity)?.changeToolbar("",true)
    }



    private fun initializeViews() {

        linearLayoutManager = GridLayoutManager(activity, 2)
        usersAdapter = UsersAdapter(
            this.requireContext(),
            userList,this@FragmentRandomUserList
        )
        binding.recyclerViewUsers.layoutManager = linearLayoutManager
        binding.recyclerViewUsers.adapter = usersAdapter


        binding.recyclerViewUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    currentItems = linearLayoutManager.getChildCount()
                    totalItems = linearLayoutManager.getItemCount()
                    scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition()
                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false
                        offset += 1
                        viewModel.getUsers(offset, RESULT_PER_PAGE)
                    }

                }

            }
        })

    }

    override fun passData(position: Int, data: UserResult) {
       /* val action=FragmentRandomUserListDirections.actionFragmentRandomUserListToFragmentUserDetail(data)
        NavHostFragment.findNavController(this).navigate(action)*/

        val navBuilder:NavOptions = NavOptions.Builder().setEnterAnim(R.anim.pull_in_right).setExitAnim(R.anim.push_out_left)
            .setPopEnterAnim(R.anim.pull_in_left).setPopExitAnim(R.anim.push_out_right).build()


        val bundle = bundleOf("myUser" to data)
        NavHostFragment.findNavController(this).navigate(R.id.fragmentUserDetail, bundle,navBuilder)


    }

}