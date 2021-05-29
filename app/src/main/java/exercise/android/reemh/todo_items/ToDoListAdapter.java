package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;


class TodoListViewHolder extends RecyclerView.ViewHolder implements Serializable
{
    TextView data;
    CheckBox checkbox;
    ImageButton deleteButton;

    public TodoListViewHolder(@NonNull View view)
    {
        super(view);
        this.data = view.findViewById(R.id.toDo_data);
        this.checkbox = view.findViewById(R.id.checkBox);
        this.deleteButton = view.findViewById(R.id.delete_button);
    }
}

public class ToDoListAdapter extends RecyclerView.Adapter<TodoListViewHolder>
{
    private TodoItemsHolder holder;

    public ToDoListAdapter(TodoItemsHolder holder)
    {
       this.holder = holder;
    }


    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo_item, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder viewHolder, int position) {
        TodoItem item = this.holder.getCurrentItems().get(position);
        viewHolder.data.setText(item.getData());
        viewHolder.checkbox.setChecked(!item.getIsInProgress());

        if (viewHolder.checkbox.isChecked())
        {
            viewHolder.data.setPaintFlags(viewHolder.data.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else
        {
            viewHolder.data.setPaintFlags(0);
        }

        viewHolder.checkbox.setOnClickListener(v ->
        {
            if (viewHolder.checkbox.isChecked())
            {
                this.holder.markItemDone(this.holder.getCurrentItems().get(position));
            }
            else
            {
                this.holder.markItemInProgress(this.holder.getCurrentItems().get(position));
            }
            notifyDataSetChanged();
        });

        viewHolder.deleteButton.setOnClickListener(v ->
        {
            this.holder.deleteItem(item);
            notifyDataSetChanged();
        });

        viewHolder.data.setOnClickListener(v ->
        {
            Intent editIntent = new Intent(v.getContext(), EditItemActivity.class);
            editIntent.putExtra("item", item.toString());
            v.getContext().startActivity(editIntent);
        });


    }


    @Override
    public int getItemCount() {
        return this.holder.getCurrentItems().size();
    }
}


