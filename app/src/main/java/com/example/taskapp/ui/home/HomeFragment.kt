package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_menu) {

            val items = arrayOf("Дате", "A-z", "z-A")

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Sort by")
            builder.setItems(items) { dialog, which ->
                when (which) {
                    0 -> {
                        adapter.addTask(App.database.taskDao()?.getAllTasksByDate())
                        dialog.dismiss()
                    }
                    1 -> {
                        adapter.addTask(App.database.taskDao()?.getAllTasksByAlphabetAz())
                        dialog.dismiss()
                    }
                    2 -> {
                        adapter.addTask(App.database.taskDao()?.getAllTasksByAlphabetZa())
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
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.newTaskFragment)
        }
    }
    private fun initViews() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = adapter

//        setFragmentResultListener("new_task") { key, bundle ->
//                val title = bundle.getString("title")
//                val desc = bundle.getString("desc")
//                Log.e("ololo", "initViews: $title и $desc")
//
//            adapter.addTask(TaskModel(title, desc))
//
//            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClickListener)
    }

    private fun onLongClickListener(pos: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удаление")
        builder.setMessage("Вы точно хотите удалить запись?")

        builder.setPositiveButton("Да") { dialog, which ->
            App.database.taskDao()?.delete(adapter.getTask(pos))
            setData()
        }

        builder.setNegativeButton("Нет") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

        fun setData() {
            val listOfTasks = App.database.taskDao()?.getAllTasks()
            adapter.addTasks(listOfTasks as List<TaskModel>)

        }
}