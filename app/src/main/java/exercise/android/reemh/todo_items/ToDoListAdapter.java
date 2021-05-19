package exercise.android.reemh.todo_items;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class TodoListViewHolder extends RecyclerView.ViewHolder
{
    TextView timeCreated;
    TextView data;
    CheckBox checkbox;

    public TodoListViewHolder(@NonNull View view)
    {
        super(view);
        this.timeCreated = view.findViewById(R.id.time_created);
        this.data = view.findViewById(R.id.toDo_data);
        this.checkbox = view.findViewById(R.id.checkBox);
    }
}

public class ToDoListAdapter extends RecyclerView.Adapter<TodoListViewHolder>
{

    public ToDoListAdapter()


    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}


