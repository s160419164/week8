package com.ubaya.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todo.R
import com.ubaya.todo.databinding.FragmentEditTodoBinding
import com.ubaya.todo.model.Todo
import com.ubaya.todo.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(), TodoSaveChangesClick,RadioClick {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.radioListener = this
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        /*btnAdd.setOnClickListener {
            var radioGroup = view.findViewById<RadioButton>(R.id.radioGroupPriority)
            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }*/

    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            /*txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
            when (it.priority) {
                1 -> radioLow.isChecked = true
                2 -> radioMedium.isChecked = true
                else -> radioHigh.isChecked = true
            }*/
        })
    }
    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}