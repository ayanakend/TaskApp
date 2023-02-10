package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_menu) {

            val items = arrayOf("Дате", "A-z", "z-A")

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.sort_by))
            builder.setItems(items) { dialog, which ->
                when (which) {
                    0 -> {
                        adapter.addTasks(App.database.taskDao()?.getAllTasksByDate() as List<TaskModel>)
                        dialog.dismiss()
                    }
                    1 -> {
                        adapter.addTasks(App.database.taskDao()?.getAllTasksByAlphabetAz() as List<TaskModel>)
                        dialog.dismiss()
                    }
                    2 -> {
                        adapter.addTasks(App.database.taskDao()?.getAllTasksByAlphabetZa() as List<TaskModel>)
                        dialog.dismiss()
                    }
                }
            }
            builder.show()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        setData()
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.newTaskFragment)
        }
    }
    private fun initViews() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClickListener, this::onTaskClick)
    }

    private fun onLongClickListener(pos: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.delite))
        builder.setMessage(getString(R.string.sure_delite))

        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            App.database.taskDao()?.delete(adapter.getTask(pos))
            setData()
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    @Suppress("UNCHECKED_CAST")
    private fun setData() {
        val listOfTasks = App.database.taskDao()?.getAllTasks()
        adapter.addTasks(listOfTasks as List<TaskModel>)}

    private fun onTaskClick(taskModel: TaskModel) {
        findNavController().navigate(R.id.newTaskFragment, bundleOf("update-tasks" to taskModel))
    }

    private fun getDataFirestore(){}
}