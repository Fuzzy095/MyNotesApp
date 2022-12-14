package com.example.mynotesapp.UI.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.R
import com.example.mynotesapp.ViewModel.NotesViewModel
import com.example.mynotesapp.databinding.FragmentCreateNoteBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Suppress("DEPRECATION")
class FragmentCreateNote : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var color: String = "yellow" //default color of a note set to yellow
    val viewModel: NotesViewModel by viewModels()

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        binding.yellowDotCreate.setImageResource(R.drawable.transparent_circle) //default option chosen is yellow

        //I have set the outline of the color option selected to a transparent circle that has a black border

        //setting the background color of the note to yellow when the yellow dot is clicked
        binding.yellowDotCreate.setOnClickListener{
            color = "yellow"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_yellow)
            binding.yellowDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.pinkDotCreate.setImageResource(R.drawable.pink_dot)
            binding.greenDotCreate.setImageResource(R.drawable.green_dot)
            binding.blueDotCreate.setImageResource(R.drawable.blue_dot)
        }

        //setting the background color of the note to pink when the pink dot is clicked
        binding.pinkDotCreate.setOnClickListener{
            color = "pink"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_pink)
            binding.pinkDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotCreate.setImageResource(R.drawable.yellow_dot)
            binding.greenDotCreate.setImageResource(R.drawable.green_dot)
            binding.blueDotCreate.setImageResource(R.drawable.blue_dot)
        }

        //setting the background color of the note to green when the green dot is clicked
        binding.greenDotCreate.setOnClickListener{
            color = "green"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_green)
            binding.greenDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotCreate.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotCreate.setImageResource(R.drawable.pink_dot)
            binding.blueDotCreate.setImageResource(R.drawable.blue_dot)
        }

        //setting the background color of the note to blue when the blue dot is clicked
        binding.blueDotCreate.setOnClickListener{
            color = "blue"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_blue)
            binding.blueDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotCreate.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotCreate.setImageResource(R.drawable.pink_dot)
            binding.greenDotCreate.setImageResource(R.drawable.green_dot)
        }

        //function called when the save button is clicked
        binding.saveNotesButtonCreate.setOnClickListener{
            createNotes(it)
        }
        return binding.root
    }

    //function to create a note when the save button is clicked
    private fun createNotes(it: View?) {
        var title = binding.titleCreateTextView.text.toString() //fetching the title typed by user
        //checks whether title is empty or sets the below string
        if (title.isEmpty()){
            title = "Unnamed Note"
        }
        val subtitle = binding.subtitleCreateTextView.text.toString() //fetching the subtitle typed by user
        val content = binding.contentCreateTextView.text.toString() //fetching the content by user

        val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy") //formatting the date in my preferred format
        val currentDate = LocalDate.now().format(formatter) //gets the current date

        //note is only saved if the title, subtitle and content is not empty

        if (title=="Unnamed Note" && subtitle.isEmpty() && content.isEmpty()){
            Toast.makeText(context, "Note not created", Toast.LENGTH_SHORT).show() //create a toast
            Log.e("NOTE_ME", "Note not created") //create a log entry
            Navigation.findNavController(it!!).navigate(R.id.action_fragmentCreateNote_to_fragmentHome)
        }
        else {
            val note = Notes(
                null, //id is set to null as it is autogenerated
                title,
                subtitle,
                content,
                currentDate.toString(),
                color
            ) //creates a note entry with the users input
            viewModel.insertNotes(note) //insert the user's note in the View Model

            Toast.makeText(context, "Note successfully created", Toast.LENGTH_SHORT).show() //create a toast
            Log.e("NOTE_ME", "Note successfully created on $currentDate") //create a log entry

            //navigate to the homepage once the user saves the note
            Navigation.findNavController(it!!).navigate(R.id.action_fragmentCreateNote_to_fragmentHome)
        }
    }
}